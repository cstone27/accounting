package com.clearent;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Chris Stone
 *
 */
public class InterestRate {
	private BigDecimal rate;
	
	/**
	 * Create a new interest rate with the supplied rate.
	 * 
	 * @param rate The interest rate as a whole number, e.g. 10 is 10% interest.
	 */
	public InterestRate(int rate){
		double doubleRate = (double)rate;
		this.rate = new BigDecimal(doubleRate/100).setScale(4, RoundingMode.HALF_EVEN);
	}
	
	public InterestRate(double rate){
		this.rate = new BigDecimal(rate/100).setScale(4, RoundingMode.HALF_EVEN);
	}

	public InterestRate(InterestRate interestRate) {
		rate = interestRate.getRate();
	}

	public BigDecimal getRate(){
		return rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
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
		InterestRate other = (InterestRate) obj;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterestRate [rate=" + rate + "]";
	}
}
