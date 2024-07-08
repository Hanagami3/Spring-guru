package be.hanagami.spring6restmvc.repositories;

import be.hanagami.spring6restmvc.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//Avec CRUDRepository, on a pa accés des des features spécifiques de JPA
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
