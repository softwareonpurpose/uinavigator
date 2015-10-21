package com.softwareonpurpose.uinavigator.driverinstantiation;

import com.softwareonpurpose.uinavigator.DriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxInstantiation implements DriverInstantiation {

    private FirefoxInstantiation() {
    }

    public static FirefoxInstantiation getInstance() {
        return new FirefoxInstantiation();
    }

    @Override
    public WebDriver execute() {
        return new FirefoxDriver();
    }
}
