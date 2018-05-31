/**
 * 
 */
package com.aks.api.serviceImpls;

import java.time.Instant;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.aks.api.model.Transaction;
import com.aks.api.repository.TransactionRepository;
import com.aks.api.service.TransactionService;
import com.aks.api.util.Constants;

/**
 * @author Amit
 *
 */
@Service
@Primary
public class TransactionServiceImpl implements TransactionService {

	@Override
	public boolean isExpiredTransaction(Transaction tns) {
		
		if(tns != null && tns.getTimestamp() > 0){
			
			long tnsTimestamp = tns.getTimestamp(); //timestamp in UTC timezone
			
			long currentEpochTime = Instant.now().toEpochMilli(); //by default it will refer to UTC clock
			
			return (currentEpochTime - tnsTimestamp) > Constants.EXPIRY_TIME_THRESHOLD_IN_SECONDS*1000;

		}
		
		return false;
	}

	@Override
	public void saveTransaction(Transaction tns) {

		TransactionRepository.addTransaction(tns);
		
	}
	
}
