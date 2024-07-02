package be.hanagami.springframework.spring6webapp.services;

import be.hanagami.springframework.spring6webapp.domain.Book;

public interface BookService {

    Iterable<Book> findAll();
}
