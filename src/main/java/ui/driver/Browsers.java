package ui.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static ui.driver.BrowserType.CHROME;
import static ui.driver.BrowserType.FIREFOX;

@Getter
@AllArgsConstructor
public enum Browsers {
    CHROME96(CHROME, "96.0.4664.45"),
    CHROME94(CHROME, "94.0.4606.61"),
    CHROME93(CHROME, "93.0.4577.63"),
    CHROME92(CHROME, "92.0.4515.43"),
    CHROME91(CHROME, "91.0.4472.101"),
    CHROME90(CHROME, "90.0.4430.24"),
    FIREFOX90(FIREFOX, "0.29.0"),
    FIREFOX89(FIREFOX, "0.29.0"),
    FIREFOX88(FIREFOX, "0.29.0"),
    DEFAULT(CHROME, "LATEST");

    private final BrowserType browser;
    private final String version;
}