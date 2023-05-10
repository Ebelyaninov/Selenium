package messages.ui;

import io.qameta.allure.Step;

public class UIAssertMessages {
    @Step("Check is opened page has correct content header")
    public String correctContentHeader() {
        return "Checked header is incorrect ";
    }

    @Step("Check if correct text is shown")
    public String wrongTextDisplayed() {
        return "Wrong text is shown";
    }

    @Step("Check if label has correct text")
    public String labelEquals() {
        return "Checked label has incorrect text: ";
    }

    @Step("Check if hint has correct text")
    public String hintEquals() {
        return "Checked hint has incorrect text: ";
    }

    @Step("Check if error has correct text")
    public String errorEquals() {
        return "Checked error message has incorrect text: ";
    }

    @Step("Check if error has correct amount")
    public String errorAmount() {
        return "Checked error message amount has incorrect counter: ";
    }

    @Step("Check if {element} was enabled")
    public String elementIsEnabled(String element) {
        return String.format("%s was not enabled", element);
    }

    @Step("Check if {element} is displayed")
    public String elementIsDisplayed(String element) {
        return String.format("%s was not displayed", element);
    }

    @Step("Check if {element} is not displayed")
    public String elementIsNotDisplayed(String element) {
        return String.format("%s was displayed", element);
    }

    @Step("Check if {element} is selected")
    public String elementIsSelected(String element) {
        return String.format("%s was not selected", element);
    }

    @Step("Check if {element} is not selected")
    public String elementIsNotSelected(String element) {
        return String.format("%s was selected", element);
    }

    @Step("Check table is not empty")
    public String tableIsNotEmpty() {
        return "Table supposed to be not empty, but found to be empty.";
    }

    @Step("Check table is empty")
    public String tableIsEmpty() {
        return "Table supposed to be empty, but found not to be empty.";
    }

    @Step("Check that all items are checked in list")
    public String allItemsChecked() {
        return "List items supposed to be checked, but found not to be checked.";
    }

    @Step("Check that no items are checked in list")
    public String noItemsChecked() {
        return "List items supposed not to be checked, but found to be checked.";
    }

    @Step("Check that items in list are filtered correctly")
    public String filteredCorrectly() {
        return "List items supposed to have filtered items, but found not to be filtered properly.";
    }

    @Step("Check that item or its part created correctly")
    public String createdCorrectly() {
        return "Item or its part supposed to be created correctly, but found not equals to compared object.";
    }

    @Step("Check if the compared elements are equal")
    public String elementsNotEquals() {
        return "Compared elements supposed to be equals, but actually elements are not equals.";
    }

    @Step("Check if the compared elements are not equal")
    public String elementsEquals() {
        return "Compared elements supposed not to be equals, but actually elements are equals.";
    }

    @Step("Check if there is no error present on the screen")
    public String noError() {
        return "No error supposed to be on the screen but some error was present. Test final result can not be true.";
    }
}
