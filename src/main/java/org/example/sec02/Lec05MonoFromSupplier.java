package org.example.sec02;

import org.example.common.Util;
import org.example.sec01.publisher.SubscriptionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3);
        Mono.fromSupplier(() -> sum(integers)).subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        return list.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
