package ui.wrapper.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Slf4j
public class LocatorStringUtils {
    public static String[] getElementLogArray(WebElement element) {
        String elementPointerChars = "->";
        String elementSpaceChars = " ";

        //Getting information about primary element => [locatorType, locator]
        String[] arrayOfLogs = element.toString()
                .substring(element.toString().indexOf(elementPointerChars) + 3)
                .split(elementSpaceChars, 2);

        String locator = arrayOfLogs[1];
        if (locator.contains(elementPointerChars)) {
            arrayOfLogs[1] = locator.substring(0, locator.indexOf(elementPointerChars) - 3);
        }

        //Appending array with all child element => [locatorType, locator, ..., ...]
        while (locator.contains(elementPointerChars)) {
            String[] internalElementLogsArray = locator
                    .substring(locator.indexOf(elementPointerChars) + 3)
                    .split(elementSpaceChars, 2);
            locator = internalElementLogsArray[1];

            arrayOfLogs = ArrayUtils.addAll(arrayOfLogs, internalElementLogsArray);
        }

        for (int i = 0; i < arrayOfLogs.length; i += 2) {
            arrayOfLogs[i] = arrayOfLogs[i].substring(0, arrayOfLogs[i].length() - 1);
            arrayOfLogs[i + 1] = arrayOfLogs[i + 1].substring(0, arrayOfLogs[i + 1].length() - 1);
        }
        return arrayOfLogs;
    }

    public static String logElementLocation(Element element) {
        String[] elementInfoArray = getElementLogArray(element.getWrappedElement());
        String locatorType = elementInfoArray[elementInfoArray.length - 2];
        String locator = elementInfoArray[elementInfoArray.length - 1];
        if (elementInfoArray.length == 2) {
            String returnString = "Trying to access element with locator \"" + locatorType + "\": \"" + locator + "\"";
            log.info(returnString);
            return returnString;
        } else {
            StringBuilder stringBuilder = new StringBuilder()
                    .append("Trying to access child element with locator \"")
                    .append(locatorType)
                    .append("\": \"")
                    .append(locator)
                    .append("\"\nFull elements hierarchy: Primary element: \"")
                    .append(elementInfoArray[0])
                    .append("\" -> \"")
                    .append(elementInfoArray[1]);


            for (int i = 2; i < elementInfoArray.length; i += 2) {
                stringBuilder.append("\" -> \n")
                        .append("\t\t\t\t\t\t\t\t\t\t\t\t")
                        .append(elementInfoArray[i])
                        .append("\" -> \"")
                        .append(elementInfoArray[i + 1]);
            }
            log.info(stringBuilder.toString());
            return stringBuilder.toString();
        }
    }

    public static String getFullXpath(Element element) {
        String[] elementLogArray = getElementLogArray(element.getWrappedElement());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < elementLogArray.length; i += 2) {
            switch (elementLogArray[i]) {
                case "id":
                    stringBuilder.append("//*[@id='").append(elementLogArray[i + 1]).append("']");
                    break;
                case "xpath":
                    stringBuilder.append(elementLogArray[i + 1]);
                    break;
                case "name":
                    stringBuilder.append("//*[@name='").append(elementLogArray[i + 1]).append("']");
                    break;
            }
        }
        return stringBuilder.toString();
    }

    @Slf4j
    public static class ActionsCommands {
        private final WebElement element;
        private final Actions actions;
        private final String elementLocator;

        ActionsCommands(String elementLocator, WebDriver driver, WebElement element) {
            this.element = element;
            this.actions = new Actions(driver);
            this.elementLocator = elementLocator;
        }

        public void doubleClick() {
            log.debug("Operation via Actions: doubleClick");
            actions.doubleClick(this.element).build().perform();
        }

        public void moveToElement() {
            log.debug("Operation via Actions: moveToElement");
            if (!this.element.isDisplayed())
                actions.moveToElement(this.element).build().perform();
            log.info("Move to element with locator: \"" + this.elementLocator + "\"");
        }

        public void sendKeys(Object keys) {
            log.debug("Operation via Actions: sendKeys");
            actions.sendKeys(this.element, keys.toString()).build().perform();
            log.debug("Send keys to element with locator: \"" + this.elementLocator + "\"");
        }
    }
}