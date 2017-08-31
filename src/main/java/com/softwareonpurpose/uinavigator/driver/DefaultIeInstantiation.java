package com.softwareonpurpose.uinavigator.driver;

import com.softwareonpurpose.uinavigator.DriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class DefaultIeInstantiation extends DriverInstantiation {
    public static DefaultIeInstantiation getInstance() {
        return new DefaultIeInstantiation();
    }

    @Override
    protected WebDriver instantiateDriver() {
        System.setProperty("webdriver.ie.driver", "./src/main/resources/IEDriverServer.exe");
        return new InternetExplorerDriver();
    }

    @Override
    protected void configureDriver(WebDriver driver) {
        provideTimeForDriverToLoad();
        driver.manage().timeouts().implicitlyWait(getConfig().getTimeout(), TimeUnit.SECONDS);
    }
}
