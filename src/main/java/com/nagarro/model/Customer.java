package com.nagarro.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {

	@Id
	@Column(name = "CUSTOMER_ID")
	private String id;

	@Column(name = "CUSTOMER_NAME")
	private String name;

	@Column(name = "DATE_OF_BIRTH")
	private String dateOfBirth;

	@Column(name = "CONTACT_NUMBER")
	private String contactNumber;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ID_PROOF_IMAGE")
	private Blob idProofImage;

	@Column(name = "BALANCE")
	private int balance;

	@Column(name = "BLOCKED_BALANCE")
	private int blockedBalance;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Blob getIdProofImage() {
		return idProofImage;
	}

	public void setIdProofImage(Blob idProofImage) {
		this.idProofImage = idProofImage;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getBlockedBalance() {
		return blockedBalance;
	}

	public void setBlockedBalance(int blockedBalance) {
		this.blockedBalance = blockedBalance;
	}

}
