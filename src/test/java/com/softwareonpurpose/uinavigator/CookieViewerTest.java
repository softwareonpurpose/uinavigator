package com.softwareonpurpose.uinavigator;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.Logs;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Test
public class CookieViewerTest {
    @DataProvider
    public static Object[][] scenarios() {
        Date expiry = new Date(LocalDate.now().toEpochDay());
        Cookie nonExistentCookie = new MockCookie("non-existent_name", null, "non-existent_domain", "non-existent_path", expiry);
        Set<Cookie> emptyCookies = new HashSet<>();
        Set<Cookie> oneCookie = new HashSet<>();
        oneCookie.add(new MockCookie("mock_cookie", "cookie value", "mock.domain.com", "\\", expiry));
        Set<Cookie> multipleCookies = new HashSet<>();
        multipleCookies.add(new MockCookie("cookie_1", "value 11", "www.domain_1.com", "\\", expiry));
        multipleCookies.add(new MockCookie("cookie_2", "value 21", "www.domain_1.com", "\\", expiry));
        multipleCookies.add(new MockCookie("cookie_3", "value 31", "www.domain_1.com", "\\", expiry));
        multipleCookies.add(new MockCookie("cookie_1", "value 120", "www.domain_2.com", "\\", expiry));
        multipleCookies.add(new MockCookie("cookie_2", "value 221", "www.domain_2.com", "\\path_1", expiry));
        multipleCookies.add(new MockCookie("cookie_3", "value 321", "www.domain_2.com", "\\path_1", expiry));
        multipleCookies.add(new MockCookie("cookie_1", "value 122", "www.domain_2.com", "\\path_2", expiry));
        multipleCookies.add(new MockCookie("cookie_3", "value 33", "www.domain_3.com", "\\", expiry));
        return new Object[][]{
                {nonExistentCookie.getName(), nonExistentCookie.getDomain(), nonExistentCookie.getPath(), emptyCookies, nonExistentCookie.getValue()},
                {"mock_cookie", "mock.domain.com", "\\", oneCookie, "cookie value"},
                {"cookie_1", "www.domain_1.com", "\\", multipleCookies, "value 11"},
                {nonExistentCookie.getName(), nonExistentCookie.getDomain(), nonExistentCookie.getPath(), multipleCookies, nonExistentCookie.getValue()},
                {"cookie_1", "www.domain_2.com", "\\", multipleCookies, "value 120"},
                {"cookie_1", "www.domain_2.com", "\\path_2", multipleCookies, "value 122"},
                {nonExistentCookie.getName(), "www.domain_1.com", "\\", multipleCookies, null},
                {"cookie_3", nonExistentCookie.getDomain(), "\\", multipleCookies, null},
                {"cookie_3", "www.domain_3.com", nonExistentCookie.getPath(), multipleCookies, null},
                {"cookie_1", "www.domain_3.com", "\\", multipleCookies, null},
                {"cookie_2", "www.domain_2.com", "\\path_2", multipleCookies, null}
        };
    }

    @Test
    public void instantiate() {
        Class expected = CookieViewer.class;
        Class actual = CookieViewer.getInstance(new MockDriver(new MockOptions(new HashSet<>()))).getClass();
        Assert.assertEquals(actual, expected, "FAILED to instantiate CookieViewer");
    }

    @Test(dataProvider = "scenarios")
    public void getCookieValue(String name, String domain, String path, Set<Cookie> cookieScenario, String expected) {
        String actual = CookieViewer.getInstance(new MockDriver(new MockOptions(cookieScenario))).getCookieValue(name, domain, path);
        Assert.assertEquals(actual, expected, "FAILED to return expected cookie value: ");
    }

    private static class MockCookie extends Cookie {
        MockCookie(String name, String value, String domain, String path, Date expiry) {
            super(name, value, domain, path, expiry);
        }
    }

    private class MockDriver implements WebDriver {
        private final Options options;

        MockDriver(Options options) {
            this.options = options;
        }

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
            return options;
        }
    }

    private class MockOptions implements WebDriver.Options {
        private final Set<Cookie> cookies;

        MockOptions(Set<Cookie> cookies) {
            this.cookies = cookies;
        }

        @Override
        public void addCookie(Cookie cookie) {

        }

        @Override
        public void deleteCookieNamed(String name) {

        }

        @Override
        public void deleteCookie(Cookie cookie) {

        }

        @Override
        public void deleteAllCookies() {

        }

        @Override
        public Set<Cookie> getCookies() {
            return cookies;
        }

        @Override
        public Cookie getCookieNamed(String name) {
            return null;
        }

        @Override
        public WebDriver.Timeouts timeouts() {
            return null;
        }

        @Override
        public WebDriver.ImeHandler ime() {
            return null;
        }

        @Override
        public WebDriver.Window window() {
            return null;
        }

        @Override
        public Logs logs() {
            return null;
        }
    }
}
