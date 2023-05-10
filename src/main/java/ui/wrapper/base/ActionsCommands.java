package ui.wrapper.base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
public class ActionsCommands {
    private final WebElement element;
    private final Actions actions;
    private final String elementLocator;

    ActionsCommands(String elementLocator, WebDriver driver, WebElement element) {
        this.element = element;
        this.actions = new Actions(driver);
        this.elementLocator = elementLocator;
    }

    public void doubleClick() {
        log.debug("Operation via Actions: doubleClick");
        actions.doubleClick(this.element).build().perform();
    }

    public void moveToElement() {
        log.debug("Operation via Actions: moveToElement");
        if (!this.element.isDisplayed())
            actions.moveToElement(this.element).build().perform();
        log.info("Move to element with locator: \"" + this.elementLocator + "\"");
    }

    public void sendKeys(Object keys) {
        log.debug("Operation via Actions: sendKeys");
        actions.sendKeys(this.element, keys.toString()).build().perform();
        log.debug("Send keys to element with locator: \"" + this.elementLocator + "\"");
    }
}