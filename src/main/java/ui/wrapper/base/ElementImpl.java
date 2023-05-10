package ui.wrapper.base;

import data.MainCoreDataManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.driver.MainDriver;
import ui.wrapper.JSCommands;
import ui.wrapper.widget.Label;
import ui.wrapper.widget.impl.LabelImpl;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static ui.wrapper.base.LocatorStringUtils.getFullXpath;

/**
 * An implementation of the Element interface. Delegates its work to an underlying WebElement instance for
 * custom functionality.
 */
@Slf4j
public class ElementImpl implements Element {
    protected final WebDriver driver;
    private final WebElement element;
    private final String elementLocator;
    protected final FluentWait wait;
    protected final JSCommands js;
    protected final LocatorStringUtils.ActionsCommands actionsCommands;

    /**
     * Creates a Element for a given WebElement.
     *
     * @param element element to wrap up
     */
    public ElementImpl(final WebElement element) {
        log.debug("Element initialization started: " + element);
        this.driver = MainDriver.getInstance().getDriver();
        this.element = element;
        this.elementLocator = getFullXpath(this);
        this.actionsCommands = new LocatorStringUtils.ActionsCommands(elementLocator, driver, this.getWrappedElement());
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(MainCoreDataManager.getBrowserConfiguration().timeoutSeconds()))
                .pollingEvery(Duration.of(MainCoreDataManager.getBrowserConfiguration().intervalMilliseconds(), ChronoUnit.SECONDS))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(InterruptedException.class)
                .ignoring(UnknownError.class);
        this.js = new JSCommands(elementLocator, driver, this.getWrappedElement(), wait);
        this.js.waitForJsReady();
        log.debug("Element initialized: " + this.element);
    }

    /**
     * Returns true when the locator is absent on the page, without any wait
     *
     * @return result of checking of an element in the DOM
     */
    public static boolean isElementNotPresentInDOM(String elementLocator) {
        String JS_TEMPLATE = "return document.evaluate(\"%s\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue";
        String executeScript = String.format(JS_TEMPLATE, elementLocator);
        JavascriptExecutor js = (JavascriptExecutor) MainDriver.getInstance().getDriver();
        return js.executeScript(executeScript) == null;
    }

    @Override
    public void click() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.element));
        log.info("Click on element with locator: \"" + this.elementLocator + "\"");
        this.actions().moveToElement();
        this.element.click();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.element));
        log.info("Input text: \"" + Arrays.toString(keysToSend).substring(1, Arrays.toString(keysToSend).length() - 1) + "\" to element with locator: \"" + this.elementLocator + "\"");
        this.actions().moveToElement();
        this.element.sendKeys(keysToSend);
    }

    @Override
    public Point getLocation() {
        Point elementLocation = this.element.getLocation();
        log.info("Getting location for element with locator: \"" + this.elementLocator + "\". Element location: \"" + elementLocation.toString() + "\"");
        return elementLocation;
    }

    @Override
    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(this.element));
        log.info("Submitting element with locator: \"" + this.elementLocator + "\"");
        this.actions().moveToElement();
        this.element.submit();
    }

    @Override
    public String getAttribute(String name) {
        log.info("Getting attribute: \"" + name + "\" for element with locator: \"" + this.elementLocator + "\"");
        this.actions().moveToElement();
        String attributeValue = this.element.getAttribute(name);
        if (attributeValue == null) {
            log.warn("Attribute value for element has no attribute: \"" + name + "\"" + "Element: " + element);
        }
        return attributeValue;
    }

    @Override
    public String getCssValue(String propertyName) {
        log.info("Getting CSS property value: \"" + propertyName + "\" for element with locator: \"" + this.elementLocator + "\"");
        return this.element.getCssValue(propertyName);
    }

    @Override
    public Dimension getSize() {
        log.info("Getting size of element with locator: \"" + this.elementLocator + "\"");
        return this.element.getSize();
    }

    @Override
    public Rectangle getRect() {
        log.info("Getting rect for element with locator: \"" + this.elementLocator + "\"");
        throw new UnsupportedOperationException("getRect() not yet implemented");
    }

    @Override
    public List<WebElement> findElements(By by) {
        log.info("Finding child elements with locator: \"" + by + "\" for parent element with locator: \"" + this.elementLocator + "\" ");
        return this.element.findElements(by);
    }

    @Override
    public String getText() {
        this.actions().moveToElement();
        String text = this.element.getText();
        log.info("Getting text for element with locator: \"" + this.elementLocator + "\". Gotten text: \"" + text + "\"");
        return text;
    }

    @Override
    public String getTagName() {
        this.actions().moveToElement();
        String tag = this.element.getTagName();
        log.info("Getting tag for element with locator: \"" + this.elementLocator + "\". Gotten tag: \"" + tag + "\"");
        return tag;
    }

    @Override
    public boolean isSelected() {
        wait.until(ExpectedConditions.visibilityOf(this.element));
        this.actions().moveToElement();
        boolean isSelected = this.element.isSelected();
        log.info("Checking selected state for element with locator: \"" + this.elementLocator + "\". Is element selected: \"" + isSelected + "\"");
        return isSelected;
    }

    @Override
    public WebElement findElement(By by) {
        log.info("Finding child element with locator: \"" + by + "\" for parent element with locator: \"" + this.elementLocator + "\" ");
        return this.getWrappedElement().findElement(by);
    }

    @Override
    public boolean isEnabled() {
        wait.until(ExpectedConditions.visibilityOf(this.element));
        this.actions().moveToElement();
        boolean isEnabled = this.element.isEnabled();
        log.info("Checking enabled state for element with locator: \"" + this.elementLocator + "\". Is element enabled: \"" + isEnabled + "\"");
        return isEnabled;
    }

    @Override
    public boolean isDisplayed() {
        boolean isDisplayed = this.element.isDisplayed();
        log.info("Checking visibility state for element with locator: \"" + this.elementLocator + "\". Is element displayed: \"" + isDisplayed + "\"");
        return isDisplayed;
    }

    public boolean isVisibleInViewport() {
        return (Boolean) ((JavascriptExecutor) this.driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , this.getWrappedElement());
    }

    @SneakyThrows
    public void scrollToElementInViewport() {
        if (!this.isVisibleInViewport()) {
            ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", this.getWrappedElement());
            Thread.sleep(1000);
            scrollToElementInViewport();
        }
    }

    @Override
    public void clear() {
        log.info("Clearing element with locator: \"" + this.elementLocator + "\"");
        this.actions().moveToElement();
        this.element.clear();
    }

    @Override
    public WebElement getWrappedElement() {
        log.debug("Getting WebElement instance of wrapped element with locator: \"" + this.elementLocator + "\"");
        return this.element;
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
        throw new UnsupportedOperationException("getScreenshotAs() not yet implemented");
    }

    @Override
    public JSCommands js() {
        return this.js;
    }

    @Override
    public LocatorStringUtils.ActionsCommands actions() {
        return this.actionsCommands;
    }

    @Override
    public boolean isActive() {
        boolean isActive = this.getAttribute("class").contains("active");
        log.info("Checking activity state for element with locator: \"" + this.elementLocator + "\". Is element active: \"" + isActive + "\"");
        return isActive;
    }

    @Override
    public String getDataContent() {
        return this.getAttribute("data-content");
    }

    @Override
    public String getValue() {
        return this.getAttribute("value");
    }

    @Override
    public Label getLabel() {
        return new LabelImpl(this.element.findElement(By.xpath(".//ancestor::div[contains(@class,'row')]//label|.//parent::label")));
    }

    @Override
    public Label getError() {
        return new LabelImpl(this.element.findElement(By.xpath(".//ancestor::div[contains(@class,'row')]//div[@class='text-danger' or @class='error']")));
    }

    @Override
    public Label getHint() {
        return new LabelImpl(this.element.findElement(By.xpath(".//ancestor::div[contains(@class,'row')]//a")));
    }

    /**
     * This method is implemented to get "clear" string for some asserts. Our UI sometimes uses a lot of spaces,
     * strange symbols etc, so we need to trim them or replace with one space. Not to store strange strings for assertions.
     *
     * @param str - unclear string
     * @return - clear string
     */
    public static String getClearString(String str) {
        String clearString = str.replaceAll("(\\*|<br>|<br />|<br|/>|«|(:)$)", "")
                .replaceAll(" ", " ")
                .replaceAll(" {2}", " ").trim();
        if (!clearString.equals(str))
            log.debug(String.format("String was cleared from unnecessary symbols:\nPrimary string: \"%s\"\nCleared string:\"%s\"", str, clearString));
        return clearString;
    }

    @Override
    public String getElementLocator() {
        return this.elementLocator;
    }
}