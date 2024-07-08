package be.hanagami.spring6restmvc.mappers;

import be.hanagami.spring6restmvc.entities.Customer;
import be.hanagami.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO dto);

    CustomerDTO CustomerToCustomerDTO(Customer customer);

}
