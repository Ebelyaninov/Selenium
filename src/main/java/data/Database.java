package data;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:main.${path}.properties"})
public interface Database extends Config {
    @Key("db.use_ssh_tunnel")
    boolean useSshTunnel();

    @Key("db.ssh.tunnel.host")
    String sshTunnelHost();

    @Key("db.ssh.tunnel.port")
    String sshTunnelPort();

    @Key("db.ssh.tunnel.username")
    String sshTunnelUsername();

    @Key("db.ssh.tunnel.private_key.path")
    String sshTunnelPrivateKeyPath();

    @Key("db.user")
    String user();

    @Key("db.user.password")
    String userPassword();

    @Key("db.local.user")
    String localUser();

    @Key("db.local.password")
    String localPassword();

    @Key("gateway.db.host")
    String gateway();

    @Key("wallet.db.host")
    String wallet();

    @Key("tickets.db.host")
    String tickets();

    @Key("recurring-payments.db.host")
    String recurringPayments();

    @Key("postbank-bg-iban.db.host")
    String postbankBgIban();

    @Key("psd2_tests.db.host")
    String psd2Tests();

    @Key("wallet_frontend.db.host")
    String walletFrontend();

    @Key("delivery_api.db.host")
    String deliveryApi();

    @Key("anemometer.db.host")
    String anemometerApi();

    @Key("statistics.db.host")
    String statistics();

    @Key("statistics.db.local.host")
    String statisticsLocal();

    @Key("partner_frontend.db.host")
    String partnerFrontend();

    @Key("savings.db.host")
    String savings();

    @Key("evp_lt.db.host")
    String evpLt();

    @Key("api_currency.db.host")
    String apiCurrency();

    @Key("blacklist.db.host")
    String apiBlacklist();

    @Key("parcel_network.db.host")
    String parcelNetwork();

    @Key("accounting.db.host")
    String accounting();
}
