package com.bookstore.jpa.services;

import com.bookstore.jpa.dtos.BookRecordDto;
import com.bookstore.jpa.models.AuthorModel;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired BookRepository bookRepository;
    @Autowired AuthorRepository authorRepository;
    @Autowired PublisherRepository publisherRepository;


    public List<BookModel> getAllBooks(){
        return bookRepository.findAll();
    }

    @Transactional
    public BookModel saveBook(BookRecordDto request){
        BookModel book = new BookModel();
        book.setTitle(request.title());
        book.setPublisher(publisherRepository.findById(request.publisherId()).get());
        book.setAuthors(authorRepository.findAllById(request.authorIds()).stream().collect(Collectors.toSet()));

        ReviewModel review = new ReviewModel();
        review.setComment(request.reviewComment());
        review.setBook(book);
        book.setReview(review);

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);
    }

}
