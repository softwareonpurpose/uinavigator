# uinavigator
UiNavigator provides individual proxies to the UI Host and UI Element functionality of WebDrivers.  Host is a singleton which can be initialized with a DriverInstantiation for any WebDriver -- browser or mobile.  Use of By locators is constrained to the most reliable and resilient (i.e. id, name, class, tag).  Doing so encourages accessing UI elements in composite regions (encapsulated WebElements) resulting in further increased reliability and resilience.  By locators for indiivdual Elements are instantiated just-in-time, only when needed, avoiding Stale Element issues common when references are instantiated on page load.

<b>Host</b> - Proxy for any Host (e.g. browser or device) related functionality (e.g. load(), waitUntilVisible()) provided by WebDriver and WebDriverWait.  The WebDriver.findElement() method is referenced ONLY in this class, and utilized ONLY via the private Element.getElement() method.

<b>Element</b> - Encapsulates WebElement related functionality (e.g. getText(), click())

<b>View</b> - Encapsulates direct navigation to a view, and user-defined confirmation of state.  
<ul>
<li>Suggest implementing a View interface which includes ONLY "state" methods (e.g. getText(), isActive(), getItemCount(), etc.) and "region" methods (e.g. inHeader(), inContent(), inFooter(), etc.).</li>
<li>Suggest "action" methods, which result in any change of state (e.g. linkClick(), login(String username), etc.) be implemented in concrete View classes, and always call the expect() method of the resulting concrete View.</li>
</ul>
