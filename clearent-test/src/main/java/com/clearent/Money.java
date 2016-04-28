package com.clearent;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Chris Stone
 *
 */
public class Money {
	public static final Money ZERO = new Money(0);
	
	private BigDecimal amount;
	
	public Money(){
		amount = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
	}

	public Money(int amount){
		this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public Money(float amount){
		this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);		
	}
	
	public Money(double amount){
		this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public Money(BigDecimal amount) {
		if (amount != null){
			this.amount = amount.setScale(2, RoundingMode.HALF_EVEN);
		}
		else{
			amount = new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
		}
	}

	public BigDecimal getAmount(){
		return amount;
	}
	
	public Money add(Money amount){
		if (amount != null){
			this.amount = this.amount.add(amount.getAmount()).setScale(2, RoundingMode.HALF_EVEN);			
		}
		return this;
	}
	
	public Money subtract(Money amount){
		if (amount != null){
			this.amount = this.amount.subtract(amount.getAmount()).setScale(2, RoundingMode.HALF_EVEN);
		}
		return this;
	}
	
	public int compareTo(Money other){
		int c = 1;
		if (other != null){
			c = amount.compareTo(other.getAmount());
		}
		return c;
	}
	
	/**
	 * Calculates the interest to charge based on the supplied interest rate.
	 * 
	 * @param interestRate The interest rate.
	 * @return The calculated interest.
	 */
	public Money calculateInterest(InterestRate interestRate){
		Money interest = Money.ZERO;
		if (interestRate != null){
			interest = new Money(amount.multiply(interestRate.getRate()));
		}
		return interest;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Money [amount=" + amount + "]";
	}
}
