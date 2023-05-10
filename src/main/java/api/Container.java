package api;

import data.MainCoreDataManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Locale;

@Getter
@AllArgsConstructor
public enum Container {
    EVPBANK(MainCoreDataManager.getRoutes().evpbankAudience(), MainCoreDataManager.getRoutes().evpbank()),
    BANKING_HISTORY(MainCoreDataManager.getRoutes().bankingHistoryAudience(), MainCoreDataManager.getRoutes().bankingHistory()),
    AUTH_API(MainCoreDataManager.getRoutes().authApiAudience(), MainCoreDataManager.getRoutes().authApi()),
    DELIVERY(MainCoreDataManager.getRoutes().deliveryAudience(), MainCoreDataManager.getRoutes().delivery()),
    CHALLENGE(MainCoreDataManager.getRoutes().challengeAudience(), MainCoreDataManager.getRoutes().challenge()),
    PARCEL_NETWORK(MainCoreDataManager.getRoutes().parcelNetworkAudience(), MainCoreDataManager.getRoutes().parcelNetwork()),
    BLACKLIST(MainCoreDataManager.getRoutes().blacklistAudience(), MainCoreDataManager.getRoutes().blacklist()),
    MOBILE_CHECKOUT_INTEGRATION(MainCoreDataManager.getRoutes().mobileCheckoutIntegrationAudience(), MainCoreDataManager.getRoutes().mobileCheckoutIntegration()),
    ACCOUNTING_API(MainCoreDataManager.getRoutes().accountingApiAudience(), MainCoreDataManager.getRoutes().accountingApi()),
    ACCOUNTING_PROXY_API(MainCoreDataManager.getRoutes().accountingProxyApiAudience(), MainCoreDataManager.getRoutes().accountingProxyApi()),
    CURRENCY_EXCHANGE(MainCoreDataManager.getRoutes().currencyExchangeApiAudience(), MainCoreDataManager.getRoutes().currencyExchangeApi()),
    SAVINGS_API(MainCoreDataManager.getRoutes().savingsApiAudience(), MainCoreDataManager.getRoutes().savingsApi()),
    RECURRING_PAYMENTS(MainCoreDataManager.getRoutes().recurringPaymentsApiAudience(), MainCoreDataManager.getRoutes().recurringPaymentsApi()),
    MOKEJIMAI(MainCoreDataManager.getRoutes().mokejimaiApiAudience(), MainCoreDataManager.getRoutes().mokejimaiApi()),
    TICKETS(MainCoreDataManager.getRoutes().ticketsApiAudience(), MainCoreDataManager.getRoutes().ticketsApi());
    private final String audience;
    private final String host;

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.ROOT).replace("_", "-");
    }
}