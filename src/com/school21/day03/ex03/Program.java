public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Пример: java Program --threadsCount=3");
            System.exit(-1);
        }
        int threadsCount = Integer.parseInt(args[0].split("=")[1]);
        UrlsList list = new UrlsList();
        list.putUrls("files_urls.txt");
        for (int i = 0; i < threadsCount; i++) {
            Thread t = new Thread(new Torrent("Thread" + "-" + (i + 1) ,list));
            t.start();
        }
    }
}
