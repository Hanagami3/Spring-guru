package be.hanagami.spring6restmvc.service;

import be.hanagami.spring6restmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<UUID, CustomerDTO> customerMap;

//    public CustomerServiceImpl(){
//        this.customerMap = new HashMap<>();
//
//        CustomerDTO customer1 = CustomerDTO.builder()
//                .id(UUID.randomUUID())
//                .name("customer1")
//                .version(1)
//                .createdDate(LocalDateTime.now())
//                .lastModifiedDate(LocalDateTime.now())
//                .build();
//
//        CustomerDTO customer2 = CustomerDTO.builder()
//                .id(UUID.randomUUID())
//                .name("customer2")
//                .version(1)
//                .createdDate(LocalDateTime.now())
//                .lastModifiedDate(LocalDateTime.now())
//                .build();
//
//        CustomerDTO customer3 = CustomerDTO.builder()
//                .id(UUID.randomUUID())
//                .name("customer3")
//                .version(1)
//                .createdDate(LocalDateTime.now())
//                .lastModifiedDate(LocalDateTime.now())
//                .build();
//
//        customerMap.put(customer1.getId(), customer1);
//        customerMap.put(customer2.getId(), customer2);
//        customerMap.put(customer3.getId(), customer3);
//    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID uuid) {
        return Optional.of(customerMap.get(uuid));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {

        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name(customer.getName())
                .version(1)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        customerMap.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCostumerById(UUID customerId, CustomerDTO customer) {

        CustomerDTO existingCustomer = customerMap.get(customerId);
        existingCustomer.setName(customer.getName());

        //customerMap.put(existingCustomer.getId(), existingCustomer);

    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerMap.remove(customerId);
    }

    @Override
    public void patchCostumerById(UUID customerId, CustomerDTO customer) {
        CustomerDTO existingCustomer = customerMap.get(customerId);

        if (StringUtils.hasText(customer.getName())){
            existingCustomer.setName(customer.getName());
        }


    }

}
