package adminAccountingPages;

import data.MainCoreDataManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.pages.BasePageSignedIn;
import ui.utils.UIEndpoints;
import ui.wrapper.base.Element;
import ui.wrapper.widget.Button;
import ui.wrapper.widget.Select;

import java.util.ArrayList;
import java.util.List;

import static utils.UrlUtils.buildUrl;

public class AccountsListPage extends BasePageSignedIn<AccountsListPage> {
    public AccountsListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "company")
    protected Select company;
    @FindBy(xpath = "//button[@class='btn btn-primary' and @type='submit']")
    protected Button filter;
    @FindBy(xpath = "//button[@class='btn btn-primary' and @type!='submit']")
    protected Button addNewMainLine;
    @FindBy(xpath = "//div[@class='dropdown dotted-dropdown-menu']")
    protected List<Button> menuButtons;
    @FindBy(xpath = "//div[@class='accounts-tree']")
    protected Element tree;
    @FindBy(xpath = "//div[@class='line-name']")
    protected List<Element> rows;
    @FindBy(xpath = "//button[text()='Edit']")
    protected List<Button> editButtons;
    @FindBy(xpath = "//button[text()='Delete']")
    protected List<Button> deleteButtons;
    @FindBy(xpath = "//button[text()='Add new']")
    protected List<Button> addNewButtons;
    @FindBy(xpath = "//h4[@class='modal-title']")
    protected Element modalTitle;
    @FindBy(xpath = "//div[@class='modal-body']")
    protected Element modalBody;
    @FindBy(xpath = "//button[@data-test-id='confirm']")
    protected Button yesButton;
    @FindBy(xpath = "//button[@data-test-id='denyConfirm']")
    protected Button noButton;

    public static final String TITLE = "Accounts list";
    public static final String DELETE_ACCOUNT_MODAL_TITLE = "Delete account";
    public static final String DELETE_ACCOUNT_MODAL_BODY = "Are you sure you want to delete this account?";

    @Step("Navigate to 'Accounts list' page via url")
    public AccountsListPage openAccountingListViaUrl() {
        navigate(buildUrl(MainCoreDataManager.getBrowserConfiguration().getAdminApplicationUrl(), UIEndpoints.adminAccounting.LANGUAGE_EN, UIEndpoints.adminAccounting.ACCOUNTING, UIEndpoints.adminAccounting.ACCOUNTS_LIST, UIEndpoints.adminAccounting.ACCOUNTS_LIST));
        return this;
    }

    @Step("Get companies")
    public List<String> getCompanies() {
        List<String> companyList = new ArrayList<>();
        company.getOptions().forEach(option -> companyList.add(option.getText()));
        return companyList;
    }

    @Step("Select company by partnerName")
    public AccountsListPage selectCompany(String partnerName) {
        company.getOptions().stream()
                .filter(partner -> partner.getText().equals(partnerName)).findFirst().get().click();
        return this;
    }

    @Step("Click on \"Filter\" button")
    public AccountsListPage clickOnFilterButton() {
        filter.click();
        return this;
    }

    @Step("Click on \"Add new main line\" button")
    public AccountsListPage clickOnAddNewMainLineButton() {
        addNewMainLine.click();
        return this;
    }

    @Step("get rows")
    public List<Element> getRows() {
        return this.rows;
    }

    @Step("Get element position")
    public int getElementPosition(String accountName) {
        List<String> listString = new ArrayList<>();
        String text;
        for (int i = 0; i < rows.size(); i++) {
            text = rows.get(i).getText();
            listString.add(text);
            if (text.equals(accountName)) {
                break;
            }
        }
        return listString.indexOf(accountName);
    }

    @Step("Click on \"menu\" button")
    public AccountsListPage clickOnMenu(int element) {
        menuButtons.get(element).click();
        return this;
    }

    @Step("Click on \"Edi\" button")
    public AccountsListPage clickOnEditButton(int element) {
        editButtons.get(element).click();
        return this;
    }

    @Step("Click on \"Delete\" button")
    public AccountsListPage clickOnDeleteButton(int element) {
        deleteButtons.get(element).click();
        return this;
    }

    @Step("Click on \"Add new\" button")
    public AccountsListPage clickOnAddNewButton(int element) {
        addNewButtons.get(element).click();
        return this;
    }

    @Step("Confirm deleting account")
    public AccountsListPage clickOnYesButton() {
        yesButton.click();
        wait.until(ExpectedConditions.visibilityOf(company));
        return this;
    }

    @Step("Click on \"No\" button")
    public AccountsListPage clickOnNoButton() {
        noButton.click();
        return this;
    }

    @Step("Get title of modal window")
    public String getModalTitle() {
        return modalTitle.getText();
    }

    @Step("Get body of modal window")
    public String getModalBody() {
        return modalBody.getText();
    }
}
