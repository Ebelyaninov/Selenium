package db;

import data.MainCoreDataManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.testng.SkipException;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.sql.*;

@Slf4j
public class DataBaseUtils implements AutoCloseable {
    private Connection CONNECTION = null;
    private Savepoint SAVEPOINT = null;
    private boolean isError;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public DataBaseUtils(DataBasePath path) {
        isError = false;
        try {
            if (MainCoreDataManager.getDatabase().useSshTunnel()) {
                JSch jsch = new JSch();
                jsch.addIdentity(MainCoreDataManager.getDatabase().sshTunnelPrivateKeyPath());
                Session session = jsch.getSession(MainCoreDataManager.getDatabase().sshTunnelUsername(), MainCoreDataManager.getDatabase().sshTunnelHost(), Integer.parseInt(MainCoreDataManager.getDatabase().sshTunnelPort()));
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();
                int forwardedPort = session.setPortForwardingL(0, path.getDataBaseHost(), Integer.parseInt(path.getDataBasePort()));
                path.setDataBasePort(String.valueOf(forwardedPort));
                path.setDataBaseHost("localhost");
            }
            final String DATA_BASE_FULL_PATH = String.format(path.getDataBaseType().getDataBasePathTemplate(), path.getDataBaseHost(), path.getDataBasePort());
            log.debug("Connecting to DB. URL: {}", DATA_BASE_FULL_PATH);
            CONNECTION = DriverManager.getConnection(DATA_BASE_FULL_PATH, path.getDataBaseUser(), path.getDataBasePassword());
            CONNECTION.setAutoCommit(false);
            SAVEPOINT = CONNECTION.setSavepoint();
        } catch (JSchException jSchException) {
            log.error("Error while connection to SSH Tunnel. JSchException is thrown: " + jSchException.getLocalizedMessage());
        } catch (SQLException sqlException) {
            log.error("Error while connection to DB. SQLException is thrown: " + sqlException.getLocalizedMessage());
            log.error("Check the data: \nhost: {}, \nport: {}, \nuser: {}, \nThe password you should check manually!", path.getDataBaseHost(), path.getDataBasePort(), path.getDataBaseUser());
            throw new SkipException("Something went wrong with getting connection. See the log");
        }
    }

    @Override
    public void close() {
        try {
            if (isError) {
                CONNECTION.rollback(SAVEPOINT);
            } else {
                CONNECTION.commit();
            }
            log.debug("Connection to DB is closing... Errors during query processing: {}", isError);
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (statement != null) {
                statement.close();
            }
            CONNECTION.close();
        } catch (SQLException sqlException) {
            log.error("Error while disconnection from DB. SQLException is thrown: {}", sqlException.getLocalizedMessage());
        }
    }

    public ResultSet executeQuery(String sqlQuery) {
        ResultSet resultSet = null;
        try {
            preparedStatement = CONNECTION.prepareStatement(sqlQuery);
            if (StringUtils.startsWithIgnoreCase(sqlQuery, "SELECT"))
                resultSet = preparedStatement.executeQuery();
            else
                preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            processException(sqlException, sqlQuery);
        }
        return resultSet;
    }

    public Statement getStatement() {
        try {
            statement = CONNECTION.createStatement();
        } catch (SQLException sqlException) {
            processException(sqlException, null);
        }
        return statement;
    }

    private void processException(SQLException sqlException, String query) {
        isError = true;
        log.error("SQLException is thrown: {} \nquery: {}", sqlException.getLocalizedMessage(), query);
        throw new SkipException("Test data was not prepared!", sqlException);
    }
}