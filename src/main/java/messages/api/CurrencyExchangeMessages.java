package messages.api;

import io.qameta.allure.Step;

public class CurrencyExchangeMessages {
    @Step("Check if response currencies list is valid")
    public String currenciesListIsNotValid() {
        return "Currencies response list is not valid";
    }

    @Step("Check if first response currencies list include all currencies from fixed currencies list object")
    public String currenciesListContainsAllFixedCurrencies() {
        return "Response currencies list object must include all currencies from fixed currencies list object";
    }

    @Step("Check if response base currency equal param base currency value")
    public String baseCurrencyEqualBaseCurrency() {
        return "Base currency must be equal param base currency!";
    }

    @Step("Check if response rates contains query param currency")
    public String ratesContainsQueryParamCurrency() {
        return "response rates must contain query param currency!";
    }

    @Step("Check if currency rate is more than 0.0")
    public String currencyRateIsNotMoreThanZero() {
        return "Any currency rates must be more than 0.0";
    }

    @Step("Check if response rates list include all currencies from get currencies list response")
    public String ratesListContainsAllCurrencies() {
        return "Rates list must be contains all currencies from get currencies list request response!";
    }

    @Step("Check if response rates list size equal 1 when use query currency param")
    public String ratesSizeEqualOne() {
        return "When use currency query param response must include only 1 rate!";
    }

    @Step("Check if response Currency param equal to given currency param")
    public String responseCurrencyEqualGivenCurrency() {
        return "Currency param must equal given currency!";
    }

    @Step("Check if response Currency param equal to result currency param")
    public String responseCurrencyEqualResultCurrency() {
        return "Currency param must equal result currency!";
    }

    @Step("Check if response amount param equal calculated amount from rates response")
    public String calculatedAmountIsWrong() {
        return "calculated amount from Rates response and calculated amount from the response not equal!";
    }

    @Step("Check if response amount with rounding UP mode is more than response amount with rounding DOWN mode")
    public String calculatedAmountWithRoundingUPModeIsNotMoreThanDownMode() {
        return "calculated amount with rounding UP modes must be more than calculated amount with rounding DOWN mode!";
    }

    @Step("Check if response amount is more than zero")
    public String responseAmountIsMoreThanZero() {
        return "Response amount must be more than zero!";
    }

//    @Step("Check if Buy rate with discount is more than rate without discount")
//    public String buyRateWithDiscountIsNotMoreThanRateWithoutDiscount(Double rateWithDiscount, Double rateWithoutDiscount, Currency currency) {
//        return String.format("Buy rate with discount is not more than rate without discount! Discount with rate == %f, Discount without rate: %f For currency: %s", rateWithDiscount, rateWithoutDiscount, currency);
//    }

//    @Step("Check if Sell rate with discount is less than rate without discount")
//    public String sellRateWithDiscountIsNotLessThanRateWithoutDiscount(Double rateWithDiscount, Double rateWithoutDiscount, Currency currency) {
//        return String.format("Sell rate with discount is not less than rate without discount! Discount with rate == %f, Discount without rate: %f For currency: %s", rateWithDiscount, rateWithoutDiscount, currency);
//    }

//    @Step("Check if official rate with discount is not equal rate without discount")
//    public String officialRateWithDiscountIsNotEqualRateWithoutDiscount(Double rateWithDiscount, Double rateWithoutDiscount, Currency currency) {
//        return String.format("Official rate with discount is not equal rate without discount! Discount with rate == %f, Discount without rate: %f For currency: %s", rateWithDiscount, rateWithoutDiscount, currency);
//    }

    @Step("Check if calculated amount with clientId is more than amount without clientId")
    public String calculatedAmountWithClientIdIsMoreThanAmountWithoutClientId() {
        return "calculated amount with clientId must be more than calculated amount without clientId!";
    }

    @Step("Check if calculated amount with clientType is more than amount without clientType")
    public String calculatedAmountWithClientTypeIsMoreThanAmountWithoutClientType() {
        return "calculated amount with clientType must be more than calculated amount without clientType!";
    }
    @Step("Check if response title value match requested value")
    public String responseDiscountGroupTitleIsWrong() {
        return "Response discount group title does not match the requested!";
    }
    @Step("Check if response priority value match requested value")
    public String responseDiscountGroupPriorityIsWrong() {
        return "Response discount group priority does not match the requested!";
    }
    @Step("Check if response conditions value match requested value")
    public String responseDiscountGroupConditionsIsWrong() {
        return "Response discount conditions type does not match the requested!";
    }

    @Step("Check if response rates list is not empty")
    public String ratesListIsEmpty() {
        return "Response rates list is empty!";
    }
}