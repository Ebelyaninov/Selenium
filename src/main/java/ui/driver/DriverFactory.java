package ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    BrowserOptions browserOptions = new BrowserOptions();

    private void globalConfig() {
        ProxyManager.setUpProxy();
    }

    public ChromeDriver getChromeDriver(String version) {
        globalConfig();
        if (!version.equals(Browsers.DEFAULT.getVersion()))
            WebDriverManager.chromedriver().browserVersion(version).setup();
        else
            WebDriverManager.chromedriver().setup();
        return new ChromeDriver(browserOptions.getChromeOptions());
    }

    public FirefoxDriver getFirefoxDriver(String version) {
        globalConfig();
        WebDriverManager.firefoxdriver().browserVersion(version).setup();
        return new FirefoxDriver(browserOptions.getFireFoxOptions());
    }

    public SafariDriver getSafariDriver() {
        globalConfig();
        return new SafariDriver();
    }
}
