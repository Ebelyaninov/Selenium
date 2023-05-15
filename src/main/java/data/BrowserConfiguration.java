package data;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:main.${path}.properties"})
public interface BrowserConfiguration extends Config {

    @Key("bank.application.url")
    String getApplicationUrl();

    @Key("tickets.url")
    String getTicketsUrl();

    @Key("statistics.application.url")
    String getStatisticsUrl();

    @Key("timeout.seconds")
    int timeoutSeconds();

    @Key("short.timeout.seconds")
    int shortTimeoutSeconds();

    @Key("pooling.milliseconds")
    int intervalMilliseconds();

    @Key("wordpress.url")
    String getWordpressUrl();

    @Key("wordpress.user")
    String getWordpressUser();

    @Key("wordpress.pass")
    String getWordpressPass();

    @Key("admin.application.url")
    String getAdminApplicationUrl();

    @Key("novaposhta.ua")
    String getNovaPoshtaUrl();
}