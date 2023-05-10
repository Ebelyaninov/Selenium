package messages.api;

import io.qameta.allure.Step;

public class APIMessages {
    public CurrencyExchangeMessages currencyExchange = new CurrencyExchangeMessages();

    public String filterLimit() {
        return getMessageFilter("limit");
    }

    public String filterOffset() {
        return getMessageFilter("offset");
    }

    public String filterOrderBy() {
        return getMessageFilter("order_by");
    }

    public String filterOrderDirection() {
        return getMessageFilter("order_direction");
    }

    public String filterById() {
        return getMessageFilter("id");
    }

    public String filterTitle() {
        return getMessageFilter("title");
    }

    public String filterName() {
        return getMessageFilter("name");
    }

    @Step("Getting items list from response. Checking if list of resources gotten from endpoint is not empty")
    public String itemsNotEmpty() {
        return "Item list in response is supposed to be not empty, but actually contains no items.";
    }

    @Step("Asserting that all required parameters of list objects are not null")
    public String itemsRequiredParametersNotNull() {
        return "Item required parameters are supposed to be not null.";
    }

    @Step("Getting items list from response. Checking if list of resources gotten from endpoint is empty")
    public String itemsEmpty() {
        return "Item list in response is supposed to be empty, but actually contains items.";
    }

    @Step("Tests is supposed to face forbidden error message. Test uses Authorization tokens with not all necessary scopes present in token. Used scopes: [\"logged_in\",\"confirmed_log_in\"].")
    public String forbiddenUser() {
        return "User is supposed forbidden for this operation. But response contains no forbidden error.";
    }

    @Step("Operation is forbidden for executing.")
    public String forbiddenOperation() {
        return "Operation is supposed to be forbidden.";
    }

    @Step("Check that forbidden operation does not affected response.")
    public String operationWasNotExecuted() {
        return "Forbidden operation was executed and affected system.";
    }

    @Step("Tests is supposed to face unauthorized error message. Test uses incorrect Bearer token value.")
    public String unauthorizedUser() {
        return "User is supposed unauthorized for this operation. But response contains no unauthorized error.";
    }

    @Step("Test is suppose to create an object and check if it is created.")
    public String resourceCreated() {
        return "Resource is supposed to be created.";
    }

    @Step("Test is suppose to update an object and check if it is updated.")
    public String resourceUpdated() {
        return "Resource is supposed to be updated.";
    }

    @Step("Test is suppose to get resource by id.")
    public String resourceGettingById() {
        return "Resource is supposed to be gotten by id.";
    }

    @Step("Test is suppose to check if response body has key.")
    public String containsJsonKey() {
        return "JSON body structure do not contain all needed keys.";
    }

    @Step("Test is suppose to getting list of resources.")
    public String resourceGetting() {
        return "Resource list is supposed to be gotten.";
    }

    @Step("Checking if resource is not found after delete")
    public String resourceNotFoundAfterDelete() {
        return "Resource is supposed not to be found after deleting";
    }

    @Step("Checking if resource is not found for operations on it")
    public String resourceNotFound() {
        return "Resource is supposed not to be found.";
    }

    @Step("Test with API Request call with incorrect params\\body.")
    public String badRequest() {
        return "Response should contain information about bad request.";
    }

    public String internalServerError() {return "Response supposed error from internal server side.";}

    @Step("Getting items list with filter: \"{0}\". Checking if response corresponds to the filter.")
    public String getMessageFilter(String filter) {
        return String.format("Response should contain item list, all items in it must correspond to filter: \"%s\"", filter);
    }

    @Step("Checking if gotten resource has state: {\"{0}\":\"{1}\"}")
    public String getMessageResourceKeyValue(String key, Object value) {
        return String.format("Checking if gotten resource has state: {\"%s\":\"%s\"}", key, value);
    }

    @Step("Unsuccessful resource creation. Reason:  \"{0}\"")
    public String getMessageResourceCreation(String reason) {
        return String.format("Unsuccessful resource creation. Reason: \"%s\"", reason);
    }

    @Step("Unsuccessful resource getting. Reason:  \"{0}\"")
    public String getMessageResourceGetting(String reason) {
        return String.format("Unsuccessful resource getting. Reason: \"%s\"", reason);
    }

    @Step("Unsuccessful resource updating. Reason:  \"{0}\"")
    public String getMessageResourceUpdating(String reason) {
        return String.format("Unsuccessful resource updating. Reason: \"%s\"", reason);
    }

    @Step("Unsuccessful resource deleting. Reason:  \"{0}\"")
    public String getMessageResourceDeleting(String reason) {
        return String.format("Unsuccessful resource deleting. Reason: \"%s\"", reason);
    }

    @Step("Getting items list from response. Checking if item of resources gotten from endpoint are equals.")
    public String itemsNotEquals() {
        return "Actually items are not equals.";
    }

    @Step("Getting items list from response. Checking if item of resources gotten from endpoint are not equals.")
    public String itemsEquals() {
        return "Actually items are equals.";
    }

    @Step("Check status code after request execution")
    public String wrongStatusCodeAfterRequest() {
        return "Wrong status code after request execution";
    }

    @Step("Check response items size")
    public String itemsSizeIsIncorrect() {
        return "Response items size is incorrect!";
    }

    @Step("Check response offset")
    public String itemsOffsetIsIncorrect() {
        return "Response items offset is incorrect!";
    }

    @Step("Check response items is ordered correctly")
    public String itemsOrderIsIncorrect() {
        return "Response items ordered incorrectly!";
    }

    @Step("Check if items cursor is correct")
    public String beforeCursorIsIncorrect() {
        return "In Response all items ID is not more than provided 'before' ID value";
    }

    @Step("Check if items cursor is correct")
    public String afterCursorIsIncorrect() {
        return "In Response all items ID is not less than provided 'after' ID value";
    }
}
