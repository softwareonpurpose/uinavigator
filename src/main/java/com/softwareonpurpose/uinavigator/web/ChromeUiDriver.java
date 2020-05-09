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

import com.softwareonpurpose.uinavigator.UiDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ChromeUiDriver extends UiDriver {

    private static final String HOST_NAME = "chrome";

    public static UiDriver getInstance() {
        return new ChromeUiDriver();
    }

    @Override
    public org.openqa.selenium.chrome.ChromeDriver instantiateDriver() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        return new org.openqa.selenium.chrome.ChromeDriver(options);
    }

    @Override
    public void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(getConfig().getTimeout(), TimeUnit.SECONDS);
    }

    @Override
    public String getHostName() {
        return HOST_NAME;
    }
}
