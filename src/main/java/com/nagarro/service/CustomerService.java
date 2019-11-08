package com.nagarro.service;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.nagarro.model.Customer;

/**
 * This interface that offers services to the AdminController.
 * 
 * @author sahilmehta02
 *
 */
public interface CustomerService {

	/**
	 * This method accepts customer details in parameters, validate it for age > 18
	 * and returns a List of string with errors. In case the list is empty, then the
	 * customer is validated successfully.
	 * 
	 * @param name          Customer's name
	 * @param email         Customer's email
	 * @param dob           Customer's date of birth
	 * @param contactNumber Customer's contact number
	 * @param idProof       Customer's ID proof image
	 * @return List of String describing where the validation failed. In case the
	 *         list is empty then the customer is validated successfully.
	 */
	public List<String> validateNewCustomer(String name, String email, String dob, String contactNumber,
			CommonsMultipartFile idProof);

	/**
	 * This method adds new customer by calling CustomerDao's addCustomer method
	 * after the customer is validated successfully.
	 * 
	 * @param name          Customer's name
	 * @param email         Customer's email
	 * @param dob           Customer's date of birth
	 * @param contactNumber Customer's contact number
	 * @param idProof       Customer's ID proof image
	 * @return true if customer is added to DB successfully and false if not.
	 */
	public boolean addNewCustomer(String name, String email, String dob, String contactNumber,
			CommonsMultipartFile idProof);

	/**
	 * This method gets the list of customer by calling CustomerDao's getCustomer
	 * method.
	 * 
	 * @param pageNo              requested page number of customer list.
	 * @param nameFilter          used for filtering customer by name.
	 * @param emailFilter         used for filtering customer by email.
	 * @param contactNumberFilter used for filtering customer by contact number.
	 * @return List of customers
	 */
	public List<Customer> getCustomers(int page_no, String nameFilter, String emailFilter, String contactNumberFilter);

	/**
	 * This method gets the total number of pages for the customer list retrieved
	 * with applied filters. It calls CustomerDao's getCustomerCount to get size of
	 * customer list with applied filters and divides the size by page size to get
	 * the total number of pages.
	 * 
	 * @param nameFilter          used for filtering customer by name.
	 * @param emailFilter         used for filtering customer by email.
	 * @param contactNumberFilter used for filtering customer by contact number.
	 * @return total number of pages
	 */
	public int getTotalPages(String nameFilter, String emailFilter, String contactNumberFilter);

	/**
	 * This method adds recharge amount to customer balance by calling CustomerDao's
	 * updateCustomerBalance.
	 * 
	 * @param customerId     Unique ID of customer
	 * @param rechargeAmount Amount of Recharge
	 * @return True if the customer's account is recharged successfully and false if
	 *         not.
	 */
	public boolean rechargeCustomerAccount(String customerId, int rechargeAmount);
}
