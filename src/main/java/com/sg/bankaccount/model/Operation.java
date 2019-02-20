package com.sg.bankaccount.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operation {

	private float amount;
	private float clientBalance = 0;
	private String type;
	private LocalDateTime creationDate;
	
	public static final String OPERATION_TYPE_WITHDRAWAL = "WITHDRAWAL";
	public static final String OPERATION_TYPE_DEPOSIT = "DEPOSIT";
	
	public Operation() {
		this.creationDate = LocalDateTime.now();
	}
	
	@Override
	public String toString() {
		String toString = "";
		Float newBalance;
		
		if(getType().equals(OPERATION_TYPE_WITHDRAWAL)) {
			toString += "Withdraw an amount of ";
			newBalance = getClientBalance() - getAmount();
			
		} else {
			toString += "Deposit an amount of ";
			newBalance = getClientBalance() + getAmount();
		}
		
		toString += getAmount() +" .";
		toString += "The balance before the operation was : " + getClientBalance() +". ";
		toString += "Your new balance is : " + newBalance +". ";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		toString += "Operation date : " + getCreationDate().format(formatter);
		
		return toString + "\n";
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setClientBalance(float clientBalance) {
		this.clientBalance = clientBalance;
	}

	public float getAmount() {
		return this.amount;
	}

	public float getClientBalance() {
		return this.clientBalance;
	}

	public String getType() {
		return this.type;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

}
