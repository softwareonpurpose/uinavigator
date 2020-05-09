package com.softwareonpurpose.uinavigator;

public class MockRegion extends UiRegion {
    private MockRegion(UiElement regionElement) {
        super(regionElement);
    }

    public static UiRegion getInstance(UiElement regionElement) {
        return new MockRegion(regionElement);
    }
}
