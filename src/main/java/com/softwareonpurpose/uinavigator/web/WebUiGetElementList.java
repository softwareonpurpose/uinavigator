package com.softwareonpurpose.uinavigator.web;
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

import com.softwareonpurpose.uinavigator.UiGetElementList;
import com.softwareonpurpose.uinavigator.UiElement;

import java.util.Collection;

public interface WebUiGetElementList extends UiGetElementList {
    @Override
    Collection<UiElement> execute();
}
