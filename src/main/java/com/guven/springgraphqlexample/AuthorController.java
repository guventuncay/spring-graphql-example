package com.guven.springgraphqlexample;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @QueryMapping//(name = "authors") // name is required if graphql schema type is not matching with method name
    Iterable<Author> authors(){
        return authorRepository.findAll();
    }

    @QueryMapping
    Optional<Author> authorById(@Argument Long id){
        return authorRepository.findById(id);
    }

    @MutationMapping
    Book addBook(@Argument BookInput book) {
        Author author = authorRepository.findById(book.authorId()).orElseThrow(() -> new RuntimeException("Author not found"));
        Book bookToBeSaved = new Book(book.title(), book.publisher(), author);
        return bookRepository.save(bookToBeSaved);
    }



}
