package com.nagarro.dao;

import java.util.List;

import javax.persistence.Tuple;

import com.nagarro.model.Customer;

public interface CustomerDao {

	/**
	 * This method counts total number of customers that matches the filters
	 * received in parameter.
	 * 
	 * @param nameFilter          used for filtering customer by name.
	 * @param emailFilter         used for filtering customer by email.
	 * @param contactNumberFilter used for filtering customer by contact number.
	 * @return count of customers that matches with the filters
	 */
	public int getCustomerCount(String nameFilter, String emailFilter, String contactNumberFilter);

	/**
	 * This method retrieves customers from DB that matches with the filter received
	 * in parameters.
	 * 
	 * @param offset              starting point of customer list from all the
	 *                            customer that matches with the filter.
	 * @param limit               number of customers to be returned
	 * @param nameFilter          used for filtering customer by name.
	 * @param emailFilter         used for filtering customer by email.
	 * @param contactNumberFilter used for filtering customer by contact number.
	 * @return list of customers that matches with the filters
	 */
	public List<Customer> getCustomers(int offset, int limit, String nameFilter, String emailFilter,
			String contactNumberFilter);

	/**
	 * This method adds customer object to the DB.
	 * 
	 * @param customer Customer object to be added
	 * @return True if customer is added successfully to DB and false if not.
	 */
	public boolean addCustomer(Customer customer);

	/**
	 * This method retrieves customer object from DB that has the customerId as the
	 * one received as parameter.
	 * 
	 * @param id Unique ID of customer
	 * @return Customer object if customer is found and null if no customer is found
	 *         with customerId received as parameter.
	 */
	public Customer getCustomer(String id);

	/**
	 * This method updates the balance of customer that matches with customerId
	 * received as parameter.
	 * 
	 * @param id     Unique ID of customer
	 * @param amount Amount to be updated.
	 * @return True if balance is updated successfully and false if not
	 */
	public boolean updateCustomerBalance(String id, int amount);

	/**
	 * This method blocks the balance of customer that matches with customerId
	 * received as parameter. In case the customer do not has sufficient amount,
	 * false is returned.
	 * 
	 * @param id     Unique ID of customer
	 * @param amount Amount to be blocked.
	 * @return True if balance is blocked successfully and false if not
	 */
	public boolean blockAmount(String id, int blockamount);

	/**
	 * This method adjusts the balance of customer that matches with customerId
	 * received as parameter.
	 * 
	 * @param id           Unique ID of customer
	 * @param adjustAmount Amount to be adjusted. Can be positive or negative.
	 * @return True if balance is adjusted successfully and false if not
	 */
	public boolean adjustAmount(String id, int adjustAmount);

	/**
	 * This method fetches top 5 customers from the DB with highest balance.
	 * 
	 * @return List of tuples. A tuple has customer's name and balance as
	 *         attributes.
	 */
	public List<Tuple> getLeaderBoard();
}
