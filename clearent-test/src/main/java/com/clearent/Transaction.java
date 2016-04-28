package com.clearent;

/**
 * Immutable data object for transactions.
 * 
 * @author Chris Stone
 *
 */
public class Transaction {
	private Long transactionId;
	private Money amount;
	private String description;

	/**
	 * Create a new transaction.
	 * 
	 * @param transactionId
	 * @param amount
	 * @param description
	 */
	public Transaction(Long transactionId, Money amount, String description) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.description = description;
	}

	public Long getTransactionId() {
		return transactionId;
	}
	
	public Money getAmount() {
		return amount;
	}

	public String getDescription() {
		return description;
	}
}
