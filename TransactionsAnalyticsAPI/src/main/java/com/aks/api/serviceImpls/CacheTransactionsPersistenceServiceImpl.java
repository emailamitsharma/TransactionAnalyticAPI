/**
 * 
 */
package com.aks.api.serviceImpls;

import org.springframework.stereotype.Service;

import com.aks.api.model.Transaction;
import com.aks.api.service.TransactionService;

/**
 * @author Amit
 * 
 * Cached which will only store last 1 second of data only. Place a mechanism for
 * for data eviction after each and every 1 second from server level clock reference.
 * 
 */
@Service
public class CacheTransactionsPersistenceServiceImpl implements TransactionService{

	@Override
	public boolean isExpiredTransaction(Transaction tns) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveTransaction(Transaction tns) {
		 // responsible for putting element in cache 
		//eviction policy		
	}

	@Override
	public Transaction retrieveTransaction(Long tnsId) {
		// TODO Auto-generated method stub
		return null;
	}

	 
}
