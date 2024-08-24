package org.example.sec03;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 3, 6, 7);

        flux.subscribe(Util.subscriber("sub1"));
        flux.filter(i -> i < 7)
                .subscribe(Util.subscriber("sub2"));

        flux
                .filter(i -> i % 2 == 0)
                .map(i -> i + "a")
                .subscribe(Util.subscriber(""));
    }
}
