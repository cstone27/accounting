package com.clearent.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.clearent.CreditCard;
import com.clearent.Money;
import com.clearent.Wallet;

/**
 * A default, basic implementation of a {@link Wallet}.
 * 
 * @author Chris Stone
 *
 */
public class DefaultWallet implements Wallet {
	private Money totalBalance = new Money();
	private Set<CreditCard> creditCards = new HashSet<>();
	
	@Override
	public Iterator<CreditCard> iterator() {
		return creditCards.iterator();
	}

	@Override
	public Money getTotalBalance() {
		return totalBalance;
	}

	@Override
	public Money getTotalInterest() {
		Money interestDue = new Money();
		Iterator<CreditCard> iter = creditCards.iterator();
		while(iter.hasNext()){
			Money ccInterest = iter.next().getInterestDue();
			interestDue = interestDue.add(ccInterest);
		}
		return interestDue;
	}

	@Override
	public void addCreditCard(CreditCard creditCard) {
		if (creditCard != null && creditCards.contains(creditCard) == false){
			creditCards.add(creditCard);
			totalBalance = totalBalance.add(creditCard.getBalance());
		}
	}
	
	public int size(){
		return creditCards.size();
	}

	@Override
	public void removeCreditCard(CreditCard creditCard) {
		if (creditCard != null && creditCards.contains(creditCard)){
			creditCards.remove(creditCard);
			totalBalance = totalBalance.subtract(creditCard.getBalance());
		}
	}
}
