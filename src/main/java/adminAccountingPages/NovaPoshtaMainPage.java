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

    @FindBy(xpath = "//head/title")
    protected WebElement title;

    @Step("Navigate to 'Accounts list' page via url")
    public NovaPoshtaMainPage openMainPage() {
        navigate(buildUrl(MainCoreDataManager.getBrowserConfiguration().getNovaPoshtaUrl()));
        return this;
    }

    //For jenkins parameters
    public NovaPoshtaMainPage openMainPageWithParameters() {
        String url = System.getProperty("URL");
        navigate(buildUrl(url));
        return this;
    }

    @Step("Click on \"reg\"")
    public NovaPoshtaMainPage clickOnReg() {
        reg.click();
        return this;
    }

    @Step("Get main title")
    public String getTtitle() {
        return title.getAttribute("text");
    }
}
