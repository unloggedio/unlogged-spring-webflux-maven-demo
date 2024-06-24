package org.unlogged.springwebfluxdemo;

import org.pf4j.spring.SpringPluginManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginConfiguration {

    @Bean
    public SpringPluginManager pluginManager() {
        return new CustomPluginManager();
    }

    private static class CustomPluginManager extends SpringPluginManager {
        public CustomPluginManager() {
            super();
        }
    }

}
