package e2e.admin;

import e2e.BaseTestsUI;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

@Slf4j
public class AccountsListTests extends BaseTestsUI {

    private static List<String> partnersList = new ArrayList<>();
    private static String ACCOUNT_ID_CREATED;
    private static final String MAIN_LINE_NAME = "0 MainLineForTesting";
    private static final String ACCOUNT_NAME = "00 AccountForTesting";
    private static final String SUB_ACCOUNT_NAME = "000 SubAccountForTesting";
    private static final String PAYSERA_LT = "Paysera LT";
    private static final String CHOOSE_COMPANY = "Choose company from list";
    private static Integer ELEMENT_POSITION;

    @BeforeClass(description = "Login with user and open the page before tests")
    public void confirmPage() {
//        mockedLoginPage.loginAs(User.ADMIN.getProfile().getCovenanteeId());
        partnersList.add(CHOOSE_COMPANY);
    }

    @BeforeMethod(description = "open accountsList page")
    void openPage() {
        novaPoshtaMainPage.openMainPage();
    }

    @Test(description = "Get partners for account list")
    public void openExample() {
        novaPoshtaMainPage.openExamplePage();
//        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
//        driver.get("https://datatables.net/examples/server_side/row_details.html");
//
//        WebElement tableExample = driver.findElement(By.id("example"));
//        List<WebElement> tableTows = tableExample.findElements(By.cssSelector("tbody > tr[role='row']"));
//        Select select = new Select(driver.findElement(By.name("example_length")));

//        select.selectByValue("25");
//
//        tableTows = new WebDriverWait(driver, Duration.ofNanos(10000), Duration.ofNanos(1000)).until(
//                ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("#example tbody > tr[role='row']"), 25));
//        assertEquals(tableTows.size(), 25);
        String title = "failed";
//        Assert.assertEquals(novaPoshtaMainPage.getTtitle(), title, title);
    }

    @Test(description = "Testing jenkins parameters")
    public void testingJenkinsParameters() {
        String titleFromProperties = System.getProperty("title");
        log.info("LocalVeriable: " + titleFromProperties);
        String title = "Термінова і експрес доставка: транспортно-логістичні послуги в Києві та по всій Україні - служба доставки №1 «Нова пошта»";
        novaPoshtaMainPage.openMainPage();
        Assert.assertEquals(novaPoshtaMainPage.getTtitle(), title, title);
    }

    @Test(description = "Navigate to login page")
    public void navigateToLoginPage() {
        String title = "Бізнес-кабінет - Нова пошта";
        novaPoshtaMainPage.clickOnButton();
        Assert.assertEquals(driver.getTitle(), title, title);
    }

    //Поиск єлемента и в нем поиск другого єлемента
    @Test(description = "Click on cost of delivery")
    public void clikOnCoastOfDelivery() {
        String title = "Вартість доставки - «Нова Пошта»| Доставка майбутнього";
        novaPoshtaMainPage.clickOnLeftMenuItem();
        Assert.assertEquals(driver.getTitle(), title, title);
    }
}
