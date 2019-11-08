package com.nagarro.service;

import java.util.List;

import javax.persistence.Tuple;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nagarro.controller.APIController;
import com.nagarro.dao.CustomerDao;
import com.nagarro.dto.AdjustAmountResponse;
import com.nagarro.dto.BlockAmountResponse;
import com.nagarro.dto.CustomerResponse;
import com.nagarro.dto.LeaderBoardResponse;
import com.nagarro.dto.LoginResponse;
import com.nagarro.model.Customer;

public class APIServiceImpl implements APIService {
	final static Logger LOG = Logger.getLogger(APIController.class);

	@Autowired
	private CustomerDao customerDao;

	public CustomerResponse getCustomer(String customerId) {

		LOG.info("Get customer : " + customerId);

		Customer customer = customerDao.getCustomer(customerId);

		if (customer == null) {
			return new CustomerResponse(false);
		}

		return new CustomerResponse(true, customer.getName(), customer.getBalance());
	}

	public LoginResponse loginCustomer(String customerId) {

		LOG.info("Login customer : " + customerId);

		Customer customer = customerDao.getCustomer(customerId);

		if (customer == null) {
			return new LoginResponse(false);
		}

		return new LoginResponse(true);
	}

	public BlockAmountResponse blockAmount(String customerId, int blockAmount) {

		LOG.info("Blocking amount :" + blockAmount + " for customer : " + customerId);

		Boolean success = customerDao.blockAmount(customerId, blockAmount);
		Customer customer = customerDao.getCustomer(customerId);

		return new BlockAmountResponse(success, customer.getBalance());
	}

	public AdjustAmountResponse adjustAmount(String customerId, int adjustAmount) {

		LOG.info("Adjusting amount :" + adjustAmount + " for customer : " + customerId);

		Boolean success = customerDao.adjustAmount(customerId, adjustAmount);
		Customer customer = customerDao.getCustomer(customerId);

		return new AdjustAmountResponse(success, customer.getBalance());
	}

	public LeaderBoardResponse getLeaderBoard() {
		
		LOG.info("Retrieving Leaderboard.");

		List<Tuple> leaderBoard = customerDao.getLeaderBoard();
		LeaderBoardResponse leaderBoardResponse = prepareLeaderBoardResponse(leaderBoard);

		return leaderBoardResponse;
	}

	private LeaderBoardResponse prepareLeaderBoardResponse(List<Tuple> leaderBoard) {
		
		LOG.info("Preparing LeaderBoardResponse.");
		
		LeaderBoardResponse leaderBoardResponse = new LeaderBoardResponse(true);

		CustomerResponse customerResponse;

		customerResponse = new CustomerResponse(true);
		customerResponse.setName(leaderBoard.get(0).get(0).toString());
		customerResponse.setBalance(Integer.parseInt(leaderBoard.get(0).get(1).toString()));
		leaderBoardResponse.setRank1(customerResponse);
		
		customerResponse = new CustomerResponse(true);
		customerResponse.setName(leaderBoard.get(1).get(0).toString());
		customerResponse.setBalance(Integer.parseInt(leaderBoard.get(1).get(1).toString()));
		leaderBoardResponse.setRank2(customerResponse);
		
		customerResponse = new CustomerResponse(true);
		customerResponse.setName(leaderBoard.get(2).get(0).toString());
		customerResponse.setBalance(Integer.parseInt(leaderBoard.get(2).get(1).toString()));
		leaderBoardResponse.setRank3(customerResponse);
		
		customerResponse = new CustomerResponse(true);
		customerResponse.setName(leaderBoard.get(3).get(0).toString());
		customerResponse.setBalance(Integer.parseInt(leaderBoard.get(3).get(1).toString()));
		leaderBoardResponse.setRank4(customerResponse);
		
		customerResponse = new CustomerResponse(true);
		customerResponse.setName(leaderBoard.get(4).get(0).toString());
		customerResponse.setBalance(Integer.parseInt(leaderBoard.get(4).get(1).toString()));
		leaderBoardResponse.setRank5(customerResponse);
		return leaderBoardResponse;
	}

}
