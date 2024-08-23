package org.example.sec02.assignment;

import reactor.core.publisher.Mono;

import java.io.IOException;

public interface FileService {
    Mono<String> read(String fileName) throws IOException;
    Mono<Void> write(String fileName, String context) throws IOException;
    Mono<Void> delete(String fileName) throws IOException;
}
