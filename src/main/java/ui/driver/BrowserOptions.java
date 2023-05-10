package ui.driver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ui.utils.FileUtils;
import java.util.HashMap;
import java.util.Set;

public class BrowserOptions {
    private static final Set<String> OPTION_VALUES;

    static {
        OPTION_VALUES = Set.of(
                "--disable-notifications",
                "--disable-popup-blocking",
                "--start-maximized",
                "--disable-infobars",
                "--disable-extensions",
                "--ignore-certificate-errors",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--remote-allow-origins=*");
    }

    public ChromeOptions getChromeOptions() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", FileUtils.DOWNLOADED_FOLDER_PATH);
        ChromeOptions options = new ChromeOptions();
        OPTION_VALUES.forEach(options::addArguments);
        options.setExperimentalOption("prefs", chromePrefs);
        return options;
    }

    public FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        OPTION_VALUES.forEach(options::addArguments);
        return options;
    }
}
