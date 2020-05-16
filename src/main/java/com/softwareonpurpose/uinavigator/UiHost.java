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

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Host for application UI
 */
@SuppressWarnings("WeakerAccess")
public class UiHost {
    private static final Logger logger = LoggerFactory.getLogger("");
    private final UiDriverBehaviors behaviors;

    private UiHost() {
        behaviors = UiDriverBehaviors.getInstance();
    }

    public static UiHost getInstance() {
        return new UiHost();
    }

    public Object findElement(By locator) {
        return behaviors.findElement(locator);
    }

    public List<Object> findElements(By locator) {
        return behaviors.findElements(locator);
    }

    boolean waitUntilVisible(UiElementGet element) {
        return behaviors.waitUntilVisible(element);
    }

    /***
     * Quit WebUiHost
     */
    public void quit() {
        behaviors.quit();
    }

    /**
     * Navigate UI host to URI
     *
     * @param address String URI
     */
    public void load(String address) {
        logger.info(String.format("Navigate browser to %s", address));
        behaviors.load(address);
    }

    /***
     * Execute JavaScript
     *
     * @param script String to execute
     */
    @SuppressWarnings("unused")
    public void executeScript(String script, Object... args) {
        behaviors.executeScript(script, args);
    }

    void setAttribute(Object element, String attribute, String value) {
        executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attribute, value);
    }

    /**
     * Current URI of UI host
     *
     * @return String URI
     */
    public String getAddress() {
        return behaviors.getAddress.execute();
    }

    /**
     * Get name of UI host driver
     *
     * @return String name of driver
     */
    public String getDriverName() {
        return behaviors.getName();
    }

    public String getState(String... identifiers) {
        return behaviors.state(identifiers);
    }

    public UiDriverBehaviors getBehaviors() {
        return behaviors;
    }

    public void switchTo(UiElement element) {
        behaviors.switchTo(element);
    }
}
