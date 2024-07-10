package be.hanagami.spring6restmvc.service;

import be.hanagami.spring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    Optional<CustomerDTO> getCustomerById(UUID uuid);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    Optional<CustomerDTO> updateCostumerById(UUID customerId, CustomerDTO customer);

    Boolean deleteCustomerById(UUID customerId);

    void patchCostumerById(UUID customerId, CustomerDTO customer);
}
