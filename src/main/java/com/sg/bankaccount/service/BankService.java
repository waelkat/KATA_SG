package com.sg.bankaccount.service;

import com.sg.bankaccount.CantMakeWithdrawalException;
import com.sg.bankaccount.model.Client;

public interface BankService {

	void addClient(Client client);

	Client getClientWithID(int clientID);

	void deposit(int clientID, float depositValue);

	boolean canMakeWithdrawal(int clientID, float withdrawlValue);

	void makeWithdrawal(int clientID, float withdrawlValue) throws CantMakeWithdrawalException;

	String getClientHistory(int clientID);

}
