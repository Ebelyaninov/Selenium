package data;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:main.${path}.properties"})
public interface LogConfiguration extends Config {

    @Key("api.request.log")
    String apiLogMethod();

    @Key("api.allure.attach")
    Boolean allureApiCurlAttaching();

    @Key("api.human.attach")
    Boolean allureApiHumanAttaching();
}
