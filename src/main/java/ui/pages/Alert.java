package ui.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;

@Slf4j
public class Alert {
    private final WebDriver driver;

    public Alert(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Accepting the alert")
    public void accept() {
        if (isPresent()) {
            log.info("Alert accepting");
            this.driver.switchTo().alert().accept();
            this.driver.switchTo().defaultContent();
        }
    }

    @Step("Dismissing the alert")
    public void dismiss() {
        if (isPresent()) {
            log.info("Alert dismissing");
            this.driver.switchTo().alert().dismiss();
            this.driver.switchTo().defaultContent();
        }
    }

    @Step("Get text from the alert")
    public String getText() {
        String alertText = null;
        if (isPresent()) {
            log.info("Alert getting text");
            alertText = this.driver.switchTo().alert().getText();
        }
        return alertText;
    }

    @Step("Checking if the alert is present")
    public boolean isPresent() {
        log.debug("Checking if alert is present");
        try {
            this.driver.switchTo().alert();
            log.debug("Alert is present");
            return true;
        } catch (NoAlertPresentException noAlertPresentException) {
            log.debug("Alert is not present");
            return false;
        } catch (UnhandledAlertException unhandledAlertException) {
            log.debug("Alert is present");
            return true;
        }
    }
}