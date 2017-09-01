package com.softwareonpurpose.uinavigator.driver;

import com.softwareonpurpose.uinavigator.DriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DefaultFirefoxInstantiation extends DriverInstantiation {

    private DefaultFirefoxInstantiation() {
    }

    public static DefaultFirefoxInstantiation getInstance() {
        return new DefaultFirefoxInstantiation();
    }

    @Override
    protected WebDriver instantiateDriver() {
        System.setProperty("webdriver.gecko.driver", "./src/main/resources/geckodriver.exe");
        return new FirefoxDriver();
    }

    @Override
    protected void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(getConfig().getTimeout(), TimeUnit.SECONDS);
    }
}
