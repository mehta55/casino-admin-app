package com.nagarro.service;

import com.nagarro.dto.AdjustAmountResponse;
import com.nagarro.dto.BlockAmountResponse;
import com.nagarro.dto.CustomerResponse;
import com.nagarro.dto.LeaderBoardResponse;
import com.nagarro.dto.LoginResponse;

/**
 * This interface offers different services to APIController.
 *
 * @author sahilmehta02
 */
public interface APIService {

	/**
	 * This method call's CustomerDao's getCustomer with customerId received as
	 * parameter to check if any customer is present with this customerId.
	 * 
	 * @param customerId Unique ID of customer
	 * @return LoginResponse Object that has success status in it.
	 */
	public LoginResponse loginCustomer(String customerId);

	/**
	 * This method calls CustomerDao's getCustomer with customerId received as
	 * parameter to get customer details.
	 * 
	 * @param customerId ustomerId Unique ID of customer.
	 * @return CustomerResponse Object that has success status, customer name and
	 *         balance.
	 * 
	 */
	public CustomerResponse getCustomer(String customerId);

	/**
	 * This method calls CustomerDao's blockAmount method with customerId and amount
	 * to be blocked received as parameter.
	 * 
	 * @param customerId  Unique ID of customer
	 * @param blockAmount Amount to be blocked
	 * @return BlockAmountResponse Object having success status and updated
	 *         customer's balance.
	 */
	public BlockAmountResponse blockAmount(String customerId, int blockAmount);

	/**
	 * This method calls CustomerDao's adjustAmount method with customerId and
	 * amount to be adjusted received as parameter.
	 * 
	 * @param customerId   Unique ID of customer
	 * @param adjustAmount Amount to be adjusted
	 * @return AdjustAmountResponse Object having success status and updated
	 *         customer's balance.
	 */
	public AdjustAmountResponse adjustAmount(String customerId, int adjustAmount);

	/**
	 * This method calls CustomerDao's getLeaderBoard method to get top customers of
	 * the Roulette application. It receives a list of tuples from the CustomerDao
	 * which is then converted into LeaderBoardResponse by calling
	 * prepareLeaderBoardResponse method.
	 * 
	 * @return LeaderBoardResponse object having top 5 customers.
	 */
	public LeaderBoardResponse getLeaderBoard();
}
