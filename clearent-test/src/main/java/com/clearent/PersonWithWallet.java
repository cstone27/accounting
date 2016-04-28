package com.clearent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * A person who holds one or more wallets.
 * 
 * @author Chris Stone
 *
 */
public class PersonWithWallet extends Person implements WalletHolder {
	private Set<Wallet> wallets = new HashSet<>();
	
	public PersonWithWallet(Name name) {
		super(name);
	}

	@Override
	public Iterator<Wallet> iterator() {
		return wallets.iterator();
	}

	@Override
	public void addWallet(Wallet wallet) {
		if (wallet != null && !wallets.contains(wallet)){
			wallets.add(wallet);
		}
	}

	@Override
	public void removeWallet(Wallet wallet) {
		if (wallet != null){
			wallets.remove(wallet);
		}
	}

	@Override
	public Money getTotalBalance() {
		Money total = new Money();
		Iterator<Wallet> iter = wallets.iterator();
		while(iter.hasNext()){
			Wallet wallet = iter.next();
			total.add(wallet.getTotalBalance());
		}
		return total;
	}

	@Override
	public Money getInterestDue() {
		Money total = new Money();
		Iterator<Wallet> iter = wallets.iterator();
		while(iter.hasNext()){
			Money totalInterest = iter.next().getTotalInterest();
			total = total.add(totalInterest);
		}
		return total;
	}
}
