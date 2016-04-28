package com.clearent;

public interface Wallet extends Iterable<CreditCard>{
	/**
	 * Get the total account balance across all credit cards in the wallet.
	 * 
	 * @return The balance.
	 */
	public Money getTotalBalance();
	
	/**
	 * Retrieve the total interest due on all credit cards in the wallet.
	 * 
	 * @return The total interest.
	 */
	public Money getTotalInterest();
	
	/**
	 * Adds a credit card to the wallet. Null credit cards will not be
	 * accepted. If the supplied credit card already exists in this wallet,
	 * it will not be replaced.
	 * 
	 * @param creditCard The credit card to add.
	 */
	public void addCreditCard(CreditCard creditCard);
	
	/**
	 * Removes the credit card from the wallet.
	 * 
	 * @param creditCard The credit card to remove.
	 */
	public void removeCreditCard(CreditCard creditCard);

	/**
	 * 
	 * @return The number of credit cards this wallet contains.
	 */
	public int size();
}
