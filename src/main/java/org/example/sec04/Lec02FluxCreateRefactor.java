package org.example.sec04;

import org.example.common.Util;
import org.example.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactor {

    public static void main(String[] args) {
        NameGenerator generator = new NameGenerator();
        Flux<String> stringFlux = Flux.create(generator);

        stringFlux.subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }
}
