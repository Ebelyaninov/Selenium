package db.adapters;

import api.model.account.BankAccount;
import db.DataBaseUtils;
import db.Database;
import db.MainAdapterDB;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import utils.RandomData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static db.queries.BankAccountsQueues.SELECT_USER_ACCOUNTS;
@Slf4j
public class BankAccountsDatabaseAdapter extends MainAdapterDB {
    public BankAccountsDatabaseAdapter() {
        super(Database.GATEWAY);
    }

    public static BankAccount buildBankAccount(ResultSet resultSet) throws SQLException {
        return BankAccount.builder()
                .number(resultSet.getString("number"))
                .ownerId(resultSet.getInt("client_id"))
                .id(resultSet.getInt("id"))
                .build();
    }

    @SneakyThrows
    @Step("Get list of user bank accounts")
    public List<BankAccount> getUserBankAccounts(int userId) {
        ArrayList<BankAccount> list = new ArrayList<>();
        DataBaseUtils dataBaseUtils = new DataBaseUtils(dataBasePath);
        ResultSet resultSet = dataBaseUtils.executeQuery(String.format(SELECT_USER_ACCOUNTS, userId));
        while (resultSet.next()) {
            list.add(BankAccount.builder()
                    .number(resultSet.getString("number"))
                    .ownerId(resultSet.getInt("client_id"))
                    .id(resultSet.getInt("id"))
                    .build());
            list.add(buildBankAccount(resultSet));
        }
        dataBaseUtils.close();
        return list;
    }

//    @SneakyThrows
//    public List<BankAccount> getPayseraAccounts(OrderDirection orderDirection, boolean active, int limit) {
//        String request = "SELECT *\n" +
//                "FROM gateway.bank_account\n" +
//                "WHERE active =" + getTableValueFromBoolean(active) +
//                "\nORDER BY id " + orderDirection.getText() +
//                "\nLIMIT " + limit;
//
//        ArrayList<BankAccount> list = new ArrayList<>();
//        DataBaseUtils dataBaseUtils = new DataBaseUtils(dataBasePath);
//        ResultSet resultSet = dataBaseUtils.executeQuery(request);
//        while (resultSet.next()) {
//            list.add(buildBankAccount(resultSet));
//        }
//        dataBaseUtils.close();
//        return list;
//    }
//
//    @SneakyThrows
//    @Step("Get client id from database which has discount for currency exchange")
//    public String getRandomBankAccountNumber() {
//        DataBaseUtils dataBaseUtils = new DataBaseUtils(dataBasePath);
//        ResultSet resultSet = dataBaseUtils.executeQuery(SELECT_TOP_ACCOUNT_NUMBERS);
//        for (int i = 0; i < RandomData.randomInt(1, 10); i++) {
//            resultSet.next();
//        }
//        dataBaseUtils.close();
//        return resultSet.getString("number");
//    }
//
//    @SneakyThrows
//    @Step("Deactivate and close Bank account")
//    public void deactivateAndCloseBankAccount(String accountNumber) {
//        DataBaseUtils dataBaseUtils = new DataBaseUtils(dataBasePath);
//        dataBaseUtils.executeQuery(String.format(DEACTIVATE_AND_CLOSE_ACCOUNT, accountNumber));
//    }
//
//    @SneakyThrows
//    @Step("Delete Bank account")
//    public void deleteBankAccount(String bankAccount) {
//        DataBaseUtils dataBaseUtils = new DataBaseUtils(dataBasePath);
//        dataBaseUtils.getStatement().execute(String.format(DELETE_BANK_ACCOUNT, bankAccount));
//    }
}
