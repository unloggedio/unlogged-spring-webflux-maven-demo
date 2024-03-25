package org.unlogged.springwebfluxdemo.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FilterConfig {

    @Bean
    public ContextEnrichmentFilter requestIdFilter() {
        return new ContextEnrichmentFilter();
    }
}