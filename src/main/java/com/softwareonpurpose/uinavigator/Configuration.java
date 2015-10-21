package com.softwareonpurpose.uinavigator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    final long timeout;
    private static Configuration config;

    private Configuration() {
        InputStream inputStream;

        Properties prop = new Properties();
        String propFileName = "uinavigator.properties";

        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        Integer configurationTimeout = null;
        if (inputStream != null) {
            try {
                prop.load(inputStream);
                configurationTimeout = Integer.parseInt(prop.getProperty("timeout"));
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        this.timeout = configurationTimeout == null ? 3 : configurationTimeout;
    }

    public static Configuration getInstance() {
        if (config == null)
            config = new Configuration();
        return config;
    }
}
