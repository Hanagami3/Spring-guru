package be.hanagami.springframework.spring6webapp.repositories;

import be.hanagami.springframework.spring6webapp.domain.Publisher;
import org.springframework.data.repository.CrudRepository;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
