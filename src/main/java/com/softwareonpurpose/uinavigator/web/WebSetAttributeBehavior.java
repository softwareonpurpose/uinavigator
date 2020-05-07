package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.SetAttributeBehavior;
import org.openqa.selenium.WebElement;

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
public class WebSetAttributeBehavior extends SetAttributeBehavior {
    private final WebGetElementBehavior getBehavior;

    public WebSetAttributeBehavior(WebGetElementBehavior getBehavior) {
        this.getBehavior = getBehavior;
    }

    public static WebSetAttributeBehavior getInstance(WebGetElementBehavior getBehavior) {
        return new WebSetAttributeBehavior(getBehavior);
    }

    public void execute(String attribute, String value) {
        WebUiHost.getInstance().setAttribute((WebElement) getBehavior.execute(), attribute, value);
    }
}
