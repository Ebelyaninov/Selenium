package ui.wrapper.widget.impl;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.wrapper.base.ElementImpl;
import ui.wrapper.widget.Button;

/**
 * Wraps a button on a html form with some behavior.
 */
@Slf4j
public class ButtonImpl extends ElementImpl implements Button {

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public ButtonImpl(WebElement element) {
        super(element);
    }

    @Override
    public void click() {
        wait.until(ExpectedConditions.elementToBeClickable(this.getWrappedElement()));
        super.click();
    }

    public void doubleClick() {
        log.info("Double click on element with locator: \"" + this.getElementLocator() + "\"");
        wait.until(ExpectedConditions.elementToBeClickable(this.getWrappedElement()));
        this.actionsCommands.doubleClick();
    }
}