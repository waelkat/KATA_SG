package com.sg.bankaccount.model;

import java.util.ArrayList;
import java.util.List;

public class Client {

	private String name;
	private int clientID;
	private float balance = 0;
	private List<Operation> operations = new ArrayList<Operation>();
	
	
	public Client(int clientID, String name) {
		this.clientID = clientID;
		this.name = name;
	}


	public Client(int clientID, String name, float initialBalance) {
		this.clientID = clientID;
		this.name = name;
		this.balance = initialBalance;
	}


	public String getName() {
		return name;
	}

	public int getClientID() {
		return clientID;
	}


	public void deposit(float depositValue) {

		addNewOperation(Operation.OPERATION_TYPE_DEPOSIT, depositValue);
		this.balance += depositValue;
	}


	public float getBalance() {
		return balance;
	}


	public void makeWithdrawal(float withdrawlValue) {
		if(this.balance > 0 && this.balance >= withdrawlValue) {
			
			addNewOperation(Operation.OPERATION_TYPE_WITHDRAWAL, withdrawlValue);
			this.balance -= withdrawlValue;
		}
	}


	public String getClientName() {
		return this.name;
	}
	
	private void addNewOperation(String operationType, float withdrawlValue) {

		Operation operation = new Operation();
		
		operation.setType(operationType);
		operation.setAmount(withdrawlValue);
		
		Float clientBalance = this.balance;
		operation.setClientBalance(clientBalance);
		
		this.operations.add(operation);
	}


	public List<Operation> getOperations() {
		return operations;
	}
	

}
