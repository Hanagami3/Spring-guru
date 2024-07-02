package be.hanagami.springframework.spring6webapp.repositories;

import be.hanagami.springframework.spring6webapp.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
