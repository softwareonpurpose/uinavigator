package com.softwareonpurpose.uinavigator;
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

import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class UiView {
    private final static String ERROR_CONSTRUCTOR_SCOPE =
            "Unable to access View constructor; ensure it is parameter-less and has 'public' scope";
    private final UiElement viewElement;

    private final String address;
    private final UiHost host;

    protected UiView(String viewAddress, UiElement viewElement, UiHost host) {
        this.address = viewAddress;
        this.viewElement = viewElement;
        this.host = host;
    }

    public static <T extends UiView> T expect(Class<T> viewClass, UiHost host) {
        String invalidCharacters =
                String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])");
        final String viewClassName = viewClass.getSimpleName().replaceAll(invalidCharacters, " ");
        LoggerFactory.getLogger("").info(String.format("Expect '%s'", viewClassName));
        T view = construct(viewClass, host);
        host.switchTo(view.getElement());
        view = construct(viewClass, host);
        if (!view.confirmElementStates()) {
            String messageFormat = "Unable to confirm the state of '%s'";
            String message = String.format(messageFormat, viewClassName);
            LoggerFactory.getLogger("").info(message);
        }
        return construct(viewClass, host);
    }

    protected static <T extends UiView> T construct(Class<T> viewClass, UiHost host) {
        T view = null;
        try {
            Constructor<T> constructor;
            try {
                constructor = viewClass.getConstructor(UiHost.class);
                view = constructor.newInstance(host);
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

    protected void load() {
        host.load(address);
    }

    protected void load(String relativeUri) {
        relativeUri = relativeUri.startsWith("?") ? relativeUri : String.format("/%s", relativeUri);
        String explicitUri = String.format("%s%s", address, relativeUri);
        host.load(explicitUri);
    }

    protected UiElement getElement() {
        return viewElement;
    }

    protected abstract boolean confirmElementStates();

    public String getAddress() {
        return address;
    }
}
