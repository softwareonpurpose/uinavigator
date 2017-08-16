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

public abstract class UiView {

    private final UiElement viewElement;
    private final String viewUri;

    protected UiView(String viewUri, UiElement viewElement, Class viewClass) {
        this.viewUri = viewUri;
        this.viewElement = viewElement;
    }

    protected abstract boolean confirmElementStates();

    protected void load() {
        UiHost.getInstance().load(viewUri);
    }

    protected void load(String relativeUri) {
        relativeUri = relativeUri.substring(0, 1).equals("?") ? relativeUri : String.format("/%s", relativeUri);
        String explicitUri = String.format("%s%s", viewUri, relativeUri);
        UiHost.getInstance().load(explicitUri);
    }

    protected UiElement getElement() {
        return viewElement;
    }
}
