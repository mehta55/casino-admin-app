package com.nagarro.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.dto.AdjustAmountResponse;
import com.nagarro.dto.BlockAmountResponse;
import com.nagarro.dto.CustomerResponse;
import com.nagarro.dto.LeaderBoardResponse;
import com.nagarro.dto.LoginResponse;
import com.nagarro.service.APIService;

/**
 * This controller handles the requests from Roulette API.
 * 
 * @author sahilmehta02
 *
 */
@RestController
public class APIController {
	final static Logger LOG = Logger.getLogger(APIController.class);

	@Autowired
	private APIService apiService;

	/**
	 * This method accepts GET request from Roulette API for /login and calls
	 * loginCustomer service for customerId received.
	 * 
	 * @param customerId Unique ID of customer.
	 * @return LoginResponse Object that has success status in it.
	 */
	@RequestMapping(value = "/login/{customerId}", method = RequestMethod.GET)
	public LoginResponse customerLogin(@PathVariable("customerId") String customerId) {

		LOG.info("Recieved GET request for /login for customerId : " + customerId);

		return apiService.loginCustomer(customerId);
	}

	/**
	 * This method accepts GET request from Roulette API for /customer/{customerId}
	 * and calls getCustomer service to get customer details for customerId
	 * received.
	 * 
	 * @param customerId Unique ID of customer.
	 * @return CustomerResponse Object that has success status, customer name and
	 *         balance.
	 */
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
	public CustomerResponse getCustomer(@PathVariable("customerId") String customerId) {

		LOG.info("Recieved GET request for /customer for customerId : " + customerId);

		return apiService.getCustomer(customerId);
	}

	/**
	 * This method accepts GET request from Roulette API for
	 * /customer/{customerId}/block_amount/{blockAmount} and calls block amount
	 * service to block the received amount from customer's balance.
	 * 
	 * @param customerId  Unique ID of customer
	 * @param blockAmount Amount to be blocked
	 * @return BlockAmountResponse Object having success status and updated
	 *         customer's balance.
	 */
	@RequestMapping(value = "customer/{customerId}/block_amount/{blockAmount}", method = RequestMethod.GET)
	public BlockAmountResponse blockAmount(@PathVariable("customerId") String customerId,
			@PathVariable("blockAmount") int blockAmount) {

		LOG.info("Recieved GET request for /block_amount for customerId : " + customerId);

		return apiService.blockAmount(customerId, blockAmount);
	}

	/**
	 * This method accepts GET request from Roulette API for
	 * /customer/{customerId}/adjust_amount/{adjustAmount} and calls adjust amount
	 * service to adjust the received amount in customer's balance.
	 * 
	 * @param customerId   Unique ID of customer
	 * @param adjustAmount Amount to be adjusted
	 * @return AdjustAmountResponse Object having success status and updated
	 *         customer's balance.
	 */
	@RequestMapping(value = "/customer/{customerId}/adjust_amount/{adjustAmount}", method = RequestMethod.GET)
	public AdjustAmountResponse adjustAmount(@PathVariable("customerId") String customerId,
			@PathVariable("adjustAmount") int adjustAmount) {

		LOG.info("Recieved GET request for /adjust_amount for customerId : " + customerId);

		return apiService.adjustAmount(customerId, adjustAmount);
	}

	/**
	 * This method accepts GET request from Roulette API for /leaderBoard and calls
	 * leader board service to retrieve top customers of the roulette application.
	 * 
	 * @return LeaderBoardResponse object having top 5 customers.
	 */
	@RequestMapping(value = "/leaderBoard", method = RequestMethod.GET)
	public LeaderBoardResponse getLeaderBoard() {
		LOG.info("Recieved GET request for /leaderBoard.");

		return apiService.getLeaderBoard();
	}

}
