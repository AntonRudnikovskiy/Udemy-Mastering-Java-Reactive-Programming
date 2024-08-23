package org.example.sec02.assignment;

import org.example.common.Util;
import org.example.sec01.publisher.SubscriptionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Path path = Path.of("src/main/resources");

    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(() -> Files.readString(path.resolve(fileName)))
                .subscribeOn(Schedulers.boundedElastic())
                .onErrorResume(throwable -> {
                    log.error("Error: {}", throwable.getMessage());
                    return Mono.error(throwable);
                });
    }

    @Override
    public Mono<Void> write(String fileName, String context) {
        return Mono.fromRunnable(() -> {
                    try {
                        Files.writeString(path.resolve(fileName), context);
                        log.info("File {} written successfully", fileName);
                    } catch (IOException e) {
                        log.error("Failed to write to file {}: {}", fileName, e.getMessage());
                        throw new RuntimeException(e);
                    }
                });
    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> {
                    try {
                        if (Files.deleteIfExists(path.resolve(fileName))) {
                            log.info("File {} deleted successfully", fileName);
                        } else {
                            log.error("File {} doesn't exist", fileName);
                            throw new IOException("File doesn't exist");
                        }
                    } catch (IOException e) {
                        log.error("Failed to delete file {}: {}", fileName, e.getMessage());
                        throw new RuntimeException(e);
                    }
                })
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }

    public static void main(String[] args) {
        FileServiceImpl fileService = new FileServiceImpl();
        fileService.write("text", "sdfsfweftwef")
                .subscribe(Util.subscriber());

//        fileService.read("text.txt")
//               .doOnNext(content -> log.info("File content: {}", content))
//               .doOnError(e -> log.error("Error during read operation", e))
//               .subscribe();

//        Mono<Void> mono = fileService.delete("text");
//        mono.subscribe();
    }
}
