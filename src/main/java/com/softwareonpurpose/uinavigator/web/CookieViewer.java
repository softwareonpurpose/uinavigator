package com.softwareonpurpose.uinavigator.web;
/*
  Copyright 2020 - 2022 Craig A. Stockton
  <p/>
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  <p/>
  http://www.apache.org/licenses/LICENSE-2.0
  <p/>
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
 */

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class CookieViewer {

    private final WebDriver driver;

    private CookieViewer(WebDriver driver) {
        this.driver = driver;
    }

    public static CookieViewer getInstance(WebDriver driver) {
        return new CookieViewer(driver);
    }

    public String getCookieValue(String name, String domain, String path) {
        Set<Cookie> candidates = driver.manage().getCookies();
        for (Cookie candidate : candidates) {
            if (candidate.getPath().equals(path) && candidate.getDomain().equals(domain) && candidate.getName().equals(name)) {
                return candidate.getValue();
            }
        }
        for (Cookie candidate : candidates) {
            if (candidate.getDomain().equals(domain) && candidate.getName().equals(name)) {
                return candidate.getValue();
            }
        }
        for (Cookie candidate : candidates) {
            if (candidate.getName().equals(name)) {
                return candidate.getValue();
            }
        }
        return null;
    }

    public String getCookieValue(String name) {
        return getCookieValue(name, null, null);
    }

    public String getCookieValue(String name, String domain) {
        return getCookieValue(name, domain, null);
    }
}
