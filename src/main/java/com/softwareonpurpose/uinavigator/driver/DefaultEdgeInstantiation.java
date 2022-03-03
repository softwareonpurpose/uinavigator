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
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DefaultEdgeInstantiation extends DriverInstantiation {
    private static final String HOST_NAME = "ie";

    public static DefaultEdgeInstantiation getInstance() {
        return new DefaultEdgeInstantiation();
    }

    @Override
    protected WebDriver instantiateDriver() {
        return new EdgeDriver();
    }

    @Override
    protected void configureDriver(WebDriver driver) {
        provideTimeForDriverToLoad();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(getConfig().getTimeout()));
    }

    @Override
    public String getHostName() {
        return HOST_NAME;
    }
}
