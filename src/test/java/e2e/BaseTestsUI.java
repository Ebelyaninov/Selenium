package e2e;

import adminAccountingPages.NovaPoshtaMainPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;

public class BaseTestsUI extends ui.BaseTestUI {
    protected NovaPoshtaMainPage novaPoshtaMainPage;

    @BeforeClass(description = "Initializing pages objects", alwaysRun = true)
    public void initPages() {
        novaPoshtaMainPage = PageFactory.initElements(driver, NovaPoshtaMainPage.class);
    }
}
