package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
@EnableWebSocket
@EnableConfigurationProperties(AppProperties.class) // Load external properties
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        // Fetch the port from external properties
        String port = System.getProperty("server.port", "8080");
        openLandingPageInBrowser("http://localhost:" + port);
    }

    public static void openLandingPageInBrowser(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
