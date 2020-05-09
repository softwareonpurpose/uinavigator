package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiRegion;

public class MockRegion extends UiRegion {
    private MockRegion(WebUiElement regionElement) {
        super(regionElement);
    }

    public static UiRegion getInstance(WebUiElement regionElement) {
        return new MockRegion(regionElement);
    }
}
