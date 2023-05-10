package data;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:main.${path}.properties"})
public interface PluginConfiguration extends Config {
    @Key("plugin.project.id")
    String getProjectId();
    @Key("plugin.project.pass")
    String getProjectPass();
}