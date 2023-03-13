package com.example.pergamumlibrary.api.controller;

import com.example.pergamumlibrary.api.model.Book;
import com.example.pergamumlibrary.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addBook")
public class BooksController {
    private BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    // Adding a new book – will add a new book to list of library books
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book newBook = booksService.addBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    // Get all books from thelibrarysorted by the author and by their title
    @GetMapping("/getBooks")
    public ResponseEntity<List<Book>> getAllBooksSortedByTitle() {
        List<Book> books = booksService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Delete a book from the library
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        booksService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // Search for a book by title
    @GetMapping("/searchBookByTitle")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam("title") String title) {
        List<Book> books = booksService.searchBookByTitle(title);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Update the author for a book
    @PutMapping("/{author}")
    public ResponseEntity<Void> updateBookAuthor(@PathVariable Long id, @RequestParam String author) {
        booksService.updateBookAuthor(id, author);
        return ResponseEntity.noContent().build();
    }
}
