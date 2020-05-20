package com.softwareonpurpose.uinavigator.web;
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

import com.softwareonpurpose.uinavigator.UiDriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ChromeDriverInstantiation extends UiDriverInstantiation {
    private final String driverFilePathname;
    private final boolean isHeadless;

    public ChromeDriverInstantiation(boolean isHeadless) {
        super("chrome", "browser");
        final boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
        String driverExecutable = isWindows ? "chromedriver.exe" : "chromedriver";
        this.driverFilePathname = String.format("%s/%s", getConfig().getDriverPath(), driverExecutable);
        this.isHeadless = isHeadless;
    }

    public static ChromeDriverInstantiation getInstance() {
        return new ChromeDriverInstantiation(true);
    }

    public static ChromeDriverInstantiation getInstance(boolean isHeadless) {
        return new ChromeDriverInstantiation(isHeadless);
    }

    @Override
    public ChromeDriver instantiateDriver() {
        System.setProperty("webdriver.chrome.driver", driverFilePathname);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200", "--ignore-certificate-errors", "--disable-gpu");
        if (isHeadless) {
            options.addArguments("--headless");
        }
        return new ChromeDriver(options);
    }

    @Override
    public void configureDriver(Object driver) {
        ((WebDriver) driver).manage().timeouts().implicitlyWait(getConfig().getTimeout(), TimeUnit.SECONDS);
    }
}
