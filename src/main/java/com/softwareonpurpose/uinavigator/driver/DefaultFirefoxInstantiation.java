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
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DefaultFirefoxInstantiation extends DriverInstantiation {

    private static final String HOST_NAME = "firefox";

    private DefaultFirefoxInstantiation() {
    }

    public static DefaultFirefoxInstantiation getInstance() {
        return new DefaultFirefoxInstantiation();
    }

    @Override
    protected WebDriver instantiateDriver() {
        return new FirefoxDriver();
    }

    @Override
    protected void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getConfig().getTimeout()));
    }

    @Override
    public String getHostName() {
        return HOST_NAME;
    }
}
