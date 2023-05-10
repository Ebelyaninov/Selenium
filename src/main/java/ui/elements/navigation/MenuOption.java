package ui.elements.navigation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenuOption {
    TRANSFERS("Transfers"),
    TO_PHONE_NUMBER("To a Phone Number"),
    BANK_TRANSFER("Bank Transfer"),
    SERVICE_UTILITY_PAYMENT("Service and Utility Payments"),
    PAYSERA_TRANSFER("To a Paysera User"),
    IMPORT_TRANSFERS("Import Transfers"),
    BANK_TRANSFERS("Bank Transfer"),
    ACCOUNTS_AND_CARDS("Accounts and Cards"),
    ACCOUNT_OVERVIEW("Account Overview"),
    ACCOUNT_STATEMENT("Account Statement"),
    DELIVERY("Delivery"),
    DELIVERIES("Deliveries"),
    BETWEEN_OWN_ACCOUNTS("Between Own Accounts"),
    TO_A_PHONE_NUMBER("To a Phone Number"),
    LIST_OF_TRANSFERS("List of Transfers"),
    WEB_MONEY_TRANSFER("WebMoney Transfer"),

    SETTINGS("Settings"),
    PAYMENT_ACCOUNTS("Payment Accounts"),
    LIMITS_RIGHTS_PERMISSIONS("Limits, Rights, Permissions"),

    TEMPLATES("Templates"),
    TRANSFER_TEMPLATES("Transfer Templates"),
    IMPORT_FROM_A_BANK("Import from a Bank"),

    PROJECTS_AND_ACTIVITIES ("Projects and Activities"),
    MY_PROJECTS ("My Projects"),
    INDIVIDUAL_ORDERS("Individual Orders"),
    RECEIVED_PAYMENTS("Received Payments");

    private final String title;

    @Override
    public String toString() {
        return this.title;
    }
}
