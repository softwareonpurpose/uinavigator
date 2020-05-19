package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;

import java.util.List;

public class UiDriver {
    public final UiDriverGetAddress getAddress;
    private final UiDriverWaitUntilVisible waitUntilVisible;
    private final UiDriverLoad load;
    private final UiDriverScriptExecute executeScript;
    private final UiDriverFindElements findElements;
    private final UiDriverFindElement findElement;
    private final UiDriverQuit quit;
    private final UiDriverState state;
    private final UiDriverGet getDriver;
    private final UiDriverSwitchTo switchTo;

    private UiDriver() {
        getDriver = UiDriverGet.getInstance();
        load = UiDriverLoad.getInstance(getDriver);
        executeScript = UiDriverScriptExecute.getInstance(getDriver);
        getAddress = UiDriverGetAddress.getInstance(getDriver);
        findElements = UiDriverFindElements.getInstance(getDriver);
        findElement = UiDriverFindElement.getInstance(getDriver);
        waitUntilVisible = UiDriverWaitUntilVisible.getInstance(getDriver);
        quit = UiDriverQuit.getInstance(getDriver);
        state = UiDriverState.getInstance(getDriver);
        switchTo = UiDriverSwitchTo.getInstance(getDriver);
    }

    public static UiDriver getInstance() {
        return new UiDriver();
    }

    public void load(String address) {
        load.execute(address);
    }

    public void executeScript(String script, Object[] args) {
        this.executeScript.execute(script, args);
    }

    public List<Object> findElements(Object locator) {
        return findElements.execute(locator);
    }

    public boolean waitUntilVisible(UiElementGet getElement) {
        return waitUntilVisible.execute(getElement);
    }

    public String getName() {
        return getDriver.execute().getClass().getName();
    }

    public void quit() {
        quit.execute();
    }

    public String state(String[] identifiers) {
        return state.execute(identifiers);
    }

    public void switchTo(UiElement element) {
        switchTo.execute(element);
    }

    public Object findElement(Object locator) {
        return findElement.execute(locator);
    }
}
