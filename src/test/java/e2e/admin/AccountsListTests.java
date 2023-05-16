package e2e.admin;

import e2e.BaseTestsUI;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

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
    public void accountingListCompany() {
//        List<String> companyListFromUI = accountsListPage.getCompanies();
//        assertEquals(accountsListPage.getPageTitle(), AccountsListPage.TITLE, Messages.ui.correctContentHeader());
//        companyListFromUI.forEach((items) -> items.equals(partnersList.get(partnersList.indexOf(items))));
    }

    @Test(description = "Testing jenkins parameters")
    public void testingJenkinsParameters() {
        String titleFromProperties = System.getProperty("title");
        String title = "Термінова і експрес доставка: транспортно-логістичні послуги в Києві та по всій Україні - служба доставки №1 «Нова пошта»";
        novaPoshtaMainPage.openMainPage();
        Assert.assertEquals(novaPoshtaMainPage.getTtitle(), title, title);
    }

    @Test(description = "Navigate to login page")
    public void navigateToLoginPage() {
        String titleFromProperties = System.getProperty("title");
        String title = "Бізнес-кабінет - Нова пошта";
        novaPoshtaMainPage.clickOnButton();
        Assert.assertEquals(driver.getTitle(), title, title);
    }
}
