package com.softwareonpurpose.uinavigator.web.mock;

import com.softwareonpurpose.uinavigator.web.WebUiElement;
import com.softwareonpurpose.uinavigator.web.WebUiRegion;

public class MockRegion extends WebUiRegion {
    private MockRegion(WebUiElement regionElement) {
        super(regionElement);
    }

    public static WebUiRegion getInstance(WebUiElement regionElement) {
        return new MockRegion(regionElement);
    }
}
