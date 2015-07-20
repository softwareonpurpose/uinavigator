# uinavigator
Intent of UI Navigator is to encapsulate the driver for any UI host.  Currently BrowserHost is a singleton.  This first implementation supports only the Google Chrome browser.  When support for future hosts is implemented, interfaces and/or abstract classes will be introduced.

<b>BrowserHost</b> - Encapsulates Selenium WebDriver, exposing minimal functionality
<ul>
<li>getInstance()<p>Returns a BrowserHost with an instantiated WebDriver.  The location of the WebDriver executable is defined in chromeDriverPath field.</p></li>
<li>quitInstance()<p>Executes WebDriver.quit() and sets reference to the current driver to null.</p></li>
<li>load(String uri)<p>Navigate the browser to the provided URI.</p></li>
<li>findUiElement(By locator)<p>Returns the first WebElement matching the provided locator.</p></li>
<li>findUiElements(By locator)<p>Returns all WebElements matching the provided locator.</p></li>
<li>waitUntilVisible(By locator)<p>Executes a WebDriverWait for the number of seconds specified by the waitLimit field (currently 13) until the WebElement matching the provided locator is visible.</p></li>
<li>getUri()<p>Returns the current URI from the browser.</p></li>
</ul>

<b>HtmlElement</b> - Encapsulates Selenium WebElement functionality.  Lazy loading of locators used to find WebElements in narrowly scoped parent WebElements mitigates virtually all Stale Element and Element Not Found exceptions.
<ul>
<li>getInstance(String description, String locatorType, String locatorValue)<p>Instantiates a HtmlElement with a By locator of the specified locator type and value.</p></li>
<li>getInstance(String description, String locatorType, String locatorValue, HtmlElement parent)<p>Instantiates a HtmlElement with a By locator of the specified locator type and value found within the parent element.</p></li>
</ul>
