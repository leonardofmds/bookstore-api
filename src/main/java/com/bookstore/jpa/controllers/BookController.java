package com.bookstore.jpa.controllers;

import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookstore/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<BookModel> saveBook(@RequestBody BookRecordDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(request));
    }

    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable UUID id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
