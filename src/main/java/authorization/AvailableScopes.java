package authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AvailableScopes {
    LOGGED_IN("logged_in"),
    CONFIRMED_LOG_IN("confirmed_log_in"),

    /**
     * [app-banking-history] available scopes
     */
    HISTORY_READ("h:r"),
    HISTORY_ROLE_SEARCH("ROLE_SEARCH"),
    HISTORY_INSERT("'h:i'"),
    HISTORY_UPDATE("'h:u'"),
    /**
     * [app-blacklist] available scopes
     */
    //https://gitlab.paysera.net/paysera/app-blacklist/-/blob/master/src/Paysera/Component/Scopes.php
    GLOBAL_BLACKLIST_ENTRIES_MANAGE("g:user_blacklist_entries:manage"),

    GLOBAL_BLACKLIST_ENTRIES_READ("g:user_blacklist_entries:read"),

    GLOBAL_USER_RESTRICTIONS_MANAGE("g:user_restrictions:manage"),

    GLOBAL_USER_RESTRICTIONS_READ("g:user_restrictions:read"),

    USER_RESTRICTIONS_READ("user_restrictions:read"),

    //https://gitlab.paysera.net/paysera/app-blacklist/-/blob/master/src/Paysera/RestrictionBundle/RestrictionScopes.php
    ACCESS_USER_RESTRICTIONS("r:a:{%s}"),

    MANAGE_RESTRICTIONS("r:m"),

    ACCESS_ALL_USERS_RESTRICTIONS("r:a:au"),

    /**
     * [app-blacklist] available scopes ended
     * [app-evpbank] available scopes
     */
    GLOBAL_CHARGES_CREATE("g:charges:create"),

    GLOBAL_CHALLENGES_CREATE("g:challenges:create"),

    GLOBAL_BANK_INFORMATION_READ("g:bank_information:read"),

    GLOBAL_TRANSFER_SURVEILLANCE_MANAGE("g:transfer_surveillance:manage"),

    GLOBAL_BLACKLISTED_PROFILES_READ("g:blacklisted_profiles:read"),

    GLOBAL_ACCESS_CURRENCIES("g:currencies:read"),

    GLOBAL_EXECUTE_CURRENCY_EXCHANGE("g:currency_exchange:execute"),

    GLOBAL_MANAGE_PERMISSIONS("g:permissions:manage"),

    DIRECT_ACCESS_DEPOSITS_CREATE("da:deposits:create"),

    CREATE_ACCOUNT("accounts:create"),

    SIGN_TRANSFER("transfer:sign"),

    GET_TRANSFER("transfer:get"),

    CREATE_TRANSFERS("transfers:create"),

    MANAGE_TRANSFERS("transfers:manage"),

    CHECK_USER_ALLOWANCES("user:check_allowances"),

    GET_TRANSFER_TEMPLATES("transfers:get_templates"),

    SMS_CHARGE_CREATE("sms_charge:create"),

    CREATE_CLIENT("client:create"),
    /**
     * [app-evpbank] available scopes ended
     * <p>
     * [app-delivery] available scopes
     */
    //https://gitlab.paysera.net/paysera/app-delivery-api/-/blob/master/src/Paysera/Bundle/ShippingBundle/Scopes.php
    ADMIN("g:delivery:admin"),

    BACKEND("delivery:backend"),

    /**
     * [app-accounting-api] available scopes
     */
    //https://gitlab.paysera.net/paysera/app-accounting-api/-/blob/master/src/Security/Scopes.php
    ADMIN_PAYSERA_LT("g:accounting:manage_paysera_lt"),

    ADMIN_PAYSERA_AL("g:accounting:manage_paysera_al"),

    ADMIN_PAYSERA_XK("g:accounting:manage_paysera_xk"),

    ADMIN_PAYSERA_LTD("g:accounting:manage_paysera_ltd"),

    ADMIN_N_TECHNOLOGIJOS("g:accounting:manage_n_technologijos"),

    /**
     * [app-accounting-proxy-api] available scopes
     */
    ACCOUNTING_PROXY_MANAGE("g:accounting_proxy:manage"),

    /**
     * [app-currency_exchange-api] available scopes
     */
    DISCOUNTS_READ("discounts:read"),
    DISCOUNTS_MANAGE("discounts:manage"),
    /**
     * [app-mokejimai] available scopes
     */
    ADMIN_MANAGE_UNRELATED("g:statements:manage_unrelated");

    private final String value;
}