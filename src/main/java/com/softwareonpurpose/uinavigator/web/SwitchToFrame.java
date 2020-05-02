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
public class SwitchToFrame implements WebSwitchToBehavior {
    private final WebGetElementBehavior getElement;

    public SwitchToFrame(WebGetElementBehavior getElement) {
        this.getElement = getElement;
    }

    public static SwitchToFrame getInstance(WebGetElementBehavior getElement) {
        return new SwitchToFrame(getElement);
    }

    @Override
    public void execute() {
        WebUiHost.getInstance().switchTo(getElement.execute());
    }
}
