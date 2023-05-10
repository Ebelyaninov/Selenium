package ui.driver;

import data.MainCoreDataManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ui.driver.BrowserType.FIREFOX;
import static ui.driver.BrowserType.SAFARI;
import static ui.driver.Browsers.DEFAULT;
@Slf4j
public class MainDriver {
    private static final ThreadLocal<MainDriver> driverThread = new ThreadLocal<>();
    private WebDriver driver;
    private final DriverFactory driverFactory;

    private MainDriver() {
        this.driverFactory = new DriverFactory();
    }

    public static MainDriver getInstance() {
        if (driverThread.get() == null) {
            synchronized (MainDriver.class) {
                driverThread.set(new MainDriver());
            }
        }
        return driverThread.get();
    }

    @SneakyThrows
    public MainDriver initDriver() {
        String browserType = System.getProperty("browser");
        log.info(String.format("Browser from property 'browser' has value: %s", browserType));
        Browsers browser = Arrays.stream(Browsers.values()).filter(item -> item.name().equalsIgnoreCase(
                browserType)).findFirst().orElse(DEFAULT);

        if (FIREFOX.equals(browser.getBrowser())) {
            driver = driverFactory.getFirefoxDriver(browser.getVersion());
        } else if (SAFARI.equals(browser.getBrowser())) {
            driver = driverFactory.getSafariDriver();
        } else {
            driver = driverFactory.getChromeDriver(browser.getVersion());
        }
        driver.manage().timeouts().implicitlyWait(MainCoreDataManager.getBrowserConfiguration().timeoutSeconds(),
                TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return this;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
}
