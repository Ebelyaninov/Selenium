package db;

import data.MainCoreDataManager;

public abstract class MainAdapterDB {
    protected DataBasePath dataBasePath;

    //Please move to method without providing ur personal credentials
    @Deprecated
    protected MainAdapterDB(DataBaseType type, String host, String port, String user, String password) {
        dataBasePath = new DataBasePath(type, host, port, user, password);
    }

    //Please move to method without providing ur personal credentials
    @Deprecated
    protected MainAdapterDB(DataBaseType type, String hostWithPort, String user, String password) {
        String host = hostWithPort.split(":")[0];
        String port = hostWithPort.split(":")[1];
        dataBasePath = new DataBasePath(type, host, port, user, password);
    }

    //Please move to method without providing ur personal credentials
    @Deprecated
    protected MainAdapterDB(DataBaseType type, Database db, String user, String password) {
        this(type, db.getHost(), user, password);
    }

    protected MainAdapterDB(DataBaseType type, String hostWithPort) {
        String host = hostWithPort.split(":")[0];
        String port = hostWithPort.split(":")[1];
        dataBasePath = new DataBasePath(type, host, port, MainCoreDataManager.getDatabase().user(), MainCoreDataManager.getDatabase().userPassword());
    }

    protected MainAdapterDB(Database db) {
        this(db.getType(), db.getHost());
    }

    protected static Boolean getBooleanFromTableValue(Object value) {
        if (value.toString().equals("1"))
            return Boolean.TRUE;
        else return Boolean.FALSE;
    }

    protected static Integer getTableValueFromBoolean(boolean is) {
        if (is)
            return 1;
        else return 0;
    }
}