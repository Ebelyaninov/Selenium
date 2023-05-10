package e2e;

import adminAccountingPages.AccountsListPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import ui.wrapper.factory.api.ElementFactory;

public class BaseTestsUI extends ui.BaseTestUI {
    protected AccountsListPage accountsListPage;

    @BeforeClass(description = "Initializing pages objects", alwaysRun = true)
    public void initPages() {
        accountsListPage = PageFactory.initElements(driver, AccountsListPage.class);
    }
}
