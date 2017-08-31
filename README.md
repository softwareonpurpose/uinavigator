# uinavigator
UiNavigator provides individual facades to the WebDriver and WebElement functionality of WebDriver.  Host is a singleton which can be initialized with a DriverInstantiation for any WebDriver -- browser or mobile.  Use of Selenium 'By' locators is constrained to the most reliable and resilient (i.e. id, name, class, tag).  Doing so encourages accessing UI elements in composite regions (encapsulated WebElements) resulting in further increased reliability and resilience.  References to WebElements are instantiated just-in-time, only when needed, avoiding Stale Element issues common when references are instantiated on page load.

<b>Browser Drivers</b> - For each browser to be supported, add the appropriate executable (e.g. ChromeDriver.exe, GeckoDriver.exe) to java/main/resources.  DefaultDriverInstantiations are provided for the most common.  <br><b>NO EXECUTABLE BROWSER DRIVERS ARE INCLUDED WITH THIS PACKAGE.</b>  

<b>UiHost</b> - Facade for WebDriver (e.g. browser or device) related functionality (e.g. load(), waitUntilVisible()) provided by WebDriver and WebDriverWait.  The WebDriver.findElement() method is referenced ONLY in this class, and utilized ONLY via the private Element.getElement() method.

<b>UiElement</b> - Facade for WebElement related functionality (e.g. getText(), click())

<b>UiView</b> - Implementation of the 'Page Object' model, providing direct navigation to a view, and user-defined confirmation of state.  
<ul>
<li>Suggest implementing a View interface which includes ONLY "state" methods (e.g. getText(), isActive(), getItemCount(), etc.) and "region" methods (e.g. inHeader(), inContent(), inFooter(), etc.).</li>
<li>Suggest 'action' methods, which result in any change of state (e.g. linkClick(), login(String username), etc.) be implemented in concrete View classes, and always call the expect() method of the resulting concrete View.</li>
<li>Suggest 'in[Region]' methods for returning a region of a given page (e.g. inHeader(), inContet(), inItemList())</li>
</ul>

<b>UiRegion</b> - Encapsulates the model of a "region" of a web page in the same manner UiView models a web page.  This improves the cohesion of the test code AND facilitates reuse for shared regions (e.g. header, footer, sidebar)