package ui.wrapper.widget.impl;

import org.openqa.selenium.WebElement;
import ui.wrapper.base.ElementImpl;
import ui.wrapper.widget.Label;

/**
 * Wraps a label on a html form with some behavior.
 */
public class LabelImpl extends ElementImpl implements Label {

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public LabelImpl(WebElement element) {
        super(element);
    }

    @Override
    public String getFor() {
        return super.getAttribute("for");
    }

    @Override
    public String getText() {
        return getClearString(super.getText());
    }

    @Override
    public String getDataContent() {
        return getClearString(super.getAttribute("data-content"));
    }
}
