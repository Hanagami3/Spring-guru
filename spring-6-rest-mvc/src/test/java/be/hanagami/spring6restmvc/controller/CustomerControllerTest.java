package be.hanagami.spring6restmvc.controller;

import be.hanagami.spring6restmvc.model.Beer;
import be.hanagami.spring6restmvc.model.Customer;
import be.hanagami.spring6restmvc.service.CustomerService;
import be.hanagami.spring6restmvc.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    //on peut l'utiliser dans plusieur methode dont update à la place de any(UUI.class)
    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    @BeforeEach
    void setUp() {
        customerServiceImpl = new CustomerServiceImpl();
    }



    @Test
    void testGetCustomerById() throws Exception {
        Customer customerTest = customerServiceImpl.getAllCustomers().get(0);

        given(customerService.getCustomerById(customerTest.getId())).willReturn(customerTest);

        mockMvc.perform(get("/api/v1/customer/" + customerTest.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(customerTest.getId().toString())))
                .andExpect(jsonPath("$.name", is(customerTest.getName())));
    }

    @Test
    void testGetAllCustomers() throws Exception {

        given(customerService.getAllCustomers()).willReturn(customerServiceImpl.getAllCustomers());

        mockMvc.perform(get("/api/v1/customer").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void testCreateNewCustomer() throws Exception {
        Customer customerTest = customerServiceImpl.getAllCustomers().get(0);
        customerTest.setVersion(null);
        customerTest.setId(null);

        given(customerService.saveNewCustomer(any(Customer.class)))
                .willReturn(customerServiceImpl.getAllCustomers().get(1));

        mockMvc.perform(post("/api/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerTest)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Customer customerTest = customerServiceImpl.getAllCustomers().get(0);

        mockMvc.perform(put("/api/v1/customer/" + customerTest.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerTest)))
                .andExpect(status().isNoContent());

        verify(customerService).updateCostumerById(any(UUID.class), any(Customer.class));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        Customer customerTest = customerServiceImpl.getAllCustomers().get(0);

        mockMvc.perform(delete("/api/v1/customer/" + customerTest.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());


        verify(customerService).deleteCustomerById(uuidArgumentCaptor.capture());

        assertThat(customerTest.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }
}
