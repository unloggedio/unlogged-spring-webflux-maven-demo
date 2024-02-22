package org.unlogged.springwebfluxdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.accept.RequestedContentTypeResolverBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public void configureContentTypeResolver(RequestedContentTypeResolverBuilder builder) {

    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        configurer.defaultCodecs().maxInMemorySize(512 * 1024);
    }

    @Bean
    public Scheduler scheduler() {
        return Schedulers.newBoundedElastic(
                Schedulers.DEFAULT_BOUNDED_ELASTIC_SIZE,
                Schedulers.DEFAULT_BOUNDED_ELASTIC_QUEUESIZE,
                "DEFAULT-THREAD-POOL-UWF");
    }
}