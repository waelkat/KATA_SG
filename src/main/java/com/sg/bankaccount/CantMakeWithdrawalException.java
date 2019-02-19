package com.sg.bankaccount;

/**
 * An Exception to throw in case the current customer dones'nt have enough balance to make the withdrawal
 * @author DELL
 *
 */
public class CantMakeWithdrawalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CantMakeWithdrawalException() {
		super("The current client don't have enough balance to make the redraw.");
	}

}
