package com.nagarro.dto;

/**
 * This class is used for sending response of GetLeaderboard Request. Success
 * property determines whether the request was successful or not. Rank 1-5
 * property are customers that hold these ranks respectively on the basis of
 * their balance.
 * 
 * @author sahilmehta02
 *
 */
public class LeaderBoardResponse {

	boolean success;
	private CustomerResponse rank1;
	private CustomerResponse rank2;
	private CustomerResponse rank3;
	private CustomerResponse rank4;
	private CustomerResponse rank5;

	public LeaderBoardResponse() {

	}

	public LeaderBoardResponse(boolean success) {
		this.success = success;
	}

	public CustomerResponse getRank1() {
		return rank1;
	}

	public void setRank1(CustomerResponse rank1) {
		this.rank1 = rank1;
	}

	public CustomerResponse getRank2() {
		return rank2;
	}

	public void setRank2(CustomerResponse rank2) {
		this.rank2 = rank2;
	}

	public CustomerResponse getRank3() {
		return rank3;
	}

	public void setRank3(CustomerResponse rank3) {
		this.rank3 = rank3;
	}

	public CustomerResponse getRank4() {
		return rank4;
	}

	public void setRank4(CustomerResponse rank4) {
		this.rank4 = rank4;
	}

	public CustomerResponse getRank5() {
		return rank5;
	}

	public void setRank5(CustomerResponse rank5) {
		this.rank5 = rank5;
	}

}
