package com.nagarro.dto;

/**
 * This class is used for sending response of Block Amount Request. Success
 * property determines whether the request was successful or not. Balance
 * property determines the updated balance of user after the request is
 * processed.
 * 
 * @author sahilmehta02
 *
 */
public class BlockAmountResponse {

	private boolean success;
	private int balance;

	public BlockAmountResponse(boolean success, int balance) {
		this.success = success;
		this.balance = balance;
	}

	public boolean isSuccess() {
		return success;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
