/**
 * 
 */
package com.aks.api.service;

import com.aks.api.model.Transaction;

/**
 * @author Amit
 *
 */
public interface TransactionService {

	public boolean isExpiredTransaction(Transaction tns);
	
	public void saveTransaction(Transaction tns);

	Transaction retrieveTransaction(Long tnsId);
	
	
}
