package ui.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.apache.commons.io.FileUtils.*;

@Slf4j
public class FileUtils {
    public static final String BUILD_DIR = System.getProperty("user.dir") + "/target";
    public static final String DOWNLOADED_FOLDER_PATH = BUILD_DIR + "/downloaded";

//    @Step("Get downloaded file in downloaded folder: {downloadedFolderPath}")
//    private File getDownloadedFile(File downloadedFolderPath, Pattern pattern) {
//        File downloadedFile;
//        FluentWait<WebDriver> wait = new FluentWait<>(MainDriver.getInstance().getDriver());
//        wait.pollingEvery(Duration.ofSeconds(1));
//        wait.withTimeout(Duration.ofSeconds(20));
//        try {
//            wait.until(x -> downloadedFolderPath.exists());
//            downloadedFile = wait.until(x ->
//                    Arrays.stream(Objects.requireNonNull(downloadedFolderPath.listFiles()))
//                            .filter(fileInDir -> pattern.matcher(fileInDir.getName()).find())
//                            .findFirst()
//                            .map(fileInDir -> new File(String.valueOf(fileInDir)))
//                            .orElse(null)
//            );
//        } catch (TimeoutException e) {
//            throw new TimeoutException("File wasn't found: \n" + e);
//        }
//        return downloadedFile;
//    }

//    @Step("Check that file was downloaded and isn't empty by name or by name and file format")
//    public void checkThatFileDownloadedAndIsNotEmpty(String... parameters) {
//        File folder = new File(DOWNLOADED_FOLDER_PATH);
//        Pattern pattern = parameters.length > 1 ?
//                Pattern.compile("(?=.*" + parameters[0] + ")(?=.*" + parameters[1] + ")") :
//                Pattern.compile("(?=.*" + parameters[0] + ")");
//
//        assertTrue(getDownloadedFile(folder, pattern).length() != 0, "File is empty");
//    }
//
//    @Step("Remove downloaded files by name or by name and format")
//    public void removeDownloadedFile(String... parameters) {
//        File folder = new File(DOWNLOADED_FOLDER_PATH);
//        Pattern pattern;
//        File downloadedFile;
//        pattern = parameters.length > 1 ?
//                Pattern.compile("(?=.*" + parameters[0] + ")(?=.*" + parameters[1] + ")") :
//                Pattern.compile("(?=.*" + parameters[0] + ")");
//        downloadedFile = Arrays.stream(Objects.requireNonNull(folder.listFiles()))
//                .filter(currentFile -> currentFile.isFile() && pattern.matcher(currentFile.getName()).find())
//                .findFirst()
//                .map(currentFile -> new File(String.valueOf(currentFile)))
//                .orElse(null);
//        assertNotNull(downloadedFile, "File isn't exist");
//        assertTrue(downloadedFile.delete(), "File wasn't removed");
//    }
//
//    @SneakyThrows
//    public static void wipeFile(String path) {
//        log.debug("File wiping with path\"" + path + "\"");
//        File file = new File(path);
//        if (file.exists()) {
//            forceDelete(file);
//            touch(file);
//        }
//    }
//
//    @SneakyThrows
//    public static void addFromFileToFile(String pathToFileToBeAppended, String secondFilePath) {
//        log.debug("Appending file \"" + pathToFileToBeAppended + "\" with lines from file: \"" + secondFilePath + "\"");
//        File fileToBeAppended = new File(pathToFileToBeAppended);
//        if (!fileToBeAppended.exists()) {
//            touch(fileToBeAppended);
//            log.debug("File created: " + pathToFileToBeAppended);
//        }
//
//        writeStringAsFile(
//                pathToFileToBeAppended,
//                readFileAsString(secondFilePath),
//                true
//        );
//    }
//
//    @SneakyThrows
//    public static String readFileAsString(String pathToFile) {
//        log.debug("Reading file: \"" + pathToFile + "\" to String");
//        String readString = readFileToString(new File(pathToFile), StandardCharsets.UTF_8);
//        log.debug("Read string length:" + readString.length());
//        return readString;
//    }
//
//    @SneakyThrows
//    public static void writeStringAsFile(String pathToFile, String string, boolean append) {
//        log.debug("Writing file: \"" + pathToFile + "\" The info length: " + string.length());
//        writeStringToFile(new File(pathToFile), string, StandardCharsets.UTF_8, append);
//    }
//
//    @SneakyThrows
//    @Step("Clean folder for downloaded files")
//    public void cleanDownloadedFolder() {
//        log.debug("Wiping folder: \"" + DOWNLOADED_FOLDER_PATH + "\"");
//        cleanDirectory(new File(DOWNLOADED_FOLDER_PATH));
//    }
}
