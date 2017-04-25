package com.gfk.test;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gfk.config.WebConfig;
import com.gfk.controller.CustomerRestController;
import com.gfk.dao.CustomerDAO;
import com.gfk.model.Customer;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class CustomerRestControllerUnitTest {

	private MockMvc mockMvc;

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private CustomerRestController customerController;
    
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .build();
    }

    // =========================================== Get All Customers ==========================================

    @Test
    public void test_get_all_success() throws Exception {
       
    	List<Customer> customers = Arrays.asList(
        		new Customer(101, "John", "Doe", "djohn@gmail.com", "121-232-3435"),
        		new Customer(201, "Russ", "Smith", "sruss@gmail.com", "343-545-2345"));

        when(customerDAO.list()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(101)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[1].id", is(201)))
                .andExpect(jsonPath("$[1].firstName", is("Russ")));

        verify(customerDAO, times(1)).list();
        verifyNoMoreInteractions(customerDAO);
    }
    
    
    //===========================================  Get Customer By Id    ==============
    
    
    @Test
    public void test_get_by_id_success() throws Exception {
        Customer customer = new Customer(101, "John", "Doe", "djohn@gmail.com", "121-232-3435");

        when(customerDAO.get(101l)).thenReturn(customer);

        mockMvc.perform(get("/customers/{id}", 101))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(101)))
                .andExpect(jsonPath("$.email", is("djohn@gmail.com")));

        verify(customerDAO, times(1)).get(101l);
        verifyNoMoreInteractions(customerDAO);
    }

    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {
        when(customerDAO.get(101l)).thenReturn(null);

        mockMvc.perform(get("/customers/{id}", 101))
                .andExpect(status().isNotFound());

        verify(customerDAO, times(1)).get(101l);
        verifyNoMoreInteractions(customerDAO);
    }
    

	
}
