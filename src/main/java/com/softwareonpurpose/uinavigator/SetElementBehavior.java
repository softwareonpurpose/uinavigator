package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebGetElementBehavior;
import com.softwareonpurpose.uinavigator.web.WebSetDefaultBehavior;
import com.softwareonpurpose.uinavigator.web.WebSetSelectBehavior;

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
public abstract class SetElementBehavior {

    public static SetElementBehavior getSelectInstance(GetElementBehavior getBehavior) {
        return new WebSetSelectBehavior((WebGetElementBehavior) getBehavior);
    }

    public static SetElementBehavior getInstance(GetElementBehavior getBehavior) {
        return new WebSetDefaultBehavior((WebGetElementBehavior) getBehavior);
    }

    public abstract void execute(String value);
}
