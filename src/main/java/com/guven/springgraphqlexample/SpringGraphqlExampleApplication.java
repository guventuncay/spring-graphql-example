package com.guven.springgraphqlexample;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringGraphqlExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphqlExampleApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
        return args -> {
            Author eric = new Author("Eric");
            authorRepository.save(eric);
            Book ddd = new Book("Domain Driven Design", "Addison-Wesley", eric);
            bookRepository.save(ddd);

            Author rod = new Author("Rod");
            authorRepository.save(rod);
            Book noEJB = new Book("J2EE Development without EJB", "Wrox", rod);
            bookRepository.save(noEJB);

            Author john = new Author("John");
            authorRepository.save(john);
            Book spring = new Book("Spring in Action", "Manning", john);
            bookRepository.save(spring);
        };
    }

}
