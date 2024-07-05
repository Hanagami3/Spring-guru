package be.hanagami.spring6restmvc.service;

import be.hanagami.spring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    List<Customer> listCustomers();

    Customer getCustomerById(UUID uuid);
}
