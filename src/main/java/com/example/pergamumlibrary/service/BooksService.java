package com.example.pergamumlibrary.service;

import com.example.pergamumlibrary.api.model.Book;
import com.example.pergamumlibrary.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    @Autowired
    private BooksRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "author", "title"));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBookByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public void updateBookAuthor(Long id, String author) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(b -> {
            b.setAuthor(author);
            bookRepository.save(b);
        });
    }
}
