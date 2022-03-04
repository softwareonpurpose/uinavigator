package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebElementGetAttribute;

/*
  Copyright 2020 - 2022 Craig A. Stockton
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
public abstract class ElementGetAttribute {
    protected final UiGetElement getBehavior;

    protected ElementGetAttribute(UiGetElement getBehavior) {
        this.getBehavior = getBehavior;
    }

    public static ElementGetAttribute getInstance(UiGetElement getElement) {
        return new WebElementGetAttribute(getElement);
    }

    public abstract String execute(String attribute);
}
