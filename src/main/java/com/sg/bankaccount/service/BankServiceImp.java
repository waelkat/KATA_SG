package com.sg.bankaccount.service;

import java.util.ArrayList;
import java.util.List;

import com.sg.bankaccount.CantMakeWithdrawalException;
import com.sg.bankaccount.model.Client;

public class BankServiceImp implements BankService {

	List<Client> listClient = new ArrayList<Client>();
	
	@Override
	public void addClient(Client client) {
		listClient.add(client);
	}

	@Override
	public Client getClientWithID(int clientID) {
		
		return listClient.stream()
				.filter(client -> clientID == client.getClientID())
				.findAny().orElse(null);
	}

	@Override
	public void deposit(int clientID, float depositValue) {
		Client client = getClientWithID(clientID);
		
		if(client == null) {
			throw new IllegalArgumentException("Client with ID " + clientID + " Not found.");
		}
		
		client.deposit(depositValue);
	}

	@Override
	public boolean canMakeWithdrawal(int clientID, float withdrawlValue) {
		Client client = getClientWithID(clientID);
		
		if(client == null) {
			throw new IllegalArgumentException("Client with ID " + clientID + " Not found.");
		}
		
		if(client.getBalance() > 0 && client.getBalance() >= withdrawlValue) {
			return true;
		}
		return false;
	}

	@Override
	public void makeWithdrawal(int clientID, float withdrawlValue) throws CantMakeWithdrawalException {
		
		if(!canMakeWithdrawal(clientID, withdrawlValue)) {
			throw new CantMakeWithdrawalException();
		}
		
		Client client = getClientWithID(clientID);
		client.makeWithdrawal(withdrawlValue);
	}

	@Override
	public String getClientHistory(int clientID) {
		Client client = getClientWithID(clientID);
		
		StringBuilder clientHistory = new StringBuilder();
		client.getOperations().stream().forEach(operation -> clientHistory.append(operation.toString()));
		
		return clientHistory.toString();
	}

}
