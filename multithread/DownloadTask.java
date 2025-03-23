package multithread;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask implements Runnable {

    private long startByte;
    private long endByte;
    private int partNumber;
    private String url;

    public DownloadTask(long startByte, long endByte, int partNumber, String url) {
        this.startByte = startByte;
        this.endByte = endByte;
        this.partNumber = partNumber;
        this.url = url;
    }

    public long getStartByte() {
        return startByte;
    }

    public void setStartByte(long startByte) {
        this.startByte = startByte;
    }

    public long getEndByte() {
        return endByte;
    }

    public void setEndByte(long endByte) {
        this.endByte = endByte;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            System.out.println("Part " + getPartNumber() + " started downloading: " + (getEndByte() - getStartByte() + 1) + " bytes");

            HttpURLConnection connection = (HttpURLConnection) new URL(getUrl()).openConnection();
            connection.setRequestProperty("Range", "bytes=" + getStartByte() + "-" + getEndByte());

            try (InputStream inputStream = connection.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream("part" + getPartNumber() + ".tmp")) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            System.out.println("Part " + getPartNumber() + " finished downloading.");
        } catch (Exception e) {
            System.err.println("Error in part " + getPartNumber() + ": " + e.getMessage());
        }
    }
}