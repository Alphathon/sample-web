package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Service
public class DropdownPropertiesService {

    @Value("${dropdown1.file}")
    private String dropdown1File;

    @Value("${dropdown2.file}")
    private String dropdown2File;

    public Properties loadProperties(String filename) throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(filename)));
        return properties;
    }

    public Properties getDropdown1Properties() throws IOException {
        return loadProperties(dropdown1File);
    }

    public Properties getDropdown2Properties() throws IOException {
        return loadProperties(dropdown2File);
    }
}
