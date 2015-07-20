# uinavigator
Intent of UI Navigator is to encapsulate the driver for any UI host.  Currently BrowserHost is a singleton.  This first implementation supports only the Google Chrome browser.  When support for future hosts is implemented, interfaces and/or abstract classes will be introduced.
<b>BrowserHost</b> - Encapsulates Selenium WebDriver, exposing minimal functionality
<b>HtmlElement</b> - Encapsulates Selenium WebElement functionality.  Lazy loading of locators used to find WebElements in 
