package com.softwareonpurpose.uinavigator.driver;

import com.softwareonpurpose.uinavigator.DriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DefaultFirefoxInstantiation extends DriverInstantiation {

    private DefaultFirefoxInstantiation() {
    }

    public static DefaultFirefoxInstantiation getInstance() {
        return new DefaultFirefoxInstantiation();
    }

    @Override
    public WebDriver execute() {
        return new FirefoxDriver();
    }
}
