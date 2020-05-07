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
import com.softwareonpurpose.uinavigator.GetTextBehavior;
import org.openqa.selenium.WebElement;

public class WebGetTextDefaultBehavior extends GetTextBehavior {
    private final WebGetElementBehavior getBehavior;

    public WebGetTextDefaultBehavior(WebGetElementBehavior getElementBehavior) {
        this.getBehavior = getElementBehavior;
    }

    @Override
    public String execute() {
        final WebElement element = getBehavior.execute();
        if (element == null) {
            return null;
        }
        boolean isInputElement = element.getTagName().equals("input");
        return isInputElement ? element.getAttribute("value") : element.getText();
    }
}
