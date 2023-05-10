package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import ui.driver.MainDriver;
import ui.pages.MockedLoginPage;
import utils.BaseTest;

public class BaseTestUI extends BaseTest {
    protected static final String BLANK = "";
    public static final String SPACES = "      ";

    protected static WebDriver driver;
    protected static MockedLoginPage mockedLoginPage;

    @BeforeMethod(description = "Refreshing page after test", alwaysRun = true)
    public void resetPage() {
        driver.navigate().refresh();
    }

    @BeforeTest(description = "Opening browser", alwaysRun = true)
    public void openBrowser() {
        driver = MainDriver.getInstance().initDriver().getDriver();
        mockedLoginPage = PageFactory.initElements(driver, MockedLoginPage.class);
    }

    @AfterTest(description = "Closing browser", alwaysRun = true)
    public void closeBrowser() {
        MainDriver.getInstance().closeDriver();
    }
}
