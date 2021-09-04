import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Torrent implements Runnable {
    public String Name;
    public UrlsList urls;

    public Torrent(String name, UrlsList Urls) {
        this.Name = name;
        this.urls = Urls;
    }

    public void startMessage(int number) {
        System.out.printf("%s start download file number %d\n", this.Name, number);
    }

    private void finishMessage(int number) {
        System.out.printf("%s finish download file number %d\n", this.Name, number);
    }

    public void run() {
        while (urls.getsize() > 0) {
            try {
                String filename = urls.download();

                if (filename != null) {
                    startMessage(urls.getUrlsCount(filename));

                    BufferedInputStream in = new BufferedInputStream(new URL(filename).openStream());
                    String[] namesArray = filename.split("/");
                    FileOutputStream out = new FileOutputStream(namesArray[namesArray.length - 1]);
                    byte[] buffer = new byte[4096];
                    int pack;
                    while ((pack = in.read(buffer, 0, 4096)) != -1) {
                        out.write(buffer, 0, pack);
                    }
                    out.close();
                    in.close();
                    finishMessage(urls.getUrlsCount(filename));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
