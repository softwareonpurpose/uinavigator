package com.craigstockton.uinavigator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrowserHostConfiguration {

    final int timeout;
    private static BrowserHostConfiguration config;

    private BrowserHostConfiguration() {
        InputStream inputStream;

        Properties prop = new Properties();
        String propFileName = "browserhost.properties";

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

    public static BrowserHostConfiguration getInstance() {
        if (config == null)
            config = new BrowserHostConfiguration();
        return config;
    }
}
