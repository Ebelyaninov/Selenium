package ui.wrapper;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

@Slf4j
public class JSCommands {
    private final String locator;
    private final WebElement element;
    private final JavascriptExecutor jsExecutor;
    private final FluentWait wait;

    public JSCommands(String locator, WebDriver driver, WebElement element, FluentWait wait) {
        this.locator = locator;
        this.element = element;
        this.jsExecutor = (JavascriptExecutor) driver;
        this.wait = wait;
    }

    public void click() {
        log.info("[JS Operation] Clicking on element with locator:\"" + locator + "\"");
        jsExecutor.executeScript("arguments[0].click();", this.element);
    }

    public void moveToElement() {
        log.info("[JS Operation] Moving to element with locator:\"" + locator + "\"");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", this.element);
    }

    public void moveToTopOfElement() {
        log.info("[JS Operation] Moving to element with locator:\"" + locator + "\"");
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", this.element);
    }


    public void waitForJsReady() {
        Boolean jQueryDefined = (Boolean) jsExecutor.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined) {
            waitForJQueryLoad();
            waitUntilJSReady();
        }
    }

    private void waitUntilJSReady() {
        log.debug("[JS Operation] Wait for Page JS to be loaded.");
        wait.until((ExpectedCondition<Boolean>) driver -> {
            if (jsExecutor.executeScript("return document.readyState").equals("complete")) {
                return Boolean.TRUE;
            }
            return null;
        });
    }

    private void waitForJQueryLoad() {
        log.debug("[JS Operation] Wait for Page JQuery to be loaded.");
        try {
            ExpectedCondition<Boolean> jQueryLoad = driver -> {
                assert driver != null;
                return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
            };
            boolean jqueryReady = (Boolean) jsExecutor.executeScript("return !window.jQuery");
            if (jqueryReady) {
                wait.until(jQueryLoad);
            }
        } catch (JavascriptException ex) {
            log.warn("Jquery not working" + ex.getMessage());
        }
    }
}