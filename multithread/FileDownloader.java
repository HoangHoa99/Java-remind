package multithread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileDownloader {
    private static final String OUTPUT_FILE = "multithread/output_file.txt";
    private static final int THREAD_NUMBER = 10;
    private final ExecutorService EXECUTOR = Executors.newFixedThreadPool(THREAD_NUMBER);

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void download() {
        long fileSize = getFileSize();
        if (fileSize == -1) {
            System.err.println("Failed to get file size.");
            return;
        }

        long partSize = fileSize / THREAD_NUMBER;

        for (int i = 0; i < THREAD_NUMBER; i++) {
            long startByte = i * partSize;
            long endByte = (i == THREAD_NUMBER - 1) ? fileSize - 1 : startByte + partSize - 1;

            DownloadTask downloadTask = new DownloadTask(startByte, endByte, i, getUrl());

            EXECUTOR.submit(downloadTask);
        }

        EXECUTOR.shutdown();
        try {
            if (!EXECUTOR.awaitTermination(1, TimeUnit.HOURS)) {
                System.err.println("Some tasks did not complete within the timeout.");
            }
        } catch (InterruptedException e) {
            System.err.println("ExecutorService was interrupted: " + e.getMessage());
        }

        assembleDownload();
    }

    public long getFileSize() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(getUrl()).openConnection();
            connection.setRequestMethod("GET");
            return connection.getContentLengthLong();
        } catch (Exception e) {
            System.err.println("Failed to get file size: " + e.getMessage());
            return -1;
        }
    }

    public void assembleDownload() {
        try (FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE)) {
            for (int i = 0; i < THREAD_NUMBER; i++) {
                String partFileName = "part" + i + ".tmp";
                mergePartFile(outputStream, partFileName, i);
            }
            System.out.println("File assembly completed.");
        } catch (Exception e) {
            System.err.println("Error during file assembly: " + e.getMessage());
        } finally {
            deletePartFiles();
        }
    }

    private void mergePartFile(FileOutputStream outputStream, String partFileName, int partNumber) {
        try (FileInputStream inputStream = new FileInputStream(partFileName)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("Part " + partNumber + " merged.");
        } catch (Exception e) {
            System.err.println("Error merging part " + partNumber + ": " + e.getMessage());
        }
    }

    private void deletePartFiles() {
        for (int i = 0; i < THREAD_NUMBER; i++) {
            String partFileName = "part" + i + ".tmp";
            File partFile = new File(partFileName);
            if (partFile.exists()) {
                if (partFile.delete()) {
                    System.out.println("Deleted part file: " + partFileName);
                } else {
                    System.err.println("Failed to delete part file: " + partFileName);
                }
            } else {
                System.err.println("Part file does not exist: " + partFileName);
            }
        }
    }
}