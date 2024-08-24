package org.example.sec03;

import org.example.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Flux<Integer> flux = Flux.fromStream(() -> list.stream());
        flux.subscribe(Util.subscriber());
        flux.subscribe(Util.subscriber());
    }
}
