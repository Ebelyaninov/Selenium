package ui.localization;

import data.MainCoreDataManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import ui.driver.MainDriver;

@Slf4j
@AllArgsConstructor
public class LanguageUtils {
    private final WebDriver driver;

    public LanguageUtils() {
        log.debug(this.getClass().getName() + " class is initialized...");
        driver = MainDriver.getInstance().getDriver();
        log.debug("Driver object is set to: " + MainDriver.getInstance().getDriver());
    }

    public void languageChangeTo(Language language) {
        String currentUrl = driver.getCurrentUrl();
        driver.get(currentUrl.replace(getCurrentLanguage().getUrlPart(), language.getUrlPart()));
    }

    public Language getCurrentLanguage() {
        log.debug("Getting current language...");
        String currentLanguage = driver.getCurrentUrl()
                .substring(MainCoreDataManager.getBrowserConfiguration().getApplicationUrl().length() + 1)
                .substring(0, 2);
        log.debug("Current language is: " + Language.getByUrlPart(currentLanguage));
        return Language.getByUrlPart(currentLanguage);
    }

    public <T extends BaseContent> T getContent(PageContent<T> contentList) {
        if (contentList.getContents() == null) {
            throw new NullPointerException("\nContent for localization check for current language can not be found\n" +
                    "Reasons: \"contents:\" null;");
        }
        T contentForCurrentLanguage;
        if (driver != null) {
            log.debug("Language content getting for language: " + getCurrentLanguage());
            contentForCurrentLanguage = contentList.getContents().stream()
                    .filter(content -> content.getLanguage().equals(getCurrentLanguage()))
                    .findFirst().orElse(null);
        } else {
            log.debug("Language content getting for default english block.");
            contentForCurrentLanguage = contentList.getContents().stream()
                    .filter(content -> content.getLanguage().equals(Language.ENGLISH))
                    .findFirst().orElse(null);
        }
        if (contentForCurrentLanguage == null) {
            throw new NullPointerException("\nContent for localization check for current language can not be found\n" +
                    "Reasons: \"Found content\": null");
        }
        return contentForCurrentLanguage;
    }
}