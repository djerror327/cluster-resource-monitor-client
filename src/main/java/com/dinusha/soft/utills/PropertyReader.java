package com.dinusha.soft.utills;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.function.Supplier;

public interface PropertyReader {
    Logger LOGGER = Logger.getLogger(PropertyReader.class);
    Supplier<Properties> READ = () -> {
        final Properties properties = new Properties();
        try {
            Path fileRoot = Paths.get("./application.properties");
            if (Files.exists(fileRoot)) {
                LOGGER.debug("Reading application property file form root folder : ./application.properties");
                properties.load(new FileInputStream("./application.properties"));
            } else {
                LOGGER.debug("Reading application property file form codebase folder : ./src/main/resources/application.properties");
                properties.load(new FileInputStream("./src/main/resources/application.properties"));
            }
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace());
        }
        return properties;
    };
}
