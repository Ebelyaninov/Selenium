package data;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:main.${path}.properties"})
public interface MacAuthConfiguration extends Config {
    @Key("mokejimai.mac.id")
    String mokejimaiMacId();

    @Key("mokejimai.mac.secret")
    String mokejimaiMacSecret();
}