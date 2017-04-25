package com.gfk.model;

public class ErrorMessage {
	private String errorField;
	private String errorMessage;
	public ErrorMessage(String errorField, String errorMessage) {
		super();
		this.errorField = errorField;
		this.errorMessage = errorMessage;
	}
	
	public ErrorMessage() {
		// TODO Auto-generated constructor stub
	}

	public String getErrorField() {
		return errorField;
	}

	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	


}
