package utils;

import data.MainCoreDataManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.driver.MainDriver;
import ui.localization.ErrorMessages;
import ui.localization.LanguageUtils;

import java.io.File;
import java.lang.reflect.Method;

import static ui.utils.FileUtils.*;
import static utils.JsonUtil.readJsonObjectFromFile;
@Slf4j
public class BaseTest {

    private final String temporaryLogsFilePath = BUILD_DIR + "/test.log";
    private String permanentlyLogsFilePath;
//    @Getter(AccessLevel.NONE)
//    private final ErrorMessages errorMessagesContent = readJsonObjectFromFile(MainCoreDataManager.getDirectories().data() + "localized.errors.json", ErrorMessages.class);

    @BeforeMethod(description = "Logging start", alwaysRun = true)
    public void beforeTestGlobal(Method method) {
        startTemporaryLogsFile(method);
    }

    @AfterMethod(description = "Logs gathering stage", alwaysRun = true)
    public void processingTestLogs(ITestResult result) {
        if (result.getStatus() != ITestResult.SUCCESS) {
            attachScreenshotToUITest();
//            logMainContainers();
//            moveLogsToPermanentFile(result);
        }
//        clearingTemporaryLogFile();
    }

    private void startTemporaryLogsFile(Method method) {
        permanentlyLogsFilePath = BUILD_DIR + "/" + method.getName() + ".log.txt";
        log.info("Test started: " + method.getName());
    }

    @Step
    private void attachScreenshotToUITest() {
        WebDriver currentUIDriver = MainDriver.getInstance().getDriver();
        if (currentUIDriver != null) {
            AllureHelper.takeScreenShot(currentUIDriver);
        }
    }

//    private void moveLogsToPermanentFile(ITestResult result) {
//        addFromFileToFile(permanentlyLogsFilePath, temporaryLogsFilePath);
//        AllureHelper.attachTextToReport(result.getName() + ".log.txt", readFileAsString(permanentlyLogsFilePath));
//    }
//
//    private void clearingTemporaryLogFile() {
//        log.info("Clearing logs files before test.");
//        wipeFile(temporaryLogsFilePath);
//    }

//    private void logMainContainers() {
//        log.debug("Logging main containers error logs:");
//        File logsScript = new File("../get-logs.sh");
//        log.debug("Logs from containers " + executeCommand(logsScript.getAbsolutePath()));
//    }

//    public ErrorMessages.ErrorMessage getErrorMessages() {
//        return new LanguageUtils().getContent(errorMessagesContent);
//    }
}
