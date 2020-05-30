package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebUiDriver;

import java.util.List;

public interface UiDriver {
    static UiDriver getInstance() {
        return new WebUiDriver();
    }

    void load(String address);

    void executeScript(String script, Object[] args);

    List<Object> findElements(Object locator);

    boolean waitUntilVisible(UiElementGet getElement);

    String getName();

    void quit();

    String getState(String[] identifiers);

    void switchTo(UiElement element);

    Object findElement(Object locator);

    String getAddress();
}
