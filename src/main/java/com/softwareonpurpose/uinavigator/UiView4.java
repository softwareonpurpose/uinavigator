package com.softwareonpurpose.uinavigator;

import org.apache.logging.log4j.LogManager;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class UiView4 {
    private final static String ERROR_CONSTRUCTOR_SCOPE =
            "Unable to access View constructor; ensure it is parameter-less and has 'public' scope";

    public static <T extends UiView4> T expect(Class<T> viewClass) {
        String invalidCharacters =
                String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])");
        final String viewClassName = viewClass.getSimpleName().replaceAll(invalidCharacters, " ");
        LogManager.getLogger("").info(String.format("Expect '%s'", viewClassName));
        T view = construct(viewClass);
        //  TODO:  Reconsider this logic
        if (!view.confirmState()) {
            String messageFormat = "Unable to confirm the state of '%s'";
            String message = String.format(messageFormat, viewClassName);
            LogManager.getLogger("").info(message);
        }
        return construct(viewClass);
    }

    protected static <T extends UiView4> T construct(Class<T> viewClass) {
        T view = null;
        Constructor<T> constructor;
        try {
            constructor = viewClass.getConstructor();
            view = constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            String message = String.format("%s%n%s", ERROR_CONSTRUCTOR_SCOPE, System.err);
            LogManager.getLogger(viewClass).error(message);

        }
        return view;
    }

    protected abstract boolean confirmState();
}
