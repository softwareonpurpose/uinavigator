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

import org.openqa.selenium.WebDriver;

/**
 * DriverInstantiation details, including specific driver executable and configuration properties
 */
public abstract class DriverInstantiation {
    private static Configuration config;

    /**
     * Get Configuration details used to instantiate UI driver
     *
     * @return Configuration details
     */
    protected static Configuration getConfig() {
        if (config == null) {
            config = Configuration.getInstance();
        }
        return config;
    }

    /**
     * Execute the instantiation of Selenium WebDriver.
     * Note: tightly couples DriverInstantiation to Selenium WebDriver, while informing future decoupling
     *
     * @return WebDriver instance using Selenium interface
     */
    WebDriver execute() {
        WebDriver driver = instantiateDriver();
        provideTimeForDriverToLoad();
        configureDriver(driver);
        return driver;
    }

    /**
     * For use by concrete implementations of DriverInstantiation, when necessary
     */
    protected void provideTimeForDriverToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementation details of concrete DriverInstantiations
     *
     * @return WebDriver instance using Selenium interface
     */
    protected abstract WebDriver instantiateDriver();

    /**
     * Implementation details of concrete DriverInstantiations
     *
     * @param driver WebDriver instance using Selenium interface
     */
    protected abstract void configureDriver(WebDriver driver);

    /**
     * Arbitrary name of the instantiated UiHost
     *
     * @return String name of UiHost
     */
    @SuppressWarnings("unused")
    public abstract String getHostName();
}
