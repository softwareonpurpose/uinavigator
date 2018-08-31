/**
 * Copyright 2018 Craig A. Stockton
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unused")
public abstract class UiView {

    private final static String ERROR_CONSTRUCTOR_SCOPE = "Unable to access View constructor; ensure it has 'public' scope";
    private final UiElement viewElement;
    private final String viewUri;

    /***
     * Super constructor of UiView classes
     * @param viewUri The full uri to the web page (view)
     * @param viewElement The UiElement fully containing the displayed content of the web page (view)
     */
    protected UiView(String viewUri, UiElement viewElement) {
        this.viewUri = viewUri;
        this.viewElement = viewElement;
    }

    /***
     * Called from any 'action' method which is expected to result in this view being displayed
     * @param viewClass The UiView class for the view expected to be displayed
     * @param <T> Any class extending UiView
     * @return An instantiated UiView object representing the view expected to be displayed
     */
    public static <T extends UiView> T expect(Class<T> viewClass) {
        String invalidCharacters =
                String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])");
        LoggerFactory.getLogger("").info(String.format("Expect '%s'", viewClass.getSimpleName().replaceAll(invalidCharacters, " ")));
        T view = construct(viewClass);
        view.confirmElementStates();
        return construct(viewClass);
    }

    /***
     * Construct a concrete UiView object
     * @param viewClass The UiView class to be constructed
     * @param <T> A class extending UiView
     * @return An instantiated UiView object
     */
    @SuppressWarnings("WeakerAccess")
    protected static <T extends UiView> T construct(Class<T> viewClass) {
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
     * Includes everything necessary to confirm a concrete UiView is actually displayed
     * @return boolean
     */
    @SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
    protected abstract boolean confirmElementStates();

    /***
     * Navigate the UiHost (browser) to the uri for this concrete UiView
     */
    protected void load() {
        UiHost.getInstance().load(viewUri);
    }

    /***
     * Navigate the UiHost (browser) to the uri for this concrete UiView, with appended relative uri or query string
     * @param relativeUri A relative uri to extend this UiView uri OR a query string
     */
    protected void load(String relativeUri) {
        relativeUri = relativeUri.substring(0, 1).equals("?") ? relativeUri : String.format("/%s", relativeUri);
        String explicitUri = String.format("%s%s", viewUri, relativeUri);
        UiHost.getInstance().load(explicitUri);
    }

    /**
     * The element containging the displayed content of the view (web page)
     *
     * @return The UiElement that fully contains the displayed content of the view (web page)
     */
    protected UiElement getElement() {
        return viewElement;
    }
}
