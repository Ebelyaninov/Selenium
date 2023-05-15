package ui.pages;

import data.MainCoreDataManager;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.localization.ErrorMessages;
import ui.localization.LanguageUtils;
import ui.wrapper.base.ElementImpl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static utils.JsonUtil.readJsonObjectFromFile;

@Slf4j
public abstract class BasePage<T> {

    protected final WebDriver driver;
    protected final Actions actions;
    protected final LanguageUtils languageUtils;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor js;
//    @Getter(AccessLevel.NONE)
//    private final ErrorMessages errorMessagesContent;
    private final Alert alert;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.languageUtils = new LanguageUtils();
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(MainCoreDataManager.getBrowserConfiguration().timeoutSeconds()));
        this.alert = new Alert(driver);
        //errorMessagesContent = readJsonObjectFromFile(MainCoreDataManager.getDirectories().data() + "localized.errors.json", ErrorMessages.class);
    }

    protected Alert alert() {
        return this.alert;
    }

    public T submitAlert() {
        this.alert().accept();
        return (T) this;
    }

    public T dismissAlert() {
        this.alert().dismiss();
        return (T) this;
    }

//    public ErrorMessages.ErrorMessage getErrorMessages() {
//        return languageUtils.getContent(errorMessagesContent);
//    }

    protected void waitForLoaderDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(MainCoreDataManager.getBrowserConfiguration().shortTimeoutSeconds()));
        final String loaderLocator = "//*[contains(@class,'template-loading')]";
        try {
            log.debug("Wait for loader to appear...");
            wait.until(x -> !ElementImpl.isElementNotPresentInDOM(loaderLocator));
        } catch (Exception ignored) {
        } finally {
            log.debug("Loaders is present with locator: " + loaderLocator + " : " + !ElementImpl.isElementNotPresentInDOM(loaderLocator));
            wait.until(x -> ElementImpl.isElementNotPresentInDOM(loaderLocator));
        }
    }

    @Step("Refresh page")
    public T refresh() {
        driver.navigate().refresh();
        return (T) this;
    }

    @Step("Navigating to page with URL: \"{0}\"")
    protected T navigate(String url) {
        log.debug("Navigate to URL: \"" + url + "\"");
        driver.get(url);
        return (T) this;
    }

    @Step("Switch to new tab")
    public T switchToNewTab(int tabNumber) {
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(tabNumber));
        return (T) this;
    }

    @Step("Check if there is no error on the page")
    public boolean isErrorMissing() {
        this.waitForLoaderDisappear();
        List<WebElement> errors = driver.findElements(By.xpath("//div[contains(@class,'alert-danger')]"));
        if (errors.size() == 0) return true;
        for (WebElement error : errors) {
            if (error.isDisplayed())
                return false;
        }
        return true;
    }
}
