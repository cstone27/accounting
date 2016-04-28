package com.clearent.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.clearent.CreditCard;
import com.clearent.InterestRate;
import com.clearent.Money;
import com.clearent.Transaction;

/**
 * A {@link CreditCard} implementation which contains the interest rate for the 
 * card and calculates interest due on demand.
 * 
 * @author Chris Stone
 *
 */
public class CreditCardWithInterestRate implements CreditCard {
	private Money transactionBalance = new Money(0);
	private Set<Transaction> transactions = new HashSet<Transaction>();
	private InterestRate interestRate = new InterestRate(0);
	
	/**
	 * Create a new credit card with an interest rate. The interest rate is specified as
	 * a percentage, e.g 10 means 10% interest.
	 * 
	 * @param interestRate The interest rate percentage
	 */
	public CreditCardWithInterestRate(double interestRate) {
		this.interestRate = new InterestRate(interestRate);
	}
	
	public CreditCardWithInterestRate(InterestRate interestRate) {
		this.interestRate = new InterestRate(interestRate);
	}

	@Override
	public void addTransaction(Transaction transaction) {
		if (transaction != null && transactions.contains(transaction) == false){
			transactions.add(transaction);
			transactionBalance = transactionBalance.add(transaction.getAmount());
		}
	}

	@Override
	public Money getBalance() {
		return transactionBalance;
	}

	@Override
	public Money getInterestDue() {
		Money interestDue = new Money(0);
		Money totalBalance = getBalance();
		if (totalBalance.compareTo(Money.ZERO) > 0){
			interestDue = totalBalance.calculateInterest(interestRate);
		}
		return interestDue;
	}

	@Override
	public Iterator<Transaction> iterator() {
		return transactions.iterator();
	}

	@Override
	public Object getInterestRate() {
		return interestRate;
	}
}
