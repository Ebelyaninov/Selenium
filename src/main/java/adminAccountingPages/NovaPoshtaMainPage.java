package adminAccountingPages;

import data.MainCoreDataManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.pages.BasePageSignedIn;

import static utils.UrlUtils.buildUrl;

public class NovaPoshtaMainPage extends BasePageSignedIn<NovaPoshtaMainPage> {

    public NovaPoshtaMainPage(WebDriver driver) {
        super(driver);
        }

    @FindBy(xpath = "//a[@class='reg']")
    protected WebElement reg;

    @Step("Navigate to 'Accounts list' page via url")
    public NovaPoshtaMainPage openMainPage() {
        navigate(buildUrl(MainCoreDataManager.getBrowserConfiguration().getNovaPoshtaUrl()));
        return this;
    }

    @Step("Click on \"reg\"")
    public NovaPoshtaMainPage clickOnReg() {
        reg.click();
        return this;
    }
}
