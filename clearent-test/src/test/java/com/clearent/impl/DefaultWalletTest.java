package com.clearent.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.clearent.CreditCard;
import com.clearent.Money;
import com.clearent.Transaction;
import com.clearent.Wallet;

/**
 * @author Chris Stone
 *
 */
public class DefaultWalletTest {

	/**
	 * Test method for {@link com.clearent.impl.DefaultWallet#getTotalBalance()}.
	 */
	@Test
	public void testGetTotalBalance() {
		Wallet wallet = new DefaultWallet();
		Money expected = new Money(100);
		Money expected2 = new Money(135);
		CreditCard cc = new CreditCardWithInterestRate(10);
		cc.addTransaction(new Transaction(1L, new Money(100), "One"));
		wallet.addCreditCard(cc);
		assertEquals(expected, wallet.getTotalBalance());
		cc = new CreditCardWithInterestRate(7);
		cc.addTransaction(new Transaction(1L, new Money(35), "CC2 First Transaction"));
		wallet.addCreditCard(cc);
		assertEquals(expected2, wallet.getTotalBalance());
	}

	/**
	 * Test method for {@link com.clearent.impl.DefaultWallet#getTotalInterest()}.
	 */
	@Test
	public void testGetTotalInterest() {
		Wallet wallet = new DefaultWallet();
		Money expected = new Money(10);
		Money expected2 = new Money(12.45);
		CreditCard cc = new CreditCardWithInterestRate(10);
		cc.addTransaction(new Transaction(1L, new Money(100), "One"));
		wallet.addCreditCard(cc);
		assertEquals(expected, wallet.getTotalInterest());
		cc = new CreditCardWithInterestRate(7);
		cc.addTransaction(new Transaction(1L, new Money(35), "CC2 First Transaction"));
		wallet.addCreditCard(cc);
		assertEquals(expected2, wallet.getTotalInterest());
	}

	/**
	 * Test method for {@link com.clearent.impl.DefaultWallet#addCreditCard(com.clearent.CreditCard)}.
	 */
	@Test
	public void testAddCreditCard() {
		Wallet wallet = new DefaultWallet();
		Money expected = new Money(0);
		Money expected2 = new Money(100);
		Money expected3 = new Money(135);
		assertEquals(expected, wallet.getTotalBalance());
		CreditCard cc = new CreditCardWithInterestRate(10);
		cc.addTransaction(new Transaction(1L, new Money(100), "One"));
		wallet.addCreditCard(cc);
		assertEquals(expected2, wallet.getTotalBalance());
		cc = new CreditCardWithInterestRate(7);
		cc.addTransaction(new Transaction(1L, new Money(35), "CC2 First Transaction"));
		wallet.addCreditCard(cc);
		assertEquals(expected3, wallet.getTotalBalance());
	}

	/**
	 * Test method for {@link com.clearent.impl.DefaultWallet#removeCreditCard(com.clearent.CreditCard)}.
	 */
	@Test
	public void testRemoveCreditCard() {
		Wallet wallet = new DefaultWallet();
		Money expected = new Money(0);
		Money expected2 = new Money(100);
		Money expected3 = new Money(135);
		Money expected4 = new Money(35);
		assertEquals(expected, wallet.getTotalBalance());

		CreditCard cc1 = new CreditCardWithInterestRate(10);
		cc1.addTransaction(new Transaction(1L, new Money(100), "One"));
		wallet.addCreditCard(cc1);
		assertEquals(expected2, wallet.getTotalBalance());
		
		CreditCard cc2 = new CreditCardWithInterestRate(7);
		cc2.addTransaction(new Transaction(1L, new Money(35), "CC2 First Transaction"));
		wallet.addCreditCard(cc2);
		assertEquals(expected3, wallet.getTotalBalance());

		wallet.removeCreditCard(cc1);
		assertEquals(expected4, wallet.getTotalBalance());
		
		// Ensure remove won't remove a credit card which doesn't exist.
		wallet.removeCreditCard(cc1);
		assertEquals(expected4, wallet.getTotalBalance());
		
		wallet.removeCreditCard(cc2);
		assertEquals(expected, wallet.getTotalBalance());
	}
}
