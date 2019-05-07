package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

@Test
public class CookieViewerTest {
    @Test
    public void instantiate(){
        Class expected = CookieViewer.class;
        Class actual = CookieViewer.getInstance(new MockDriver()).getClass();
        Assert.assertEquals(actual, expected, "FAILED to instantiate CookieViewer");
    }

    @Test
    public void getCookieValue(){
        String testValue = "cookie value";
        String expected = testValue;
        String path = "\\";
        String domain = "mock.domain.com";
        String cookieName = "mock_cookie";
        String actual = CookieViewer.getInstance(new MockDriver()).getCookieValue(cookieName, domain, path);
        Assert.assertEquals(actual, expected, "FAILED to return expected cookie value");
    }

    private class MockDriver implements WebDriver {
        @Override
        public void get(String url) {

        }

        @Override
        public String getCurrentUrl() {
            return null;
        }

        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public List<WebElement> findElements(By by) {
            return null;
        }

        @Override
        public WebElement findElement(By by) {
            return null;
        }

        @Override
        public String getPageSource() {
            return null;
        }

        @Override
        public void close() {

        }

        @Override
        public void quit() {

        }

        @Override
        public Set<String> getWindowHandles() {
            return null;
        }

        @Override
        public String getWindowHandle() {
            return null;
        }

        @Override
        public TargetLocator switchTo() {
            return null;
        }

        @Override
        public Navigation navigate() {
            return null;
        }

        @Override
        public Options manage() {
            return null;
        }
    }
}
