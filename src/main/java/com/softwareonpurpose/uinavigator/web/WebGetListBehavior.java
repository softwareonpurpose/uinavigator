package com.softwareonpurpose.uinavigator.web;

import com.softwareonpurpose.uinavigator.GetListBehavior;

import java.util.Collection;

public interface WebGetListBehavior extends GetListBehavior {
    @Override
    Collection<WebUiElement> execute();
}
