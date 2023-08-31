package com.softwareonpurpose.uinavigator;

public class UiHost4 {

    public static UiHost4 getInstance() {
        return new UiHost4();
    }

    public void load(String url) {
        UiNavigator.getDriver().get(url);
    }
}
