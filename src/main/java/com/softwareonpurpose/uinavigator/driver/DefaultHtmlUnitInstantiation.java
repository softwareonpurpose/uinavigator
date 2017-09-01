package com.softwareonpurpose.uinavigator.driver;

import com.softwareonpurpose.uinavigator.DriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class DefaultHtmlUnitInstantiation extends DriverInstantiation {
    public static DefaultHtmlUnitInstantiation getInstance() {
        return new DefaultHtmlUnitInstantiation();
    }

    @Override
    protected WebDriver instantiateDriver() {
        return new HtmlUnitDriver(true);
    }

    @Override
    protected void configureDriver(WebDriver driver) {
    }
}
