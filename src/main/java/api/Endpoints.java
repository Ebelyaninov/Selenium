package api;

public class Endpoints {
    public class EvpBank {
        public class PublicAccounts {
            public static final String ACCOUNTS = "accounts";
            public static final String HOST = "public/account";
        }

        public class Transfer {
            public static final String HOST = "public/transfer";
            public static final String TRANSFERS = "transfers";
            public static final String TRUST_BENEFICIARY = "transfers/%s/trust-beneficiary";
            public static final String CURRENCY_CONVERSION = "currency-conversion";
            public static final String CONVERSION_TRANSFERS = "conversion-transfers";
            public static final String PURPOSE_CODES = "purpose-codes";
            public static final String TRANSFER_TEMPLATES = "transfer-templates";
            public static final String TRUSTED_BENEFICIARIES = "trusted-beneficiaries";
            public static final String SIGNATURE_INFO = "signature-info";
            public static final String REQUIRED_SUPPLEMENT = "required-supplement";
            public static final String NOTICES = "notices";
            public static final String SINGLE_WINDOW_TRANSFER_NUMBER = "single-window-transfer-number";
            public static final String REGISTER = "register";
            public static final String CHECK_LIMITS = "check-limits";
            public static final String PROVIDE_PASSWORD = "provide-password";
        }

        public class TransferSurveillance {
            public static final String HOST = "transfer-surveillance";
            public static final String BLACKLISTED_COMPANIES = "blacklisted-companies";
            public static final String BLACKLISTED_WORDS = "blacklisted-words";
            public static final String BLACKLISTED_WORDS_GROUPS = BLACKLISTED_WORDS + "/groups";
            public static final String BLACKLISTED_WORDS_GROUPS_WORDS = BLACKLISTED_WORDS_GROUPS + "/%s/words";
            public static final String CRITERIA = "criteria";
            public static final String FRAUDULENT_ACCOUNTS = "fraudulent-accounts";
            public static final String FRAUDULENT_ACCOUNTS_GROUPS = "fraudulent-account-groups";
            public static final String MATCHERS = "matchers";
            public static final String PARTY_COMMENTS = "party-comments";
            public static final String PARTY_COMMENTS_ATTACHMENTS = PARTY_COMMENTS + "/%s/attachments";
            public static final String RULES = "rules";
            public static final String RULES_WHITELISTS = RULES + "/%s/whitelists";
            public static final String RULES_WHITELIST_PROFILE_LIST = RULES_WHITELISTS + "/%s/profile-list";
            public static final String RULES_WHITELIST_IGNORED_WORD_LIST = RULES_WHITELISTS + "/%s/ignored-word-list";
        }

        public class Deposit {
            public static final String HOST = "deposit";
            public static final String DEPOSIT = "deposits";
            public static final String DEPOSIT_CHARGES = "charges";

        }

        public class Account {
            public static final String HOST = "account";
            public static final String ACCOUNTS = "accounts";
            public static final String RESERVATION_STATEMENTS = "reservation-statements";
            public static final String OWNED_ACCOUNTS_COUNT = "owned-accounts/count";
            public static final String STATEMENTS = "statements";


        }

        public class AccountDiscrepancy {
            public static final String HOST = "accounting-discrepancies";
            public static final String CAUSE_REQUEST = "cause-request";

        }

        public class PublicApi {
            public static final String HOST = "public";
            public static final String ACCOUNT = "account";
            public static final String TRANSFER_IMPORT = "transfer-import";
            public static final String TAX_BENEFICIARY = "tax-beneficiary";
            public static final String TRANSFER = "transfer";
            public static final String TRANSFER_IMPORTS = "transfer-imports";
            public static final String CATEGORIZED_ACCOUNT_NUMBERS = "categorized-account-numbers";
            public static final String CATEGORIZED_COUNTRIES = "categorized-countries";
            public static final String SWIFT = "swift";
            public static final String BANK_INFORMATION = "bank-information";
            public static final String BANK_PARTICIPATION = "bank-participation";
            public static final String TAX_BENEFICIARIES = "tax-beneficiaries";
            public static final String PERMISSION = "permission";
            public static final String AUTHORIZATIONS = "authorizations";
            public static final String AUTHORIZATIONS_USER_VALIDATION = AUTHORIZATIONS + "/authorization-user-validation";
            public static final String AUTHORIZATIONS_BY_AUTHORIZATIONS_ID = AUTHORIZATIONS + "/%s";
            public static final String AUTHORIZATIONS_BY_AUTH_ID_AND_USER_ID = AUTHORIZATIONS + "/%s/users/%s";
            public static final String AUTHORIZATIONS_LIMITS_BY_USER_ID = "users/%s/limits";
            public static final String INVALIDATE_PERMISSIONS = "permissions/invalidate";
            public static final String CHARGE = "charge";
            public static final String CHARGES = "charges";
            public static final String CURRENCY = "currency";
            public static final String AVAILABLE_CURRENCIES = "available-currencies";
        }
    }


    public class Blacklist {

    }

    public class AuthApi {
        public static final String AUTHENTICATION = "authentication";
        public static final String AUTH_TOKENS = "auth-tokens/user-id";
        public static final String STRICT = "system-tokens/strict";
        public static final String OPTIONAL = "system-tokens/optional";
    }

    public class StatusApi {

    }

    public class Challenge {

    }

    public class BankingHistory {
        public static final String TRANSFERS = "transfers";
        public static final String AGGREGATED_TERMS = "aggregated-terms";
        public static final String DISJOIN_QUERY = "transfers/disjoin-query";
        public static final String AGGREGATED_SEARCH = "transfers/aggregated-search";
        public static final String BULK = "bulk";
        public static final String UPDATE_STATUS = "transfers/%s/update-status";
        public static final String INSPECTION = "transfers/%s/inspection";
        public static final String USERS = "users";
        public static final String CARD_TRANSACTIONS = "card-transactions";
    }

    public class DeliveryApi {
        public static final String ORDERS = "orders";

        public class Admin {
            public static final String HOST = "admin";
            public static final String GATEWAYS = "gateways";
            public static final String METHODS = "methods";
            public static final String LIMIT_PACKAGES = "packages";
            public static final String LIMIT_PACKAGES_IMPORT = "packages-import";
            public static final String DEFAULT_PACKAGE_SIZES = "default-package-sizes";
        }

        public class Backend {
            public static final String HOST = "backend";
            public static final String PROJECTS = "projects";
            public static final String MONEY_RESERVATIONS = "money-reservations";
            public static final String MONEY_RESERVATIONS_PROCESS = "process";
        }
    }

    public class ParсelNetworkApi {
        public static final String HOST = "admin";
        public static final String PRICE = "price";
        public static final String TERMINALS = "terminals";
        public static final String TERMINALS_CELLS = "cells";
        public static final String TERMINALS_CELLS_SIZES = "sizes";
        public static final String TERMINALS_MODELS = "terminal-models";
        public static final String TERMINALS_MODEL_CELL_SIZES = "cell-sizes";
        public static final String COURIER_COMPANIES = "courier-companies";
        public static final String COURIER_COMPANIES_CREATE_AUTH_TOKEN = "create-auth-token";
        public static final String COURIER_COMPANIES_SETTINGS = "settings";
    }

    public class MobileCheckoutIntegration {
        public static final String PAYMENT_TYPES = "payment-types";
    }

    public class AccountingApi {
        public static final String PARTNERS = "partners";
        public static final String OPERATIONS = "operations";
        public static final String OPERATION_ACCOUNTS = "operation-accounts";
        public static final String TEMPLATES = "templates";
        public static final String ACCOUNT_BALANCES = "account-balances";
        public static final String HISTORY = "history";
        public static final String FULL = "full";
        public static final String REPORTS = "reports";
        public static final String STATUS = "status";
    }

    public class AccountingProxyApi {
        public static final String OPERATIONS = "operations";
        public static final String OPERATION_BY_LEGACY_ID = "operation-by-legacy-id";
        public static final String OPERATION_BY_ID = "operation-by-id";
        public static final String ACCOUNTS = "accounts";
        public static final String OPERATION_ACCOUNT_BY_LEGACY_ID = "operation-account-by-legacy-id";
        public static final String OPERATION_ACCOUNT_BY_ID = "operation-account-by-id";
        public static final String TEMPLATES = "templates";
        public static final String TEMPLATE_BY_LEGACY_ID = "template-by-legacy-id";
        public static final String TEMPLATE_BY_ID = "template-by-id";
    }

    public static class CurrencyExchangeApi {
        public static final String CURRENCY = "currency";
        public static final String DISCOUNT = "discount/rest/v1";

        public static class Official {
            public static final String HOST = "official";
            public static final String CURRENCIES = "currencies/%s";
            public static final String EXCHANGE_RATES = "exchange-rates/%s/%s";
            public static final String FROM_GIVEN_CURRENCY = "exchange/%s/%s-%s/%s";
            public static final String FROM_GIVEN_AMOUNT_AND_CURRENCY = "exchange/%s-%s/%s/%s";
        }

        public static class OfficialByPartner {
            public static final String HOST = "official-by-partner";
            public static final String CURRENCIES = "%s/currencies/%s";
            public static final String EXCHANGE_RATES = "%s/exchange-rates/%s/%s";
            public static final String FROM_GIVEN_CURRENCY = "%s/exchange/%s/%s-%s/%s";
            public static final String FROM_GIVEN_AMOUNT_AND_CURRENCY = "%s/exchange/%s-%s/%s/%s";
        }

        public static class Commercial {
            public static final String HOST = "commercial";
            public static final String CURRENCIES = "currencies/%s";
            public static final String EXCHANGE_RATES = "exchange-rates/%s/%s";
            public static final String FROM_GIVEN_CURRENCY = "exchange/%s/%s-%s/%s";
            public static final String FROM_GIVEN_AMOUNT_AND_CURRENCY = "exchange/%s-%s/%s/%s";
        }

        public static class Banks {
            public static final String CURRENCIES = "%s/currencies/%s";
            public static final String EXCHANGE_RATES = "%s/exchange-rates/%s/%s";
            public static final String FROM_GIVEN_CURRENCY = "%s/exchange/%s/%s-%s/%s";
            public static final String FROM_GIVEN_AMOUNT_AND_CURRENCY = "%s/exchange/%s-%s/%s/%s";
        }

        public static class MarketValue {
            public static final String HOST = "market-value";
            public static final String CURRENCIES = "currencies/%s";
            public static final String EXCHANGE_RATES = "exchange-rates/%s/%s";
            public static final String FROM_GIVEN_CURRENCY = "exchange/%s/%s-%s/%s";
            public static final String FROM_GIVEN_AMOUNT_AND_CURRENCY = "exchange/%s-%s/%s/%s";
        }

        public static class Discount {
            public static final String GROUP = "group";
            public static final String GROUP_ID = GROUP + "/%d";
            public static final String GROUP_ID_RULE = GROUP_ID + "/rule";
            public static final String GROUP_ID_RULE_ID = GROUP_ID_RULE + "/%d";
            public static final String MATCHING_RULE = "matching-rule";
        }
    }

    public static class SavingsApi {
        public static final String HOST = "savings/rest/v1";
        public static final String USER = "users/%s/savings-accounts";
        public static final String SAVINGS_ACCOUNTS = "savings-accounts";
        public static final String ACCOUNT_NUMBER = SAVINGS_ACCOUNTS + "/%s";
        public static final String GOAL = ACCOUNT_NUMBER + "/goal";
        public static final String ACCOUNT_AUTOMATED_FILLS = ACCOUNT_NUMBER + "/automated-fills";
        public static final String AUTOMATED_FILLS = "automated-fills";
        public static final String AUTOMATED_FILL_ID = AUTOMATED_FILLS + "/%s";
    }

    public static class RecurringPaymentsApi {
        public static class Recurrence {
            public static final String HOST = "recurrence/rest/v1";
            public static final String RECURRENCES = "recurrences";
            public static final String RECURRENCES_HASH = RECURRENCES + "/%s";
            public static final String RECURRENCES_HASH_ACTIVATE = RECURRENCES_HASH + "/activate";
            public static final String RECURRENCES_HASH_DEACTIVATE = RECURRENCES_HASH + "/deactivate";
            public static final String RECURRENCES_HASH_TRANSFERS = RECURRENCES_HASH + "/transfers";
            public static final String RECURRENCES_HASH_TRANSFERS_REPEAT = RECURRENCES_HASH + "/transfers/%s/repeat";
        }

        public static class RecurrenceDev {
            public static final String HOST = "dev";
            public static final String SET_NEXT_PROCESSING_DATE_TO_NOW = "set-all-waiting-recurrence-next-processing-date-to-now";
            public static final String PROCESS_WAITING_RECURRENCES = "process-waiting-recurrences";
            public static final String PROCESS_RECURRENCE = "recurrence/%s/process";
            public static final String PROCESS_TRANSFER_CALLBACK = "transfer-callback/%d";
        }
    }

    public class TicketsApi {
        public static final String MAIL_BASE = "en/my/user-main-email/rest/v1";
        public static final String DEVICE_API = "api/sync/v1/events";
        public static final String EVENT_SEARCH = "en/my/dashboard/rest/v1";
        public static final String EVENT_INFO = "{token}/info";
        public static final String EVENT_SYNC = "{token}/sync";
        public static final String MAIL_CHALLENGE = "main-email-challenges";
        public static final String MAIL_CHALLENGE_ACTIVE= "main-email-challenges/active";
        public static final String MAIL_CHALLENGE_CANCEL= "main-email-challenges/{hashOfChallenge}/cancel";
    }

    public class MokejimaiApi {
        public static final String STATEMENT_ANALYSIS_REPORT_HOST = "statement-analysis-report";
        public static final String STATEMENTS = "statements";
        public static final String DISABLE_ACCOUNTING_STATEMENTS = "disable-accounting-only";
        public static final String ENABLE_ACCOUNTING_STATEMENTS = "enable-accounting-only";
        public static final String CHECKOUT_HOST = "checkout";
        public static final String GATEWAYS = "gateways";
        public static final String PAYMENT_METHODS = "payment-methods";
        public static final String PAYMENT_REQUSET = "payment-request";
    }
}