package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
}
