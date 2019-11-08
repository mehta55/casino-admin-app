package com.nagarro.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.nagarro.dao.CustomerDao;
import com.nagarro.model.Customer;
import com.nagarro.util.Constants;

public class CustomerServiceImpl implements CustomerService {

	final static Logger LOG = Logger.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	public List<String> validateNewCustomer(String name, String email, String dob, String contactNumber,
			CommonsMultipartFile idProof) {

		LOG.info("Validating new customer : " + name);

		List<String> errors = new ArrayList<String>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate birthday = LocalDate.parse(dob, formatter);
		LocalDate today = LocalDate.now();
		Period p = Period.between(birthday, today);

		int age = p.getYears();

		if (age < 18) {
			errors.add("Customer must be above 18 years.");
		}

		return errors;
	}

	public boolean addNewCustomer(String name, String email, String dob, String contactNumber,
			CommonsMultipartFile idProof) {

		LOG.info("Adding new customer :" + name);

		Customer customer = createCustomerObject(name, email, dob, contactNumber, idProof);

		return customerDao.addCustomer(customer);
	}

	public List<Customer> getCustomers(int page_no, String nameFilter, String emailFilter, String contactNumberFilter) {

		LOG.info("Retreiving customers for page :" + page_no + ", nameFilter : " + nameFilter + ", emailFilter : "
				+ emailFilter + ", contactNumberFilter : " + contactNumberFilter);

		int offset = (page_no - 1) * Constants.PAGE_SIZE;

		return customerDao.getCustomers(offset, Constants.PAGE_SIZE, nameFilter, emailFilter, contactNumberFilter);
	}

	public int getTotalPages(String nameFilter, String emailFilter, String contactNumberFilter) {

		LOG.info("Retreiving total pages for nameFilter : " + nameFilter + ", emailFilter : " + emailFilter
				+ ", contactNumberFilter :" + contactNumberFilter);

		int customerCount = customerDao.getCustomerCount(nameFilter, emailFilter, contactNumberFilter);

		return customerCount % Constants.PAGE_SIZE == 0 ? customerCount / Constants.PAGE_SIZE
				: customerCount / Constants.PAGE_SIZE + 1;
	}

	public boolean rechargeCustomerAccount(String customerId, int rechargeAmount) {

		LOG.info("Recharge customerId :" + customerId);

		return customerDao.updateCustomerBalance(customerId, rechargeAmount);

	}

	private Customer createCustomerObject(String name, String email, String dob, String contactNumber,
			CommonsMultipartFile idProof) {

		LOG.info("Creating customer-object for customer :" + name);

		Customer customer = new Customer();

		customer.setId(name.substring(0, 3) + dob.substring(0, 4) + '@'
				+ ((int) (Math.random() * Constants.RANDOM_NUMBER_LENGTH)));
		customer.setName(name);
		customer.setEmail(email);
		customer.setDateOfBirth(dob);
		customer.setContactNumber(contactNumber);
		customer.setBalance(500);
		customer.setBlockedBalance(0);

		byte[] idProofContent = idProof.getBytes();
		Blob idProofBlob = null;
		try {
			idProofBlob = new SerialBlob(idProofContent);
		} catch (SerialException e) {
			LOG.error("SerialException : ", e);
		} catch (SQLException e) {
			LOG.error("SQLException : ", e);
		}

		customer.setIdProofImage(idProofBlob);

		return customer;
	}

}
