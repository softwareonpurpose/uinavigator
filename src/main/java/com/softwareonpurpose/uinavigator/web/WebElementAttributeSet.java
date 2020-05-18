package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiElementAttributeSet;
import com.softwareonpurpose.uinavigator.UiElementGet;
import com.softwareonpurpose.uinavigator.UiHost;

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
public class WebElementAttributeSet extends UiElementAttributeSet {
    public WebElementAttributeSet(UiElementGet getBehavior, UiHost host) {
        super(getBehavior, host);
    }

    public static WebElementAttributeSet getInstance(UiElementGet getBehavior, UiHost host) {
        return new WebElementAttributeSet(getBehavior, host);
    }

    public void execute(String attribute, String value) {
        Object[] arguments = {getBehavior.execute(), attribute, value};
        final String script = "arguments[0].setAttribute(arguments[1], arguments[2]);";
        host.executeScript(script, arguments);
    }
}
