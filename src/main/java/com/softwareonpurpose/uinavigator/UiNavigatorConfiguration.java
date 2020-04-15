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

import java.io.InputStream;
import java.util.Properties;

public class UiNavigatorConfiguration {

    private static UiNavigatorConfiguration config;
    private final long timeout;

    UiNavigatorConfiguration(String propertiesFilename) {
        Properties prop = new Properties();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFilename)) {
            if (inputStream != null) {
                prop.load(inputStream);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        String configurationTimeout = prop.getProperty("timeout");
        this.timeout = configurationTimeout == null ? 3 : Integer.parseInt(configurationTimeout);
    }

    /**
     * Get UiNavigatorConfiguration instance
     *
     * @return UiNavigatorConfiguration details of UiNavigator
     */
    public static UiNavigatorConfiguration getInstance() {
        if (config == null)
            config = new UiNavigatorConfiguration("uinavigator.properties");
        return config;
    }

    /**
     * Get default 'timeout' value
     *
     * @return long timeout value
     */
    public long getTimeout() {
        return timeout;
    }
}
