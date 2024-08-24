package org.example.sec03;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 3, 6, 7);

        flux.subscribe(Util.subscriber());
    }
}
