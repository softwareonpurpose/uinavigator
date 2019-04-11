package com.softwareonpurpose.uinavigator;

import com.softwareonpurpose.calibrator4test.Calibrator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public abstract class TestBase {
    void confirm(String result) {
        Assert.assertEquals(Calibrator.SUCCESS, result);
    }

    @AfterMethod(alwaysRun = true)
    protected void terminate() {
        UiHost.quitInstance();
    }

}
