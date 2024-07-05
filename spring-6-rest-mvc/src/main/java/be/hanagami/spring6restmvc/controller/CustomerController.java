package be.hanagami.spring6restmvc.controller;

import be.hanagami.spring6restmvc.model.Customer;
import be.hanagami.spring6restmvc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    final private CustomerService customerService;

    @RequestMapping (method = RequestMethod.GET)
    public List<Customer> listAllCustomers() {
        return customerService.getAllCustomers();
    }

    //de '/' is niet nodig, doet spring het voor ons
    @RequestMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId")UUID customerId){
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    //Parse the JSON body into a customer object
    public ResponseEntity handlePost (@RequestBody Customer customer){

        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }


}
















