package com.softwareonpurpose.uinavigator;
/*
  Copyright 2020 Craig A. Stockton
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

import com.softwareonpurpose.uinavigator.web.ChromeUiDriverService;

/**
 * DriverInstantiation details, including specific driver executable and configuration properties
 */
public abstract class UiDriverService {
    protected static UiNavigatorConfiguration config;
    private final String name;
    private final String type;

    protected UiDriverService(String name, String type) {
        this.name = name;
        this.type = type;
        config = UiNavigatorConfiguration.getInstance();
    }

    public static UiDriverService getInstance() {
        return ChromeUiDriverService.getInstance();
    }

    /**
     * Execute the instantiation of Selenium WebDriver.
     * Note: tightly couples DriverInstantiation to Selenium WebDriver, while informing future decoupling
     *
     * @return WebDriver instance using Selenium interface
     */
    public abstract Object getDriver();

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract void quit();
}
