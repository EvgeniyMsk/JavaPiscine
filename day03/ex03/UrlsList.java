import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UrlsList {
    private ArrayList<String> urls;
    private ArrayList<String> allUrls;

    public UrlsList() {
        urls = new ArrayList<String>();
        allUrls = new ArrayList<String>();
    }

    public int getsize() {
        return urls.size();
    }

    public int getUrlsCount(String url) {
        return (allUrls.indexOf(url) + 1);
    }

    public void putUrls(String Filename) {

        try (BufferedReader br = new BufferedReader(new FileReader(Filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                urls.add(line);
                allUrls.add(line);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
    }

    public synchronized String download() {
        if (this.urls.size() > 0) {
            String url = this.urls.get(0);
            this.urls.remove(0);
            return (url);
        }
        return (null);
    }
}
