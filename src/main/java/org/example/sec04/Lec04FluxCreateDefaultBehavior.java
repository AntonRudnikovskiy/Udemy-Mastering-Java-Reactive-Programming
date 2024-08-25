package org.example.sec04;

import org.example.common.Util;
import org.example.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateDefaultBehavior {
    private static final Logger log = LoggerFactory.getLogger(SubscriberImpl.class);

    public static void main(String[] args) {
        Subscriber<Object> subscriber = Util.subscriber();
        Flux.<String>create(fluxSink -> fluxSink.onRequest(request -> {
            for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                String firstName = Util.faker().name().firstName();
                log.info("generated: {}", firstName);
                fluxSink.next(firstName);
            }
        })).subscribe(subscriber);
    }
}
