package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiGetElement;
import com.softwareonpurpose.uinavigator.web.WebElementSet;
import com.softwareonpurpose.uinavigator.web.WebSelectSet;

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
public abstract class ElementSet {
    protected final UiGetElement getBehavior;

    protected ElementSet(UiGetElement getBehavior) {
        this.getBehavior = getBehavior;
    }

    public static ElementSet getSelectInstance(UiGetElement getBehavior) {
        return new WebSelectSet(getBehavior);
    }

    public static ElementSet getInstance(UiGetElement getBehavior) {
        return new WebElementSet(getBehavior);
    }

    public abstract void execute(String value);
}
