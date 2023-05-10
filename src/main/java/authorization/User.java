package authorization;

import api.Container;
//import api.evpbank.transfer.rest.v2.transfers.TransfersAdapter;
import api.model.account.BankAccount;
//import api.model.account.PublicAccountAdapter;
//import api.model.transfer.BankTransfer;
//import api.model.transfer.TransfersList;
import api.model.account.PublicAccountAdapter;
import authorization.adapters.UserIds;
import authorization.models.Context;
import authorization.models.SystemToken;
//import db.adapters.BankAccountsDatabaseAdapter;
import db.adapters.BankAccountsDatabaseAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import utils.RandomData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static authorization.AvailableScopes.ADMIN_N_TECHNOLOGIJOS;
import static authorization.AvailableScopes.ADMIN_PAYSERA_AL;
import static authorization.AvailableScopes.ADMIN_PAYSERA_LT;
import static authorization.AvailableScopes.ADMIN_PAYSERA_LTD;
import static authorization.AvailableScopes.ADMIN_PAYSERA_XK;
import static authorization.AvailableScopes.GLOBAL_BLACKLIST_ENTRIES_MANAGE;
import static authorization.AvailableScopes.GLOBAL_BLACKLIST_ENTRIES_READ;
import static authorization.AvailableScopes.GLOBAL_TRANSFER_SURVEILLANCE_MANAGE;
import static authorization.AvailableScopes.GLOBAL_USER_RESTRICTIONS_MANAGE;
import static authorization.AvailableScopes.GLOBAL_USER_RESTRICTIONS_READ;
import static authorization.AvailableScopes.HISTORY_ROLE_SEARCH;
import static authorization.AvailableScopes.LOGGED_IN;
import static authorization.AvailableScopes.CONFIRMED_LOG_IN;
import static authorization.AvailableScopes.MANAGE_TRANSFERS;
import static authorization.AvailableScopes.DISCOUNTS_READ;
import static authorization.AvailableScopes.DISCOUNTS_MANAGE;
import static authorization.AvailableScopes.ADMIN_MANAGE_UNRELATED;
import static authorization.AvailableScopes.ACCOUNTING_PROXY_MANAGE;
import static authorization.adapters.SystemTokenManager.createSystemToken;
import static authorization.adapters.SystemTokenManager.createSystemTokenOptionalEndpoint;
import static authorization.models.SystemToken.convertScopesListToString;

@AllArgsConstructor
@Getter
@Slf4j
public enum User {
    ADMIN(new UserProfile(UserIds.ADMIN, UserAuthType.USER_ID_FULLY_AUTHENTICATED),
            AuthorizationBasic.BASIC_AUTH_MOKEJIMAI_API,
            Arrays.asList(LOGGED_IN, GLOBAL_TRANSFER_SURVEILLANCE_MANAGE, MANAGE_TRANSFERS),
            Arrays.asList(LOGGED_IN, GLOBAL_BLACKLIST_ENTRIES_MANAGE, GLOBAL_BLACKLIST_ENTRIES_READ,
                    GLOBAL_USER_RESTRICTIONS_MANAGE, GLOBAL_USER_RESTRICTIONS_READ
                    //USER_RESTRICTIONS_READ - GLOBAL_USER_RESTRICTIONS_READ but for only itself
                    //ACCESS_USER_RESTRICTIONS MANAGE_RESTRICTIONS, ACCESS_ALL_USERS_RESTRICTIONS - deprecated scopes, but could be used somewhere
            ),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(AvailableScopes.ADMIN),
            Arrays.asList(ADMIN_PAYSERA_LT, ADMIN_PAYSERA_AL, ADMIN_PAYSERA_XK, ADMIN_PAYSERA_LTD, ADMIN_N_TECHNOLOGIJOS),
            Collections.singletonList(HISTORY_ROLE_SEARCH),
            Arrays.asList(DISCOUNTS_READ, DISCOUNTS_MANAGE),
            Arrays.asList(LOGGED_IN, CONFIRMED_LOG_IN),
            Arrays.asList(LOGGED_IN, CONFIRMED_LOG_IN),
            Collections.singletonList(ADMIN_MANAGE_UNRELATED),
            Collections.singletonList(ACCOUNTING_PROXY_MANAGE)),

    USER(new UserProfile(UserIds.USER, UserAuthType.USER_ID_BASIC),
            AuthorizationBasic.BASIC_AUTH_MOKEJIMAI_API,
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Arrays.asList(AvailableScopes.HISTORY_READ, AvailableScopes.HISTORY_ROLE_SEARCH),
            Collections.singletonList(LOGGED_IN),
            null,
            null,
            Collections.singletonList(LOGGED_IN),
            null),

    NO_SCOPES(new UserProfile(UserIds.USER, UserAuthType.USER_ID_FULLY_AUTHENTICATED),
            AuthorizationBasic.BASIC_AUTH_BROKEN,
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN)),

    INVALID_TOKEN(new UserProfile(UserIds.USER, UserAuthType.USER_ID_BASIC),
            AuthorizationBasic.BASIC_AUTH_BROKEN,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null),

    INCORRECT_USER_ACCOUNT(new UserProfile(UserIds.USER, UserAuthType.USER_ID_BASIC),
            AuthorizationBasic.BASIC_AUTH_MOKEJIMAI_API,
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            Collections.singletonList(LOGGED_IN),
            null,
            null,
            null,
            null,
            null,
            null),

    NO_TOKEN(new UserProfile(UserIds.USER, UserAuthType.USER_ID_BASIC),
            AuthorizationBasic.BASIC_AUTH_BROKEN,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            Collections.singletonList(LOGGED_IN),
            null),

    EXPIRED_TOKEN(new UserProfile(UserIds.USER, UserAuthType.USER_ID_BASIC),
            AuthorizationBasic.BASIC_AUTH_BROKEN,
            Collections.singletonList(LOGGED_IN),
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null);

    private final UserProfile profile;
    private final AuthorizationBasic basicAuth;
    private final List<AvailableScopes> scopesEvpbank;
    private final List<AvailableScopes> scopesBlacklist;
    private final List<AvailableScopes> scopesMobileCheckoutIntegration;
    private final List<AvailableScopes> scopesDelivery;
    private final List<AvailableScopes> scopesAccountingApi;
    private final List<AvailableScopes> scopesBankingHistory;
    private final List<AvailableScopes> scopesCurrencyExchange;
    private final List<AvailableScopes> scopesSavingsApi;
    private final List<AvailableScopes> scopesRecurringPayments;
    private final List<AvailableScopes> scopesMokejimai;
    private final List<AvailableScopes> scopesAccountingProxyApi;

    private final static PublicAccountAdapter publicAccountAdapter = new PublicAccountAdapter();
//    private final static TransfersAdapter transfersAdapter = new TransfersAdapter();
    //To get accounts list by DB request
    private final static BankAccountsDatabaseAdapter bankAccountsDatabaseAdapter = new BankAccountsDatabaseAdapter();

    public String getBearerToken(Container container) {
        if (this == NO_TOKEN) {
            return null;
        } else if (this == EXPIRED_TOKEN) {
            return "Bearer" + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesEvpbank, 1);
        } else if (this != INVALID_TOKEN) {
            switch (container) {
                case EVPBANK:
                    SystemToken systemToken = SystemToken.builder()
                            .audience(container.getAudience())
                            .scope(convertScopesListToString(this.scopesEvpbank))
                            .expiresIn(0)
                            .context(Context.builder()
                                    .accountNumber(this.getFirstAccountNumber())
                                    .userId(this.profile.getCovenanteeId())
                                    .build())
                            .build();
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), systemToken);
                case BLACKLIST:
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesBlacklist, 0);
                case MOBILE_CHECKOUT_INTEGRATION:
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesMobileCheckoutIntegration, 0);
                case DELIVERY:
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesDelivery, 0);
                case SAVINGS_API:
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesSavingsApi, 0);
                case BANKING_HISTORY:
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesBankingHistory, 0);
                case RECURRING_PAYMENTS:
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesRecurringPayments, 0);
                case MOKEJIMAI:
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesMokejimai, 0);
                case ACCOUNTING_API:
                    String createSystemTokenOptional = createSystemTokenOptionalEndpoint(this.profile.getShortAuthToken(), container, this.scopesAccountingApi);
                    if (createSystemTokenOptional == null) {
                        log.info("system token was not generated");
                        return "Bearer null" + RandomData.randomAlphabetic(30);
                    } else
                        return "Bearer " + createSystemTokenOptional;
                case ACCOUNTING_PROXY_API:
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesAccountingProxyApi, 0);
                case CURRENCY_EXCHANGE:
                    return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, this.scopesCurrencyExchange, 0);
                default:
                    throw new IllegalStateException("Unknown container provided for token generation");
            }
        } else return "Bearer null" + RandomData.randomAlphabetic(30);
    }

    public String getBearerToken(Container container, List<AvailableScopes> scopes) {
        return "Bearer " + createSystemToken(this.profile.getShortAuthToken(), container, scopes, 0);
    }

    public List<BankAccount> getAccounts() {
        if (this == INVALID_TOKEN || this == NO_TOKEN) {
            return publicAccountAdapter.getAccountByUserId(USER, HttpStatus.SC_OK)
                    .getItems();
        } else if (this == INCORRECT_USER_ACCOUNT) {
            return bankAccountsDatabaseAdapter.getUserBankAccounts(ADMIN.profile.getCovenanteeId());
        } else return bankAccountsDatabaseAdapter.getUserBankAccounts(this.profile.getCovenanteeId());
    }

//    public List<String> getAccountsNumbers() {
//        return getAccounts().stream()
//                .map(BankAccount::getNumber)
//                .collect(Collectors.toList());
//    }

    public String getFirstAccountNumber() {
        return this.getAccounts()
                .get(0)
                .getNumber();
    }

//    public List<BankTransfer> getTransfers() {
//        TransfersList transfersList = transfersAdapter.getAllTransfersWithCreditNumber(this,
//                this.getFirstAccountNumber(), HttpStatus.SC_OK);
//        return transfersList.getTransfers();
//    }

//    public BankTransfer getRandomTransfer() {
//        List<BankTransfer> transfers = this.getTransfers();
//        return transfers.get(RandomData.randomInt(0, transfers.size() - 1));
//    }
}