package db.queries;

public class BankAccountsQueues {
    public static final String SELECT_USER_ACCOUNTS = "SELECT * " +
            "FROM gateway.bank_account " +
            "JOIN gateway.client ON client_id = client.id " +
            "WHERE covenantee_id = %s;";
}
