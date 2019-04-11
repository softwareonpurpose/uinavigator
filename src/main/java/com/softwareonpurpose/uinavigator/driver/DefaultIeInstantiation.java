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
package com.softwareonpurpose.uinavigator.driver;

import com.softwareonpurpose.uinavigator.DriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class DefaultIeInstantiation extends DriverInstantiation {
    private static final String HOST_NAME = "ie";

    public static DefaultIeInstantiation getInstance() {
        return new DefaultIeInstantiation();
    }

    @Override
    protected WebDriver instantiateDriver() {
        System.setProperty("webdriver.ie.driver", "./src/main/resources/IEDriverServer.exe");
        return new InternetExplorerDriver();
    }

    @Override
    protected void configureDriver(WebDriver driver) {
        provideTimeForDriverToLoad();
        driver.manage().timeouts().implicitlyWait(getConfig().getTimeout(), TimeUnit.SECONDS);
    }

    @Override
    public String getHostName() {
        return HOST_NAME;
    }
}
