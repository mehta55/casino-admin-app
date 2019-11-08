package com.nagarro.dto;

/**
 * This class is used for sending response of login Request. Success property
 * determines whether the login was successful or not.
 * 
 * @author sahilmehta02
 *
 */
public class LoginResponse {

	private boolean success;

	public LoginResponse(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
