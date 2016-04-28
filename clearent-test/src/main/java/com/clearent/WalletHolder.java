package com.clearent;

/**
 * 
 * @author Chris Stone
 *
 */
public interface WalletHolder extends Iterable<Wallet> {
	/**
	 * Add a wallet to this holder.
	 * 
	 * @param wallet The wallet to add.
	 */
	public void addWallet(Wallet wallet);	
	
	/**
	 * Remove the wallet from this holder.
	 * 
	 * @param wallet The wallet to remove.
	 */
	public void removeWallet(Wallet wallet);
	
	/**
	 * Get the total balance for this holder's wallets.
	 * 
	 * @return The total balance of the wallets.
	 */
	public Money getTotalBalance();
	
	/**
	 * Gets the total interest due for this holder's wallet.
	 * 
	 * @return The total interest due.
	 */
	public Money getInterestDue();
}
