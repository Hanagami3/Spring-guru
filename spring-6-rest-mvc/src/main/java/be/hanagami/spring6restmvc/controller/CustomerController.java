package be.hanagami.spring6restmvc.controller;

import be.hanagami.spring6restmvc.model.CustomerDTO;
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
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    final private CustomerService customerService;

    @GetMapping (CUSTOMER_PATH)
    public List<CustomerDTO> listAllCustomers() {
        return customerService.getAllCustomers();
    }


    //de '/' is niet nodig, doet spring het voor ons
    @GetMapping(CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("customerId")UUID customerId){
        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping(CUSTOMER_PATH)
    //Parse the JSON body into a customer object
    public ResponseEntity handlePost (@RequestBody CustomerDTO customer){

        CustomerDTO savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("customerId")UUID customerId,
                                     @RequestBody CustomerDTO customer){

        if (customerService.updateCostumerById(customerId, customer).isEmpty()){
            throw new NotFoundException();
        };

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteById(@PathVariable("customerId")UUID customerId){

        if (!customerService.deleteCustomerById(customerId)){
            throw new NotFoundException();
        };

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomerPatchById(@PathVariable("customerId") UUID customerId,
                                   @RequestBody CustomerDTO customer){

        if (customerService.patchCostumerById(customerId, customer).isEmpty()){
            throw new NotFoundException();
        };

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
















