package ui.wrapper.widget;

import ui.wrapper.base.Element;
import ui.wrapper.base.ImplementedBy;
import ui.wrapper.widget.impl.LabelImpl;

/**
 * Any text block.
 */
@ImplementedBy(LabelImpl.class)
public interface Label extends Element {

    /**
     * Gets the for attribute on the label.
     *
     * @return string containing value of the for attribute, null if empty.
     */
    String getFor();

    String getText();

    String getDataContent();

    void scrollToElementInViewport();
}
