package ui.wrapper.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import ui.wrapper.JSCommands;
import ui.wrapper.widget.Label;

/**
 * wraps a web element interface with extra functionality. Anything added here will be added to all descendants.
 */
@ImplementedBy()
public interface Element extends WebElement, WrapsElement {
    JSCommands js();

    LocatorStringUtils.ActionsCommands actions();

    boolean isActive();

    String getDataContent();

    String getValue();

    Label getLabel();

    Label getError();

    Label getHint();

    String getElementLocator();
}
