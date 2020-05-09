package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.UiRegion;
import com.softwareonpurpose.uinavigator.UiElement;

public class MockRegion extends UiRegion {
    private MockRegion(UiElement regionElement) {
        super(regionElement);
    }

    public static UiRegion getInstance(UiElement regionElement) {
        return new MockRegion(regionElement);
    }
}
