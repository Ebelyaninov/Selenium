package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.elements.navigation.MenuOption;
import ui.localization.Language;
import ui.wrapper.base.Element;
import ui.wrapper.base.ElementImpl;
import ui.wrapper.widget.Label;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Slf4j
public abstract class BasePageSignedIn<T extends BasePage> extends BasePage<T> implements Page<BasePageSignedIn> {
//    @FindBy(xpath = "//h1[contains(@class,'page-title')]")
//    protected Label header;
//    @FindBy(xpath = "//footer")
//    protected Label footer;
//    @FindBy(xpath = "//div[@data-field-error-container]/div")
//    protected Label errorTextOnField;
//    @FindBy(xpath = "//div[@data-field-error-container]/div")
//    protected List<Label> errorsTextOnFieldList;
//    @FindBy(xpath = "//li[@class='current-user']//b")
//    protected Label currentUserName;
//    @FindBy(xpath = "//li[@class='current-user']//span[contains(@class,'current-user-id')]")
//    protected Label currentUserCovenanteeId;

    public BasePageSignedIn(WebDriver driver) {
        super(driver);
    }

    @Step("Pressing menu option: \"{0}\"")
    public T goToMenuOption(MenuOption option) {
        Element menuOption = new ElementImpl(wait.until(ExpectedConditions.visibilityOfElementLocated(getMenuOption(option))));
        if (!menuOption.isActive()) {
            menuOption.click();
        }
        return (T) this;
    }

    @Step("collapse menu option: \"{0}\"")
    public T collapseMenuOption(MenuOption option) {
        Element menuOption = new ElementImpl(wait.until(ExpectedConditions.visibilityOfElementLocated(getMenuOption(option))));
        if (menuOption.isActive()) {
            menuOption.click();
        }
        return (T) this;
    }

    @Step("Navigating to page with URL: \"{0}\"")
    protected T navigate(String url) {
        log.debug("Navigate to URL: \"" + url + "\"");
        driver.get(url);
        return (T) this;
    }

//    @Step("Getting logged in user covenantee id")
//    protected Integer getCurrentUserId() {
//        return Integer.valueOf(currentUserCovenanteeId.getText());
//    }

//    @Step("Getting logged in user covenantee name")
//    protected String getCurrentUserName() {
//        return currentUserName.getText();
//    }

    private By getMenuOption(MenuOption option) {
        return By.xpath(String.format("//span[text()='%s']//parent::a", option.getTitle()));
    }

    @Step("Language change to: \"{0}\"")
    public T languageChange(Language language) {
        languageUtils.languageChangeTo(language);
        return (T) this;
    }

//    @Step("Validate that only error on the page is: \"{0}\"")
//    public T validateOnlyError(String errorString) {
//        wait.until(ExpectedConditions.visibilityOf(errorTextOnField));
//        assertEquals(errorsTextOnFieldList.size(), 1, "Check is only one error is present on page");
//        wait.until(ExpectedConditions.textToBePresentInElement(errorTextOnField, errorString));
//        return (T) this;
//    }

//    @Step("Validate that first error on the page is: \"{0}\"")
//    public T validateFirstError(String errorString) {
//        wait.until(ExpectedConditions.visibilityOf(errorTextOnField));
//        wait.until(ExpectedConditions.textToBePresentInElement(errorTextOnField, errorString));
//        return (T) this;
//    }

//    @Step("Validate that all \"{1}\" error on the page is: \"{0}\"")
//    public T validateAllErrorsHasSameTextError(String errorString, int numberOfErrors) {
//        wait.until(ExpectedConditions.visibilityOf(errorTextOnField));
//        assertEquals(errorsTextOnFieldList.size(), numberOfErrors, "Amount of errors on page is incorrect: ");
//        assertTrue(errorsTextOnFieldList.stream().allMatch(element -> element.getText().equals(errorString)),
//                "All errors do not have same text: ");
//        return (T) this;
//    }

    @Step("Switch to new tab")
    public T switchToNewTab(int tabNumber) {
        List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(tabNumber));
        return (T) this;
    }

//    @Step("Get the title of the opened page")
//    public String getPageTitle() {
//        return header.getText();
//    }

    @Step("Get an array of parts of a string splitted by spaces")
    public String[] getArrayPartsString(String text) {
        return text.split(" ");
    }
}
