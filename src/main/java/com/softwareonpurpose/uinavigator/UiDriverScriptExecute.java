package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.uinavigator.web.WebDriverScriptExecute;

public abstract class UiDriverScriptExecute {
    protected final UiDriverGet getDriver;

    protected UiDriverScriptExecute(UiDriverGet getDriver) {
        this.getDriver = getDriver;
    }

    public static UiDriverScriptExecute getInstance(UiDriverGet getDriver) {
        return new WebDriverScriptExecute(getDriver);
    }

    public abstract void execute(String script, Object[] args);
}
