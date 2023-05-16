package adminAccountingPages;

import data.MainCoreDataManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;
import ui.pages.BasePageSignedIn;


import static utils.UrlUtils.buildUrl;

public class NovaPoshtaMainPage extends BasePageSignedIn<NovaPoshtaMainPage>  {

    public NovaPoshtaMainPage(WebDriver driver) {
        super(driver);
        }

    @FindBy(xpath = "//a[@class='reg']")
    protected WebElement reg;

    @FindBy(xpath = "//head/title")
    protected WebElement title;

    @FindBy(xpath = "//a[@class='logo_in']")
    protected WebElement button;

    @FindBy(xpath = "//div[@class='block_left']")
    protected WebElement leftSideBar;

    @FindBy(xpath = "//a[@class='cost']")
    protected WebElement coastOfDelivery;

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

    @Step("Click on button")
    public NovaPoshtaMainPage clickOnButton() {
         button.click();
         return this;
    }

    @Step("Find leftSideBar")
    public WebElement getLeftSideBat() {
        return leftSideBar;
    }

    @Step("Click on element on leftSide bar")
    public NovaPoshtaMainPage clickOnLeftMenuItem() {
        getLeftSideBat().findElement(By.xpath("//a[@class='cost']")).click();
        return this;
    }
}
