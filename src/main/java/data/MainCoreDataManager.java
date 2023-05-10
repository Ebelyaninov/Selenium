package data;

public class MainCoreDataManager extends DataManager {
    private static BrowserConfiguration browserConfiguration;
    private static LogConfiguration logConfiguration;
    private static Directories directories;
    private static Routes routes;
    private static Database database;
    private static PluginConfiguration pluginConfiguration;
    private static MacAuthConfiguration macAuthConfiguration;

    public static BrowserConfiguration getBrowserConfiguration() {
        return getConfigData(browserConfiguration, BrowserConfiguration.class);
    }

    public static Routes getRoutes() {
        return getConfigData(routes, Routes.class);
    }

    public static Database getDatabase() {
        return getConfigData(database, Database.class);
    }

    public static Directories getDirectories() {
        return getConfigData(directories, Directories.class);
    }

    public static LogConfiguration getLogConfiguration() {
        return getConfigData(logConfiguration, LogConfiguration.class);
    }

    public static PluginConfiguration getPluginConfiguration() {
        return getConfigData(pluginConfiguration, PluginConfiguration.class);
    }

    public static MacAuthConfiguration getMacAuthConfiguration() {
        return getConfigData(macAuthConfiguration, MacAuthConfiguration.class);
    }
}
