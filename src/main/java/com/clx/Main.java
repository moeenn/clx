package com.clx;

import java.nio.file.Files;
import java.nio.file.Path;
import io.javalin.Javalin;

public class Main {
    final static int PORT = 5000;

    static void run() throws Exception {
        Path targetDir = Path.of("./downloads");
        Files.createDirectories(targetDir);
        var downloader = new BatchDownloader();

        System.out.printf("[INFO] starting server: Port=%d\n", Main.PORT);
        var app = Javalin.create(config -> {
            config.startup.showJavalinBanner = false;
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });

            config.routes.post("/download", ctx -> {
                var body = ctx.bodyAsClass(BatchDownloadRequest.class);
                downloader.download(targetDir, body.urls());
                ctx.status(200);
            });
        });

        app.start(Main.PORT);
    }

    public static void main(String[] args) {
        try {
            Main.run();
        } catch (Exception e) {
            System.err.printf("[ERROR] failed to create target dir: %s.\n", e.getMessage());
        }
    }
}