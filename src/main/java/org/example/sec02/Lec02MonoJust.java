package org.example.sec02;

import org.example.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("hello");
        SubscriberImpl subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);

        subscriber.getSubscription().request(10);

        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();

        save(Mono.just("hello"));
    }

    private static void save(Publisher<String> publisher) {

    }
}
