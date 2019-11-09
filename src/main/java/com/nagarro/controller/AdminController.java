package com.nagarro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.model.Customer;
import com.nagarro.service.CustomerService;

/**
 * This controller handles requests from the Administrator application.
 * 
 * @author sahilmehta02
 *
 */
@Controller
public class AdminController {

	final static Logger LOG = Logger.getLogger(AdminController.class);

	@Autowired
	private CustomerService customerService;

	/**
	 * This method accepts GET request for /home and returns home page.
	 * 
	 * @return ModelAndView Object with home view and current page name (for
	 *         activating it in the navigation bar).
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getHome() {

		LOG.info("Recieved GET request for /home");

		ModelAndView mv = new ModelAndView();
		mv.addObject("currPage", "nav-home");
		mv.setViewName("home");
		return mv;
	}

	/**
	 * This method accepts GET request for /register and return register page that
	 * has registration form for new customer.
	 * 
	 * @return ModelAndView Object with register view and current page name (for
	 *         activating it in the navigation bar).
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getRegister() {

		LOG.info("Recieved GET request for /register");

		ModelAndView mv = new ModelAndView();
		mv.addObject("customer", new Customer());
		mv.addObject("currPage", "nav-register");
		mv.setViewName("register");
		return mv;
	}

	/**
	 * This method accepts GET request for /customers and return customers page that
	 * has the list of registered customers with their details and Administrator can
	 * filter customer and recharge their account balance. This method uses filter
	 * data inside the request URL.
	 * 
	 * @param pageNo              requested page number of customer list.
	 * @param nameFilter          used for filtering customer by name.
	 * @param emailFilter         used for filtering customer by email.
	 * @param contactNumberFilter used for filtering customer by contact number.
	 * @return ModelAndView Object with customer list (single page), page number of
	 *         customer list, total number of pages of full customer list, filter
	 *         details, customers view and current page name (for activating it in
	 *         the navigation bar).
	 * 
	 */
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public ModelAndView getCustomers(@RequestParam("page") int pageNo,
			@RequestParam(value = "nameFilter", defaultValue = "", required = false) String nameFilter,
			@RequestParam(value = "emailFilter", defaultValue = "", required = false) String emailFilter,
			@RequestParam(value = "contactNumberFilter", defaultValue = "", required = false) String contactNumberFilter) {

		LOG.info("Recieved GET request for /customers");

		ModelAndView mv = new ModelAndView();

		List<Customer> customers = customerService.getCustomers(pageNo, nameFilter, emailFilter, contactNumberFilter);
		int totalPages = customerService.getTotalPages(nameFilter, emailFilter, contactNumberFilter);

		mv.addObject("nameFilter", nameFilter);
		mv.addObject("emailFilter", emailFilter);
		mv.addObject("contactNumberFilter", contactNumberFilter);
		mv.addObject("customers", customers);
		mv.addObject("currPage", "nav-customers");
		mv.addObject("pageNo", pageNo);
		mv.addObject("totalPages", totalPages);
		mv.setViewName("customers");
		return mv;
	}

	/**
	 * This method accepts POST request for /customers and return customers page
	 * that has the list of registered customers with their details and
	 * Administrator can filter customer and recharge their account balance. This
	 * method uses filter data inside request body.
	 * 
	 * @param pageNo              requested page number of customer list.
	 * @param nameFilter          used for filtering customer by name.
	 * @param emailFilter         used for filtering customer by email.
	 * @param contactNumberFilter used for filtering customer by contact number.
	 * @return ModelAndView Object with customer list (single page), page number of
	 *         customer list, total number of pages of full customer list, filter
	 *         details, customers view and current page name (for activating it in
	 *         the navigation bar).
	 */
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public ModelAndView getCustomersFilter(@RequestParam("page") int pageNo,
			@RequestParam("nameFilter") String nameFilter, @RequestParam("emailFilter") String emailFilter,
			@RequestParam("contactNumberFilter") String contactNumberFilter) {

		LOG.info("Recieved POST request for /customers");

		ModelAndView mv = new ModelAndView();

		List<Customer> customers = customerService.getCustomers(pageNo, nameFilter, emailFilter, contactNumberFilter);
		int totalPages = customerService.getTotalPages(nameFilter, emailFilter, contactNumberFilter);

		mv.addObject("nameFilter", nameFilter);
		mv.addObject("emailFilter", emailFilter);
		mv.addObject("contactNumberFilter", contactNumberFilter);
		mv.addObject("customers", customers);
		mv.addObject("currPage", "nav-customers");
		mv.addObject("pageNo", pageNo);
		mv.addObject("totalPages", totalPages);
		mv.setViewName("customers");
		return mv;
	}

	/**
	 * This method accepts POST request for /register and registers the customer
	 * details entered in the register page. First the customer details are first
	 * validated using validateNewCustomer method customerService class and then
	 * customer is registered according to the validation result.
	 * 
	 * @param customer customer object with customer details entered in the
	 *                 registration form
	 * @param idProof  CommonsMultipartObject with customer's ID proof image.
	 * @return ModelAndView Object with register view, current page name (for
	 *         activating it in the navigation bar) and error message or success
	 *         message depending on the registration result.
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView postRegister(@ModelAttribute("customer") Customer customer,
			@RequestParam("idProof") CommonsMultipartFile idProof) {

		LOG.info("Recieved POST request for /register");

		ModelAndView mv = new ModelAndView();

		String name = customer.getName(), email = customer.getEmail(), dob = customer.getDateOfBirth(),
				contactNumber = customer.getContactNumber();

		List<String> errors = customerService.validateNewCustomer(name, email, dob, contactNumber, idProof);
		List<String> msgs = new ArrayList<String>();

		if (!errors.isEmpty()) {
			mv.addObject("errors", errors);
		} else {

			boolean added = customerService.addNewCustomer(name, email, dob, contactNumber, idProof);

			if (added) {
				customer.setName("");
				customer.setEmail("");
				customer.setDateOfBirth("");
				customer.setContactNumber("");
				msgs.add("Registeration Successfull.");
				mv.addObject("msgs", msgs);
			} else {
				errors.add("Could'nt Register Customer.");
			}

		}

		mv.addObject("currPage", "nav-register");
		mv.setViewName("register");
		return mv;
	}

	/**
	 * This method accepts POST request for /recharge and adds recharge amount in
	 * customer's balance.
	 * 
	 * @param customerId     Unique ID of customer.
	 * @param rechargeAmount Amount to be added in customer's account.
	 * @param redirectURL    URL used to redirect the request after recharge process
	 *                       to the same page having same filters.
	 * @return ModelAndView Object with redirect URL that redirects to the same page
	 *         with same filters applied.
	 */
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	public ModelAndView rechargeCustomer(@RequestParam("recharge-modal-id") String customerId,
			@RequestParam("recharge-modal-rechargeAmount") int rechargeAmount,
			@RequestParam("redirect-url") String redirectURL) {

		LOG.info("Recieved POST request for /recharge");

		boolean recharged = customerService.rechargeCustomerAccount(customerId, rechargeAmount);
		if (!recharged) {
			return new ModelAndView("error");
		}

		return new ModelAndView("redirect:" + redirectURL);

	}

	/**
	 * This method accepts GET request for /error and sends the error page. This
	 * method is requested in case any internal error occurs.
	 * 
	 * @param httpRequest request with error details in it that is used to render
	 *                    the error page.
	 * @return ModelAndView Object with error details and error page.
	 */
	@RequestMapping(value = "/errors", method = RequestMethod.GET)
	public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

		LOG.info("Recieved POST request for /error");

		ModelAndView errorPage = new ModelAndView("error");
		String errorMsg = "";
		int httpErrorCode = getErrorCode(httpRequest);

		switch (httpErrorCode) {
		case 400: {
			errorMsg = "Http Error Code: 400. Bad Request";
			break;
		}
		case 401: {
			errorMsg = "Http Error Code: 401. Unauthorized";
			break;
		}
		case 404: {
			errorMsg = "Http Error Code: 404. Resource not found";
			break;
		}
		case 500: {
			errorMsg = "Http Error Code: 500. Internal Server Error";
			break;
		}
		}
		errorPage.addObject("errorMsg", errorMsg);
		return errorPage;
	}

	/**
	 * This method is used to retrieve error code from httpRequest received.
	 * 
	 * @param httpRequest request with error details.
	 * 
	 * @return error code
	 */
	private int getErrorCode(HttpServletRequest httpRequest) {

		Throwable throwable = (Throwable) httpRequest.getAttribute("javax.servlet.error.exception");

		LOG.error(throwable == null ? "throwable is null" : throwable.toString());

		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}

}
