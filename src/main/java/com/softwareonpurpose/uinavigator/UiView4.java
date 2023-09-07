package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;

import javax.security.auth.callback.ConfirmationCallback;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class UiView4 {
    private final static String ERROR_CONSTRUCTOR_SCOPE =
            "Unable to access View constructor; ensure it is parameter-less and has 'public' scope";
    public static final int TIMEOUT = 3000;
    protected final String VIEW_URL;
    private final UiElement4 VIEW_ELEMENT;

    protected UiView4(String viewUrl, UiElement4 viewElement) {
        VIEW_URL = viewUrl;
        VIEW_ELEMENT = viewElement;
    }


    public static <T extends UiView4> T expect(Class<T> viewClass) {
        final String viewClassName = composeViewClassName(viewClass);
        T view = construct(viewClass);
        if (!view.isDisplayed()) {
            String messageFormat = "Unable to confirm the state of '%s'";
            String message = String.format(messageFormat, viewClassName);
//            Logger logger = LogManager.getLogger(viewClass);
//            logger.info(String.format("Unexpected URL %s", currentUrl));
        }
        return construct(viewClass);
    }

    private static <T extends UiView4> String composeViewClassName(Class<T> viewClass) {
        String invalidCharacters =
                String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])");
        return viewClass.getSimpleName().replaceAll(invalidCharacters, " ");
    }

    private static <T extends UiView4> T construct(Class<T> viewClass) {
        T view = null;
        Constructor<T> constructor;
        try {
            constructor = viewClass.getConstructor();
            view = constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            String message = String.format("%s%n%s", ERROR_CONSTRUCTOR_SCOPE, System.err);
//            LogManager.getLogger(viewClass).error(message);

        }
        return view;
    }

    protected abstract boolean confirmState();

    public boolean isDisplayed() {
        return confirmNavigationToView() && confirmState();
    }

    private boolean confirmNavigationToView() {
        String urlPath = removeProtocol(VIEW_URL);
        boolean isDisplayed = false;
        boolean timeRemaining = true;
        long start = System.currentTimeMillis();
        while (!isDisplayed && timeRemaining) {
            isDisplayed = UiHost4.getInstance().getCurrentUrl().contains(urlPath);
            timeRemaining = System.currentTimeMillis() - start < TIMEOUT;
        }
        return isDisplayed;
    }

    private static String removeProtocol(String urlPath) {
        int startIndex = urlPath.indexOf("/", urlPath.indexOf("/") + 1);
        urlPath = urlPath.substring(startIndex);
        return urlPath;
    }

    protected void load() {
        UiHost4.getInstance().load(VIEW_URL);
    }

    protected UiElement4 getElement() {
        return VIEW_ELEMENT;
    }

    ;
}
