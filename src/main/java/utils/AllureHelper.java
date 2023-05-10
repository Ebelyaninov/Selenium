package utils;

import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

@Slf4j
public class AllureHelper {

    @Attachment(value = "{name}", type = "text/plain")
    public static String attachTextToReport(String name, String text) {
        log.debug("Attaching file \"" + name + "\" to Allure report");
        return text;
    }

    @Attachment(value = "{name}", type = "application/json")
    public static String attachJSONToReport(String name, String body) {
        log.debug("Attaching file \"" + name + "\" to Allure report");
        return body;
    }

    @Attachment(value = "screenshot.png", type = "image/png")
    public static byte[] takeScreenShot(WebDriver driver) {
        log.debug("Attaching screenshot of failed UI test to Allure report");
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}