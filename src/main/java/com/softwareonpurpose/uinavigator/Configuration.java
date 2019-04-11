/*
  Copyright 2019 Craig A. Stockton
  <p/>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p/>
  http://www.apache.org/licenses/LICENSE-2.0
  <p/>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */
package com.softwareonpurpose.uinavigator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Configuration config;
    private final long timeout;

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

    public long getTimeout() {
        return timeout;
    }
}
