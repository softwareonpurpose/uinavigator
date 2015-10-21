package com.softwareonpurpose.uinavigator;

import com.craigstockton.validator4test.Validator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public abstract class TestBase {

    protected void confirm(String result) {
        Assert.assertTrue(result.equals(Validator.PASS), result);
    }

    @AfterMethod(alwaysRun = true)
    protected void terminate() {
        UiHost.quitInstance();
    }

}
