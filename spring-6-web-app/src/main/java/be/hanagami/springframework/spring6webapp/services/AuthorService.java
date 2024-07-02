package be.hanagami.springframework.spring6webapp.services;

import be.hanagami.springframework.spring6webapp.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();
}
