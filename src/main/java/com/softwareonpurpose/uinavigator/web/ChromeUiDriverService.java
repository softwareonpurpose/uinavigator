package com.softwareonpurpose.uinavigator.web;

//  Copyright 2020 Craig A. Stockton
//  <p/>
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//  <p/>
//  http://www.apache.org/licenses/LICENSE-2.0
//  <p/>
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.

import com.softwareonpurpose.uinavigator.UiDriverService;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public class ChromeUiDriverService extends UiDriverService {
    private static ChromeOptions options;
    private static ChromeDriverService service;
    private final String driverFilePathname;

    public ChromeUiDriverService(boolean isHeadless) {
        super("chrome", "browser");
        final boolean isWindows = System.getProperty("os.name").toLowerCase().contains("windows");
        String driverExecutable = isWindows ? "chromedriver.exe" : "chromedriver";
        this.driverFilePathname = String.format("%s/%s", getConfig().getDriverPath(), driverExecutable);
        options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200", "--ignore-certificate-errors", "--disable-gpu");
        if (isHeadless) {
            options.addArguments("--headless");
        }
    }

    public static ChromeUiDriverService getInstance() {
        return new ChromeUiDriverService(true);
    }

    public static ChromeUiDriverService getInstance(boolean isHeadless) {
        return new ChromeUiDriverService(isHeadless);
    }

    private ChromeDriverService getService() {
        if (service == null) {
            service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(driverFilePathname))
                    .usingAnyFreePort()
                    .build();
            try {
                service.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return service;
    }

    @Override
    public RemoteWebDriver getDriver() {
        return new RemoteWebDriver(getService().getUrl(), options);
    }

    @Override
    public void quit() {
        service.stop();
    }
}
