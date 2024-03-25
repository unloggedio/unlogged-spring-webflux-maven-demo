package org.unlogged.springwebfluxdemo.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

//@Component
@Configuration
public class ContextEnrichmentFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (exchange.getResponse().getHeaders() != null) {
            exchange.getResponse()
                    .getHeaders().add("web-filter", "web-filter-test");
        }
        return chain.filter(exchange);
    }

    public Context updateContextWithQuoteHeader(Context context, ServerWebExchange exchange) {
        Context finalContext = context.put("quote_header", getQuoteHeader(exchange));
        return finalContext;
    }

    public Object getQuoteHeader(ServerWebExchange exchange) {
        return exchange.getAttribute("something");
    }
}
