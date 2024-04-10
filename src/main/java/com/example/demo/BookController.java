package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable UUID id) {
        var filteredBooks = books.stream().filter(book -> book.getId().equals(id));
        return filteredBooks.findFirst().get();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        book.setId(UUID.randomUUID());
        books.add(book);

        return book;
    }

    @PostMapping("/third-party")
    public Book createBookWithThirdPartyContent(@RequestBody Book book){
        WebClient client = WebClient.builder()
                .baseUrl("https://lorenz-hello-world.azurewebsites.net")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        var response = client.get().uri("/").retrieve().bodyToMono(String.class).block();
        book.setId(UUID.randomUUID());
        book.setDescription(response);
        books.add(book);

        return book;
    }
}
