package org.unlogged.springwebfluxdemo;


import org.reactivestreams.Subscription;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Operators;
import reactor.util.context.Context;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Configuration
public class MDCConfig {

    private static final String MDC_CONTEXT_REACTOR_KEY = "MDCConfig";

    @PostConstruct
    void contextOperatorHook() {
        Hooks.onEachOperator(MDC_CONTEXT_REACTOR_KEY, Operators.lift((sc, subscriber) -> new MdcContextLifter<>(subscriber)));
    }

    @PreDestroy
    void cleanupHook() {
        Hooks.resetOnEachOperator(MDC_CONTEXT_REACTOR_KEY);
    }

    static class MdcContextLifter<T> implements CoreSubscriber<T> {

        private final CoreSubscriber<T> coreSubscriber;

        public MdcContextLifter(CoreSubscriber<T> coreSubscriber) {
            this.coreSubscriber = coreSubscriber;
        }

        @Override
        public void onSubscribe(Subscription s) {
            this.copyToMdc(coreSubscriber.currentContext());
            coreSubscriber.onSubscribe(s);
        }

        @Override
        public void onNext(T t) {
            this.copyToMdc(coreSubscriber.currentContext());
            coreSubscriber.onNext(t);
        }

        @Override
        public void onError(Throwable throwable) {
            coreSubscriber.onError(throwable);
        }

        @Override
        public void onComplete() {
            coreSubscriber.onComplete();
        }

        @Override
        public Context currentContext() {
            return coreSubscriber.currentContext();
        }

        private void copyToMdc(Context context) {
            MDC.clear();
        }
    }
}
