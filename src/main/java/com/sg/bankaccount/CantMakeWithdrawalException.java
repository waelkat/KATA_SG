package com.sg.bankaccount;

public class CantMakeWithdrawalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CantMakeWithdrawalException() {
		super("The current client don't have enough balance to make the redraw.");
	}

}
