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
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * UI View (e.g. page)
 */
@SuppressWarnings("unused")
public abstract class WebUiView implements UiView {
    private final static String ERROR_CONSTRUCTOR_SCOPE = "Unable to access View constructor; ensure it is parameter-less and has 'public' scope";
    private final WebUiElement viewElement;
    private final String viewUri;

    protected WebUiView(String viewUri, WebUiElement viewElement) {
        this.viewUri = viewUri;
        this.viewElement = viewElement;
    }

    /***
     * Confirm existence of and return expected UI view
     *
     * @param viewClass Class of expected view
     * @param <T> Class extending WebUiView
     * @return WebUiView instance
     */
    public static <T extends WebUiView> T expect(Class<T> viewClass) {
        String invalidCharacters =
                String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])");
        LoggerFactory.getLogger("").info(String.format("Expect '%s'", viewClass.getSimpleName().replaceAll(invalidCharacters, " ")));
        T view = construct(viewClass);
        view.confirmElementStates();
        return construct(viewClass);
    }

    /***
     * Construct a concrete instance of WebUiView
     *
     * @param viewClass Class extension of UI view
     * @param <T> Class extending WebUiView
     * @return WebUiView object
     */
    @SuppressWarnings("WeakerAccess")
    protected static <T extends WebUiView> T construct(Class<T> viewClass) {
        T view = null;
        try {
            Constructor<T> constructor;
            try {
                constructor = viewClass.getConstructor();
                view = constructor.newInstance();
            } catch (NoSuchMethodException | InvocationTargetException e) {
                LoggerFactory.getLogger(viewClass).error(ERROR_CONSTRUCTOR_SCOPE);
                e.printStackTrace();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            LoggerFactory.getLogger(viewClass).error(ERROR_CONSTRUCTOR_SCOPE);
            e.printStackTrace();
        }
        return view;
    }

    /***
     * View-specific logic confirming the state of key elements in a UI view
     *
     * @return boolean confirmation result
     */
    @SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
    protected abstract boolean confirmElementStates();

    /***
     * Load UI view in UI host (e.g. browser)
     */
    protected void load() {
        WebUiHost.getInstance().load(viewUri);
    }

    /***
     * Load UI view in UI host appending relative path to URI of view
     *
     * @param relativeUri String relative path OR query string beginning with "?"
     */
    protected void load(String relativeUri) {
        relativeUri = relativeUri.substring(0, 1).equals("?") ? relativeUri : String.format("/%s", relativeUri);
        String explicitUri = String.format("%s%s", viewUri, relativeUri);
        WebUiHost.getInstance().load(explicitUri);
    }

    /**
     * UI element defining UI view
     *
     * @return The WebUiElement that fully contains the displayed content of the view (web page)
     */
    protected WebUiElement getElement() {
        return viewElement;
    }
}
