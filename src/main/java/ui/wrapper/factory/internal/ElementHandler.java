package ui.wrapper.factory.internal;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import ui.wrapper.base.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static ui.wrapper.factory.internal.ImplementedByProcessor.getWrapperClass;

/**
 * Replaces DefaultLocatingElementHandler. Simply opens it up to descendants of the WebElement interface, and other
 * mix-ins of WebElement and Locatable, etc. Saves the wrapping type for calling the constructor of the wrapped classes.
 */
@Slf4j
public class ElementHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<?> wrappingType;

    /**
     * Generates a handler to retrieve the WebElement from a locator for a given WebElement interface descendant.
     *
     * @param interfaceType Interface wrapping this class. It contains a reference the the implementation.
     * @param locator       Element locator that finds the element on a page.
     * @param <T>           type of the interface
     */
    public <T> ElementHandler(Class<T> interfaceType, ElementLocator locator) {
        this.locator = locator;
        if (!Element.class.isAssignableFrom(interfaceType)) {
            throw new RuntimeException("interface not assignable to Element.");
        }

        this.wrappingType = getWrapperClass(interfaceType);
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        final WebElement element;
        try {
            element = locator.findElement();
        } catch (NoSuchElementException e) {
            log.error("Element was not found. No such element with locator: " + locator.toString());
            if ("toString".equals(method.getName())) {
                return "Proxy element for: " + locator.toString();
            }
            throw e;
        }

        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }
        Constructor<?> cons = wrappingType.getConstructor(WebElement.class);
        Object thing = cons.newInstance(element);
        try {
            return method.invoke(wrappingType.cast(thing), objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
}
