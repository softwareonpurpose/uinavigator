package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiStateBehavior;

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
public class WebStateBehavior extends UiStateBehavior {
    private final WebGetElementBehavior getBehavior;
    private final String attribute;
    private final String value;

    private WebStateBehavior(WebGetElementBehavior getBehavior, String attribute, String value) {
        this.getBehavior = getBehavior;
        this.attribute = attribute;
        this.value = value;
    }

    public static WebStateBehavior getInstance(WebGetElementBehavior getBehavior, String attribute, String value) {
        return new WebStateBehavior(getBehavior, attribute, value);
    }

    @Override
    public boolean execute() {
        return getBehavior.execute().getAttribute(attribute).contains(value);
    }
}
