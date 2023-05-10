package ui.pages;

import authorization.User;
import data.MainCoreDataManager;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import static utils.UrlUtils.buildUrl;
@Slf4j
@Feature("Logging in application via mock endpoint")
public class MockedLoginPage extends BasePage {
    private String mockEndpoint = "dev/login";

    public MockedLoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Logging to application via mock \"dev/login\" with user: {0}")
    public MockedLoginPage loginAs(int userId) {
        log.debug("All cookies deleting");
        driver.manage().deleteAllCookies();
        log.debug("Logging via mock endpoint for user: " + userId);
        driver.get(buildUrl(MainCoreDataManager.getBrowserConfiguration().getApplicationUrl(), mockEndpoint, userId));
        return this;
    }

    public MockedLoginPage loginAs(User user) {
        return loginAs(user.getProfile().getCovenanteeId());
    }
}
