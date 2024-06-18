package org.unlogged.springwebfluxdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RestController
@RequestMapping("/api/files")
public class FilesController {

    private String filePath = "src/main/resources/testFile";

    @PostMapping(value = "/create", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<Void> createFile(@RequestBody(required = false) String initialContent) {
        Path path = Paths.get(filePath);
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
            if (initialContent != null && !initialContent.isEmpty()) {
                Files.writeString(path, initialContent, StandardOpenOption.TRUNCATE_EXISTING);
            }
            return Mono.empty();
        } catch (IOException e) {
            return Mono.error(e);
        }
    }

    @GetMapping(value = "/read", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> readFile() {
        Path path = Paths.get(filePath);
        try {
            String content = Files.readString(path);
            return Mono.just(content);
        } catch (IOException e) {
            return Mono.error(e);
        }
    }

    @PostMapping(value = "/write", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<Void> writeFile(@RequestBody String content) {
        Path path = Paths.get(filePath);
        try {
            Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return Mono.empty();
        } catch (IOException e) {
            return Mono.error(e);
        }
    }

    @PostMapping(value = "/write-with-charset", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<Void> writeFileWithCharset(@RequestBody String content,
                                           @RequestParam String charsetName) {
        Path path = Paths.get(filePath);
        Charset charset = Charset.forName(charsetName);
        try {
            Files.writeString(path, content, charset, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return Mono.empty();
        } catch (IOException e) {
            return Mono.error(e);
        }
    }
}
