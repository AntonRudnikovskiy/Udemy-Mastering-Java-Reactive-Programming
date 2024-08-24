package org.example.sec03;

import org.example.common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {

    public static void main(String[] args) {
        Flux.range(2, 10)
                .subscribe(Util.subscriber());
    }
}
