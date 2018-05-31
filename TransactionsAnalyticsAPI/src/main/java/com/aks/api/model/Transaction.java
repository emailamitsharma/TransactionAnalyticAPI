/**
 * 
 */
package com.aks.api.model;

import java.io.Serializable;

/**
 * @author Amit
 *
 */
public class Transaction implements Serializable {
	
	private long tnsId;
	
	private double amount;
	
	private long timestamp;
	

	/**
	 */
	private static final long serialVersionUID = 1L;


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getTnsId() {
		return tnsId;
	}

	public void setTnsId(long tnsId) {
		this.tnsId = tnsId;
	}
	
	@Override
	public String toString() {
		return String.format("[Tns Id: %s , Amount: %s]", tnsId,amount);
	}

}
