package com.gfk.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseObject {
	Object requestObject;
	List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
	
	public ResponseObject(Object requestObject, List<ErrorMessage> errors) {
		super();
		this.requestObject = requestObject;
		this.errors = errors;
	}
	
	public ResponseObject() {
		// TODO Auto-generated constructor stub
	}
	
	public Object getRequestObject() {
		return requestObject;
	}
	
	public void setRequestObject(Object requestObject) {
		this.requestObject = requestObject;
	}
	
	public List<ErrorMessage> getErrors() {
		return errors;
	}
	
	public void setErrors(List<ErrorMessage> errors) {
		this.errors = errors;
	}
	
	
	

}
