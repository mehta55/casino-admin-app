package com.nagarro.dto;

/**
 * This class is used for sending response of GetCustomer Request. Success
 * property determines whether the request was successful or not. Name property
 * determines the name of the customer that was requested. Balance property
 * determines the balance of customer that was requested.
 * 
 * @author sahilmehta02
 *
 */
public class CustomerResponse {

	private boolean success;
	private String name;
	private int balance;

	public CustomerResponse(boolean customerFound) {
		this.success = customerFound;
	}

	public CustomerResponse(boolean success, String name, int balance) {
		this.success = success;
		this.name = name;
		this.balance = balance;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
