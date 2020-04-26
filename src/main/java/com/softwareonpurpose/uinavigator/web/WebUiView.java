/*
  Copyright 2019 Craig A. Stockton
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
package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiView;

public abstract class WebUiView extends UiView {
    private final String viewUri;

    protected WebUiView(String viewUri, WebUiElement viewElement) {
        super(viewElement);
        this.viewUri = viewUri;
    }

    protected void load() {
        WebUiHost.getInstance().load(viewUri);
    }

    protected void load(String relativeUri) {
        relativeUri = relativeUri.startsWith("?") ? relativeUri : String.format("/%s", relativeUri);
        String explicitUri = String.format("%s%s", viewUri, relativeUri);
        WebUiHost.getInstance().load(explicitUri);
    }

    @Override
    protected WebUiElement getElement() {
        return (WebUiElement) super.getElement();
    }

    public String getUri() {
        return viewUri;
    }
}
