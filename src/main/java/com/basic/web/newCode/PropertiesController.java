package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
public class PropertiesController {

    @Autowired
    private DropdownPropertiesService dropdownPropertiesService;

    // Endpoint to fetch dropdown values
    @GetMapping("/api/dropdowns")
    public Map<String, Map<String, String>> getDropdownValues() throws IOException {
        Properties dropdown1 = dropdownPropertiesService.getDropdown1Properties();
        Properties dropdown2 = dropdownPropertiesService.getDropdown2Properties();

        // Convert Properties to Map
        Map<String, String> dropdown1Values = new HashMap<>();
        dropdown1.forEach((key, value) -> dropdown1Values.put(key.toString(), value.toString()));

        Map<String, String> dropdown2Values = new HashMap<>();
        dropdown2.forEach((key, value) -> dropdown2Values.put(key.toString(), value.toString()));

        // Return a map of both dropdowns
        Map<String, Map<String, String>> dropdowns = new HashMap<>();
        dropdowns.put("dropdown1", dropdown1Values);
        dropdowns.put("dropdown2", dropdown2Values);

        return dropdowns;
    }

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
