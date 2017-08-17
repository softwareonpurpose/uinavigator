/**
 * Copyright 2017 Craig A. Stockton
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.softwareonpurpose.uinavigator;

import org.slf4j.LoggerFactory;

public abstract class UiRegion {

    private final UiElement regionElement;
    private static boolean suppressLogging;

    protected UiRegion(UiElement regionElement) {
        this.regionElement = regionElement;
        if (!suppressLogging)
            LoggerFactory.getLogger(this.getClass()).info(String.format("In %s...", getDescription()));
    }

    public boolean isVisible() {
        return regionElement.waitUntilVisible();
    }

    public static void suppressConstructionLogging(boolean suppress) {
        suppressLogging = suppress;
    }

    @SuppressWarnings("WeakerAccess")
    protected UiElement getElement() {
        return regionElement;
    }

    @SuppressWarnings("WeakerAccess")
    protected String getDescription() {
        return getElement().getDescription();
    }
}
