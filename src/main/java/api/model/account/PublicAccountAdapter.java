package api.model.account;

import api.Container;
import api.Endpoints;
import authorization.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static utils.UrlUtils.buildUrl;

public class PublicAccountAdapter extends BasePublicAccountAdapter {

    public PublicAccountAdapter() {
        super(Endpoints.EvpBank.Account.ACCOUNTS);
    }

    @Step("Get accounts by userId - \"{0}\" using GET request")
    public AccountItemsResponse getAccountByUserId(User user, int expectedCode) {
        RequestSpecification requestSpecification = setRequestSpecAuthHeader(user.getBearerToken(Container.EVPBANK))
                .queryParam("owned_by_user_id", user.getProfile().getCovenanteeId());
        Response response = get(requestUrl, requestSpecification, expectedCode);
        return gson.fromJson(response.body().asString(), AccountItemsResponse.class);
    }

//    @Step("Get accounts by userId - \"{0}\" using GET request")
//    public AccountItemsResponse getAccountByUserId(String userId, int expectedCode, String token) {
//        RequestSpecification requestSpecification = setRequestSpecAuthHeader(token)
//                .queryParam("owned_by_user_id", userId);
//        Response response = get(requestUrl, requestSpecification, expectedCode);
//        return gson.fromJson(response.body().asString(), AccountItemsResponse.class);
//    }

//    @Step("Get account by account numbers - \"{0}\" and user ID - \"{1}\" using GET request")
//    public AccountItemsResponse getAccountByAccountNumberAndUserId(String accountNumber, String userId, int expectedCode, String token) {
//        RequestSpecification requestSpecification = setRequestSpecAuthHeader(token)
//                .queryParam("account_numbers[]", accountNumber)
//                .queryParam("owned_by_user_id", userId);
//        Response response = get(requestUrl, requestSpecification, expectedCode);
//        return gson.fromJson(response.body().asString(), AccountItemsResponse.class);
//    }
//
//    @Step("Get account descriptions by account numbers - \"{0}\" and user ID - \"{1}\" using GET request")
//    public DescriptionsResponse getAccountDescriptionByAccountNumberAndUserId(String accountNumber, String userId, int expectedCode, String token) {
//        RequestSpecification requestSpecification = setRequestSpecAuthHeader(token)
//                .queryParam("account_numbers[]", accountNumber)
//                .queryParam("user_id", userId);
//        Response response = get(buildUrl(requestUrl, "descriptions"), requestSpecification, expectedCode);
//        return gson.fromJson(response.body().asString(), DescriptionsResponse.class);
//    }
//
//    @Step("Activate account by accountNumber - \"{0}\" using PUT request")
//    public AccountResponse activateAccountByAccountNumber(String accountNumber, int expectedCode, String token) {
//        Response response = put(buildUrl(requestUrl, accountNumber, "activate"), setRequestSpecAuthHeader(token), expectedCode);
//        return gson.fromJson(response.body().asString(), AccountResponse.class);
//    }
//
//    @Step("Deactivate account by accountNumber - \"{0}\" using PUT request")
//    public AccountResponse deactivateAccountByAccountNumber(String accountNumber, int expectedCode, String token) {
//        Response response = put(buildUrl(requestUrl, accountNumber, "deactivate"), setRequestSpecAuthHeader(token), expectedCode);
//        return gson.fromJson(response.body().asString(), AccountResponse.class);
//    }
//
//    @Step("Edit account description by body - \"{0}\" and accountNumber - \"{0}\" using PUT request")
//    public DescriptionEditResponse editAccountDescriptionByAccountNumber(DescriptionEditResponse body, String accountNumber, int expectedCode, String token) {
//        Response response = put(buildUrl(requestUrl, accountNumber, "descriptions"), setRequestSpecAuthHeader(token).body(gson.toJson(body)), expectedCode);
//        return gson.fromJson(response.body().asString(), DescriptionEditResponse.class);
//    }
//
//    @Step("Delete account description by accountNumber - \"{0}\" using DELETE request")
//    public DescriptionEditResponse deleteAccountDescriptionByAccountNumber(String accountNumber, int expectedCode, String token) {
//        Response response = delete(buildUrl(requestUrl, accountNumber, "descriptions"), setRequestSpecAuthHeader(token), expectedCode);
//        return gson.fromJson(response.body().asString(), DescriptionEditResponse.class);
//    }
//
//    @Step("Get Full account balance by account numbers - \"{0}\" using GET request")
//    public FullBalanceResponse getFullAccountBalanceByAccountNumber(String accountNumber, int expectedCode, String token) {
//        Response response = get(buildUrl(requestUrl, accountNumber, "full-balance"), setRequestSpecAuthHeader(token), expectedCode);
//        return gson.fromJson(response.body().asString(), FullBalanceResponse.class);
//    }
//
//    @Step("Get Credit amount for account by account numbers - \"{0}\" using GET request")
//    public CredentialsAndContisBalanceResponse getCreditBalanceByAccountNumber(String accountNumber, int expectedCode, String token) {
//        Response response = get(buildUrl(requestUrl, accountNumber, "credit"), setRequestSpecAuthHeader(token), expectedCode);
//        return gson.fromJson(response.body().asString(), CredentialsAndContisBalanceResponse.class);
//    }
//
//    @Step("Get Contis balance amount for account by account numbers - \"{0}\" using GET request")
//    public Reserved getContisBalanceByAccountNumber(String accountNumber, int expectedCode, String token) {
//        Response response = get(buildUrl(requestUrl, accountNumber, "contis-balance"), setRequestSpecAuthHeader(token), expectedCode);
//        return gson.fromJson(response.body().asString(), Reserved.class);
//    }
//
//    @Step("Get AccountAliases for account by account numbers - \"{0}\" using GET request")
//    public AliasesResponse getAliasesByAccountNumber(String accountNumber, int expectedCode, String token) {
//        Response response = get(buildUrl(requestUrl, accountNumber, "aliases"), setRequestSpecAuthHeader(token), expectedCode);
//        return gson.fromJson(response.body().asString(), AliasesResponse.class);
//    }
}
