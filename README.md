# uinavigator
Intent of UI Navigator is to encapsulate the driver for any UI host.  This first implementation supports only the Google Chrome browser.  When support for future hosts is implemented, interfaces and/or abstract classes will be introduced.

<b>BrowserHost</b>
Encapsulates Selenium WebDriver, exposing minimal functionality
<ul>
<li><b>getInstance()</b><p>Returns a BrowserHost with an instantiated WebDriver.  The location of the WebDriver executable is defined in chromeDriverPath field.</p>
</ul>
