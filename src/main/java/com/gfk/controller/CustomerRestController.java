package com.gfk.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfk.dao.CustomerDAO;
import com.gfk.model.Customer;
import com.gfk.model.ErrorMessage;
import com.gfk.model.ResponseObject;

@RestController
public class CustomerRestController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerRestController.class);
	
	@Autowired
	private CustomerDAO customerDAO;

	/**
	 * 
	 * @return
	 */
	@GetMapping("/customers")
	public List getCustomers() {
		return customerDAO.list();
	}

	@GetMapping("/customers/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Customer customer = customerDAO.get(id);
		if (customer == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/customers")
	public ResponseEntity createCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
		
		ResponseEntity responseEntity = null;
		
		if(result.hasErrors()) {
			
			ResponseObject responseObject = new ResponseObject();
			responseObject.setRequestObject(customer);
			
			for(ObjectError error:result.getAllErrors()){
				String errorField = ((DefaultMessageSourceResolvable)error.getArguments()[0]).getDefaultMessage();
				logger.info(error.getObjectName() + " :" + error.getCode()  +" :" + ((DefaultMessageSourceResolvable)error.getArguments()[0]).getDefaultMessage() +" :" + error.getDefaultMessage());
				responseObject.getErrors().add(new ErrorMessage(errorField, error.getDefaultMessage()));
				
			}
			
			
			responseEntity = new ResponseEntity(responseObject, HttpStatus.BAD_REQUEST);
			
		}else{
			customerDAO.create(customer);
			responseEntity = new ResponseEntity(customer, HttpStatus.CREATED);
		}

		return responseEntity;
	}

	@DeleteMapping("/customers/{id}")
	public ResponseEntity deleteCustomer(@PathVariable Long id) {

		if (null == customerDAO.delete(id)) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(id, HttpStatus.OK);

	}

	@RequestMapping("/customers/{id}")
	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

		customer = customerDAO.update(id, customer);

		if (null == customer) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(customer, HttpStatus.OK);
	}

}
