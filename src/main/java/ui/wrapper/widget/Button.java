package ui.wrapper.widget;

import ui.wrapper.base.Element;
import ui.wrapper.base.ImplementedBy;
import ui.wrapper.widget.impl.ButtonImpl;

/**
 * Html form button.
 */
@ImplementedBy(ButtonImpl.class)
public interface Button extends Element {

    void click();

    void doubleClick();
}
