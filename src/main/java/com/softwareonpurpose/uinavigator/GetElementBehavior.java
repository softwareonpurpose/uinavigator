package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;

public abstract class GetElementBehavior {
    protected By locator;

    protected GetElementBehavior(By locator) {
        this.locator = locator;
    }

    protected abstract Object execute();

    @Override
    public String toString() {
        return locator.toString();
    }
}
