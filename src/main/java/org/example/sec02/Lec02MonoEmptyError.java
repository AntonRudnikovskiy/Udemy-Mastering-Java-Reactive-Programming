package org.example.sec02;

import org.example.common.Util;
import reactor.core.publisher.Mono;

public class Lec02MonoEmptyError {
    public static void main(String[] args) {
        getUsername(2).subscribe(Util.subscriber());
    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("user");
            case 2 -> Mono.empty(); // null
            default -> Mono.error(new RuntimeException("Invalid input"));
        };
    }
}
