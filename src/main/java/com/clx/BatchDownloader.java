package com.clx;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchDownloader {
    final int MAX_PARALLEL = 4;
    final ExecutorService exec;
    final ImageConverter conv;

    public BatchDownloader(ImageConverter converter) {
        exec = Executors.newFixedThreadPool(MAX_PARALLEL);
        conv = converter;
    }

    public void download(Path targetDir, List<String> urls) {
        System.out.printf("[INFO] adding %d files\n", urls.size());
        var batchId = UUID.randomUUID().toString();
        for (String u : urls) {
            exec.submit(() -> {
                var filename = String.format("%s/%s-%s.jpg", targetDir.toString(), batchId, extractFilename(u));
                try {
                    downloadFile(u, filename);
                    conv.convert(filename);
                    System.out.printf("[DONE] %s\n", u);
                } catch (Exception e) {
                    System.err.printf("[ERROR] %s: %s\n", u, e.getMessage());
                }
            });
        }
    }

    void downloadFile(String url, String filename) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<Path> res = client.send(
                request,
                HttpResponse.BodyHandlers.ofFile(Path.of(filename)));

        var status = res.statusCode();
        if (status != 200) {
            throw new Exception(String.format("unexpected status code received (%d)", status));
        }
    }

    String extractFilename(String url) {
        var pieces = url.split("/");
        var n = pieces.length;
        return pieces[n - 1];
    }
}
