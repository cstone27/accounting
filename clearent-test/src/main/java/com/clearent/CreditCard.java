package com.clearent;

public interface CreditCard extends Iterable<Transaction> {
	
	/**
	 * Add a transaction to the credit account.
	 * 
	 * @param transaction The transaction.
	 */
	public void addTransaction(Transaction transaction);
	
	/**
	 * Get the total balance on the credit account.
	 * 
	 * @return The balance.
	 */
	public Money getBalance();
	
	/**
	 * Retrieve the interest due.
	 * 
	 * @return The interest due.
	 */
	public Money getInterestDue();

	public Object getInterestRate();
}
