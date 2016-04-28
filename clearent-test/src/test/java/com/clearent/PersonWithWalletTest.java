/**
 * 
 */
package com.clearent;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.clearent.impl.CreditCardWithInterestRate;
import com.clearent.impl.DefaultWallet;

/**
 * @author Chris Stone
 *
 */
public class PersonWithWalletTest {
	private static Map<String, InterestRate> creditCardRates = new HashMap<>();
	private static final String VISA = "Visa";
	private static final String MC = "Master Card";
	private static final String DISCOVER = "Discover";
	
	@BeforeClass
	public static void init(){
		creditCardRates.put(VISA, new InterestRate(10));
		creditCardRates.put(MC, new InterestRate(5));
		creditCardRates.put(DISCOVER, new InterestRate(1));
	}

	@Test
	public void oneWalletThreeCardsTest(){
		Money expectedPersonTotal = new Money(16);
		Money expectedVisa = new Money(10);
		Money expectedMc = new Money(5);
		Money expectedDisc = new Money(1);
		Money expectedBalance = new Money(100);

		WalletHolder person = new PersonWithWallet(new Name("Test", "A", "Person"));
		Wallet wallet = new DefaultWallet();
		person.addWallet(wallet);
		
		CreditCard creditCard = createCreditCard(VISA);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedVisa, creditCard.getInterestDue());
		wallet.addCreditCard(creditCard);

		creditCard = createCreditCard(MC);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedMc, creditCard.getInterestDue());
		wallet.addCreditCard(creditCard);

		creditCard = createCreditCard(DISCOVER);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedDisc, creditCard.getInterestDue());
		wallet.addCreditCard(creditCard);
		
		assertEquals(3, wallet.size());
		Money personTotal = person.getInterestDue();
		assertEquals(expectedPersonTotal, personTotal);
		
	}
	
	@Test
	public void onePersonTwoWalletsTest(){
		Money expectedPersonTotal = new Money(16);
		Money expectedVisa = new Money(10);
		Money expectedMc = new Money(5);
		Money expectedDisc = new Money(1);
		Money expectedBalance = new Money(100);
		Money expWallet1Interest = new Money(11);
		Money expWallet2Interest = new Money(5);

		WalletHolder person = new PersonWithWallet(new Name("Test", "A", "Person"));

		Wallet wallet1 = new DefaultWallet();
		person.addWallet(wallet1);
		Wallet wallet2 = new DefaultWallet();
		person.addWallet(wallet2);

		CreditCard creditCard = createCreditCard(VISA);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedVisa, creditCard.getInterestDue());
		wallet1.addCreditCard(creditCard);

		creditCard = createCreditCard(MC);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedMc, creditCard.getInterestDue());
		wallet2.addCreditCard(creditCard);

		creditCard = createCreditCard(DISCOVER);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedDisc, creditCard.getInterestDue());
		wallet1.addCreditCard(creditCard);
		
		assertEquals(expectedPersonTotal, person.getInterestDue());
		assertEquals(expWallet1Interest, wallet1.getTotalInterest());
		assertEquals(expWallet2Interest, wallet2.getTotalInterest());
	}
	
	@Test
	public void twoPeopleTest(){
		Money expectedPersonTotal1 = new Money(16);
		Money expectedPersonTotal2 = new Money(15);

		Money expectedVisa = new Money(10);
		Money expectedMc = new Money(5);
		Money expectedDisc = new Money(1);
		
		Money expectedBalance = new Money(100);
		Money expWallet1Interest = new Money(16);
		Money expWallet2Interest = new Money(15);

		WalletHolder person1 = new PersonWithWallet(new Name("Person", "", "One"));
		WalletHolder person2 = new PersonWithWallet(new Name("Person", "", "One"));

		Wallet wallet1 = new DefaultWallet();
		person1.addWallet(wallet1);

		Wallet wallet2 = new DefaultWallet();
		person2.addWallet(wallet2);

		// Person 1 setup
		CreditCard creditCard = createCreditCard(VISA);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedVisa, creditCard.getInterestDue());
		wallet1.addCreditCard(creditCard);

		creditCard = createCreditCard(MC);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedMc, creditCard.getInterestDue());
		wallet1.addCreditCard(creditCard);

		creditCard = createCreditCard(DISCOVER);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedDisc, creditCard.getInterestDue());
		wallet1.addCreditCard(creditCard);

		// Person 2 Setup
		creditCard = createCreditCard(VISA);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedVisa, creditCard.getInterestDue());
		wallet2.addCreditCard(creditCard);

		creditCard = createCreditCard(MC);
		creditCard.addTransaction(new Transaction(1L, new Money(100), "Transaction"));
		assertEquals(expectedBalance, creditCard.getBalance());
		assertEquals(expectedMc, creditCard.getInterestDue());
		wallet2.addCreditCard(creditCard);

		assertEquals(expectedPersonTotal1, person1.getInterestDue());
		assertEquals(expWallet1Interest, wallet1.getTotalInterest());

		assertEquals(expectedPersonTotal2, person2.getInterestDue());
		assertEquals(expWallet2Interest, wallet2.getTotalInterest());
	}
	
	private CreditCard createCreditCard(String name){
		CreditCard creditCard = null;
		if (name != null && creditCardRates.containsKey(name)){
			InterestRate cardRate = creditCardRates.get(name);
			creditCard = new CreditCardWithInterestRate(cardRate);
			assertEquals(cardRate, creditCard.getInterestRate());
		}
		return creditCard;
	}
}
