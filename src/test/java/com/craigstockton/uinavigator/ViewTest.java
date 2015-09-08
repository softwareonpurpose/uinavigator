package com.craigstockton.uinavigator;

import com.craigstockton.uinavigator.view.GoogleLandingView;
import com.craigstockton.uinavigator.view.GoogleLandingViewExpected;
import org.testng.annotations.Test;

@Test
public class ViewTest extends TestBase {

    @Test( //*//
            groups = "under_development"
            //*///
    )
    public void directNav() {
        GoogleLandingViewExpected expected = GoogleLandingViewExpected.compose();
        GoogleLandingView actual = GoogleLandingView.directNav();
        confirm(expected.validate(actual));
    }
}
