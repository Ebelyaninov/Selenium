package data;

import org.aeonbits.owner.Config;

public interface Directories extends Config {
    @DefaultValue("../Selenium/")
    String mainCore();
    @DefaultValue("${mainCore}/src/main/resources")
    String mainResources();
    @DefaultValue("${mainResources}/data/")
    String data();
    @DefaultValue("${mainResources}/localization/")
    String localization();
}
