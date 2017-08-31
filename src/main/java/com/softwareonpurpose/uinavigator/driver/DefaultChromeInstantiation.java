package com.softwareonpurpose.uinavigator.driver;

import com.softwareonpurpose.uinavigator.DriverInstantiation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DefaultChromeInstantiation extends DriverInstantiation {

    public static DriverInstantiation getInstance() {
        return new DefaultChromeInstantiation();
    }

    @Override
    protected ChromeDriver instantiateDriver() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        return new ChromeDriver();
    }

    @Override
    protected void configureDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(getConfig().getTimeout(), TimeUnit.SECONDS);
    }
}
