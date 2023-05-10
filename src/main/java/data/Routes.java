package data;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:main.${path}.properties"})
public interface Routes extends Config {
    @Key("evp.bank.api.url")
    String evpbank();

    @Key("evp.bank.api.audience")
    String evpbankAudience();

    @Key("banking-history.url")
    String bankingHistory();

    @Key("banking-history.audience")
    String bankingHistoryAudience();

    @Key("auth-api.url")
    String authApi();

    @Key("auth-api.audience")
    String authApiAudience();

    @Key("challenge.url")
    String challenge();

    @Key("challenge.audience")
    String challengeAudience();

    @Key("parcel.network.api.url")
    String parcelNetwork();

    @Key("parcel.network.api.audience")
    String parcelNetworkAudience();

    @Key("delivery.api.url")
    String delivery();

    @Key("delivery.api.audience")
    String deliveryAudience();

    @Key("blacklist.url")
    String blacklist();

    @Key("blacklist.audience")
    String blacklistAudience();

    @Key("mobile.checkout.integration.url")
    String mobileCheckoutIntegration();

    @Key("mobile.checkout.integration.audience")
    String mobileCheckoutIntegrationAudience();

    @Key("accounting.api.audience")
    String accountingApiAudience();

    @Key("accounting.api.url")
    String accountingApi();

    @Key("currency_exchange.api.url")
    String currencyExchangeApi();

    @Key("currency_exchange.api.audience")
    String currencyExchangeApiAudience();

    @Key("savings.api.url")
    String savingsApi();

    @Key("savings.api.audience")
    String savingsApiAudience();

    @Key("recurring_payments.api.url")
    String recurringPaymentsApi();

    @Key("recurring_payments.api.audience")
    String recurringPaymentsApiAudience();

    @Key("mokejimai.api.audience")
    String mokejimaiApiAudience();

    @Key("tickets.url")
    String ticketsApi();
    @Key("tickets.api.audience")
    String ticketsApiAudience();

    @Key("mokejimai.api.url")
    String mokejimaiApi();

    @Key("accounting.proxy.api.audience")
    String accountingProxyApiAudience();

    @Key("accounting.proxy.api.url")
    String accountingProxyApi();
}