package com.clearent.impl;

import static org.junit.Assert.*;
import java.util.Iterator;

import org.junit.Test;

import com.clearent.CreditCard;
import com.clearent.Money;
import com.clearent.Transaction;

public class CreditCardWithInterestRateTest {

	@Test
	public void testAddTransaction() {
		Transaction expected = new Transaction(1L, new Money(100.00), "A charge");
		CreditCard cc = new CreditCardWithInterestRate(10.00);
		cc.addTransaction(expected);
		int count = 0;
		Iterator<Transaction> iter = cc.iterator();
		while(iter.hasNext()){
			count++;
			assertEquals(expected, iter.next());
		}
		assertEquals(1, count);
	}

	@Test
	public void testGetBalance() {
		Money expected = new Money(100.00);
		CreditCard cc = new CreditCardWithInterestRate(10.00);
		cc.addTransaction(new Transaction(1L, new Money(100.00), "A charge"));
		Money balance = cc.getBalance();
		assertEquals(expected, balance);
	}

	@Test
	public void testGetInterestDue() {
		Money expected = new Money(10);
		CreditCard cc = new CreditCardWithInterestRate(10);
		cc.addTransaction(new Transaction(1L, new Money(100.00), "First Charge"));
		Money interestDue = cc.getInterestDue();
		assertEquals(expected, interestDue);
	}
}
