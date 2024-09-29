package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
public class PropertiesController {

    // Handle form submission
    @PostMapping("/api/submit")
    public CompletableFuture<String> submitForm(@RequestParam String username,
                                                @RequestParam String password,
                                                @RequestParam String dropdown) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Simulate long-running task
                Thread.sleep(5000);
                if ("noData".equals(dropdown)) {
                    return "NO_DATA";
                } else {
                    return "DATA_READY";
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Handle file download if data is available
    @GetMapping("/api/download")
    public void downloadFile(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=data.xlsx");

        // Simulate Excel file creation
        response.getOutputStream().write("Excel Data".getBytes());
        response.getOutputStream().flush();
    }

    // WebSocket to send logs
    @GetMapping("/api/logs")
    public SseEmitter streamLogs() {
        SseEmitter emitter = new SseEmitter();

        CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    emitter.send("Log line " + i);
                    Thread.sleep(1000);
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}
