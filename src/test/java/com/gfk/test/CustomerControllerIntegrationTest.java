package com.gfk.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import com.gfk.config.WebConfig;
import com.gfk.model.Customer;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
public class CustomerControllerIntegrationTest {

	private static final String BASE_URI = "http://localhost:8080/springmvcdemo/customers";
    private static final int UNKNOWN_ID = Integer.MAX_VALUE;

    @Autowired
    private RestTemplate template;

    // =========================================== Get All Customers ==========================================

    @Test
    public void test_get_all_success(){
        ResponseEntity<Customer[]> response = template.getForEntity(BASE_URI, Customer[].class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
	
    
 // =========================================== Get Customer By ID =========================================

    @Test
    public void test_get_by_id_success(){
        ResponseEntity<Customer> response = template.getForEntity(BASE_URI + "/101", Customer.class);
        Customer customer = response.getBody();
        assertThat(customer.getId().intValue(), is(101));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void test_get_by_id_failure_not_found(){
        try {
            ResponseEntity<Customer> response = template.getForEntity(BASE_URI + "/" + UNKNOWN_ID, Customer.class);
            fail("should return 404 not found");
        } catch (HttpClientErrorException e){
            assertThat(e.getStatusCode(), is(HttpStatus.NOT_FOUND));
        }
    }
}
