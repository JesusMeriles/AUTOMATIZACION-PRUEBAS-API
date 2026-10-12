package com.todoly.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiConfig {

    private static final Properties properties = new Properties();
    private static ApiConfig instance;

    static {
        try (InputStream input = ApiConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties in resources");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading config.properties", ex);
        }
    }

    private ApiConfig() {
    }

    public static synchronized ApiConfig getInstance() {
        if (instance == null) {
            instance = new ApiConfig();
        }
        return instance;
    }

    public String getProperty(String key) {
        String envValue = System.getenv(key.replace('.', '_').toUpperCase());
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }
        String systemProperty = System.getProperty(key);
        if (systemProperty != null && !systemProperty.isEmpty()) {
            return systemProperty;
        }
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getUserEndpoint() {
        return getProperty("user.endpoint");
    }

    public String getUserCurrentEndpoint() {
        return getProperty("user.current.endpoint");
    }

    public String getTokenEndpoint() {
        return getProperty("token.endpoint");
    }

    public String getProjectsEndpoint() {
        return getProperty("projects.endpoint");
    }

    public String getProjectItemEndpoint(long id) {
        return getProperty("project.item.endpoint").replace("{0}", String.valueOf(id));
    }
}
