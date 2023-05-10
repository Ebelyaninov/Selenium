package db;

import data.MainCoreDataManager;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Database {
    API_CURRENCY(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().apiCurrency()),
    WALLET(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().wallet()),
    TICKETS(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().tickets()),
    RECURRING_PAYMENTS(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().recurringPayments()),
    POSTBANK_BG_IBAN(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().postbankBgIban()),
    PSD2_TESTS(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().psd2Tests()),
    GATEWAY(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().gateway()),
    WALLET_FRONTEND(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().walletFrontend()),
    DELIVERY_API(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().deliveryApi()),
    ANEMOMETER(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().anemometerApi()),
    STATISTICS(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().statistics()),
    STATISTICS_LOCAL(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().statisticsLocal()),
    PARTNER_FRONTEND(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().partnerFrontend()),
    SAVINGS(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().savings()),
    EVP_LT(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().evpLt()),
    PARCEL_NETWORK(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().parcelNetwork()),
    ACCOUNTING(DataBaseType.MYSQL, MainCoreDataManager.getDatabase().accounting());

    private final DataBaseType type;
    private final String host;
}