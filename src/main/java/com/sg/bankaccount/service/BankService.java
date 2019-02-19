package com.sg.bankaccount.service;

import com.sg.bankaccount.CantMakeWithdrawalException;
import com.sg.bankaccount.model.Client;

public interface BankService {

	/**
	 * Add a new client to the bank clients
	 * @param Client client
	 */
	void addClient(Client client);

	/**
	 * Get the client form the client list using client ID
	 * @param int clientID
	 * @return Client object or null if none was found
	 */
	Client getClientWithID(int clientID);

	/**
	 * Add the deposit amount to the specified client balance
	 * @param int clientID
	 * @param float depositValue
	 * @throws IllegalArgumentException if the client is not found
	 */
	void deposit(int clientID, float depositValue);

	/**
	 * Test if the current client can make a Withdrawal from his account. 
	 * @param int clientID
	 * @param float withdrawlValue
	 * @return true if the client can make a Withdrawal
	 * @throws IllegalArgumentException if the client is not found
	 */
	boolean canMakeWithdrawal(int clientID, float withdrawlValue);

	/**
	 * Make a Withdrawal with the specified amount for the current customer
	 * @param int clientID
	 * @param float withdrawlValue
	 * @throws CantMakeWithdrawalException if the Withdrawal amount is greater than the customer balance
	 * @throws IllegalArgumentException if the client is not found
	 */
	void makeWithdrawal(int clientID, float withdrawlValue) throws CantMakeWithdrawalException;

	/**
	 * Get the current client transactions history
	 * @param int clientID
	 * @return String : the client history
	 */
	String getClientHistory(int clientID);

}
