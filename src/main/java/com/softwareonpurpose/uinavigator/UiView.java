package com.softwareonpurpose.uinavigator;

import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class UiView {
    private final static String ERROR_CONSTRUCTOR_SCOPE =
            "Unable to access View constructor; ensure it is parameter-less and has 'public' scope";
    private final Object viewElement;

    protected UiView(Object viewElement) {
        this.viewElement = viewElement;
    }

    public static <T extends UiView> T expect(Class<T> viewClass) {
        String invalidCharacters =
                String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])");
        final String viewClassName = viewClass.getSimpleName().replaceAll(invalidCharacters, " ");
        LoggerFactory.getLogger("").info(String.format("Expect '%s'", viewClassName));
        T view = construct(viewClass);
        if (view.confirmElementStates()) {
            String messageFormat = "Unable to confirm the state of '%s'";
            String message = String.format(messageFormat, viewClassName);
            LoggerFactory.getLogger("").info(message);
        }
        return construct(viewClass);
    }

    protected static <T extends UiView> T construct(Class<T> viewClass) {
        T view = null;
        try {
            Constructor<T> constructor;
            try {
                constructor = viewClass.getConstructor();
                view = constructor.newInstance();
            } catch (NoSuchMethodException | InvocationTargetException e) {
                LoggerFactory.getLogger(viewClass).error(ERROR_CONSTRUCTOR_SCOPE);
                e.printStackTrace();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            LoggerFactory.getLogger(viewClass).error(ERROR_CONSTRUCTOR_SCOPE);
            e.printStackTrace();
        }
        return view;
    }

    protected abstract Object getElement();

    protected abstract boolean confirmElementStates();
}
