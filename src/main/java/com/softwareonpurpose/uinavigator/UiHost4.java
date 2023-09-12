package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;

public class UiHost4 {
//    private static Logger logger;
    private final ChromeDriver driver;

    private UiHost4() {
        driver = UiNavigator.getInstance().getDriver();
    }

    public static UiHost4 getInstance() {
        return new UiHost4();
    }

    public void load(String url) {
//        getLogger().info(String.format("Navigate to %s", url));
        driver.get(url);
    }

//    private Logger getLogger() {
//        if (logger == null) {
//            logger = LogManager.getLogger("");
//        }
//        return logger;
//    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
