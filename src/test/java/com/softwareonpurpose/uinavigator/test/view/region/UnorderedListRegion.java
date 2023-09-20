package com.softwareonpurpose.uinavigator.test.view.region;

import com.softwareonpurpose.uinavigator.UiElement4;
import com.softwareonpurpose.uinavigator.UiLocatorType4;
import com.softwareonpurpose.uinavigator.UiRegion4;

public class UnorderedListRegion extends UiRegion4 {
    public UnorderedListRegion(UiElement4 parent) {
        super(UiElement4.getInstance("Unordered List", UiLocatorType4.TAG, "ul", parent));
    }

    public static UnorderedListRegion getInstance(UiElement4 parent) {
        return new UnorderedListRegion(parent);
    }

    @Override
    protected boolean confirmState() {
        return true;
    }
}
