package multithread;

public class Main {
    public static void main(String[] args) {
        // change url for testing
        String url = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
        FileDownloader fileDownloader = new FileDownloader();
        fileDownloader.setUrl(url);

        fileDownloader.download();
    }
}
