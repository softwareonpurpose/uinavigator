/**
 * Copyright 2017 Craig A. Stockton
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.WebDriver;

public abstract class DriverInstantiation {
    private static Configuration config;

    protected static Configuration getConfig() {
        if (config == null) {
            config = Configuration.getInstance();
        }
        return config;
    }

    WebDriver execute() {
        WebDriver driver = instantiateDriver();
        provideTimeForDriverToLoad();
        configureDriver(driver);
        return driver;
    }

    protected void provideTimeForDriverToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected abstract WebDriver instantiateDriver();

    protected abstract void configureDriver(WebDriver driver);

    @SuppressWarnings("unused")
    public abstract String getHostName();
}
