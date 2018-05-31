/**
 * 
 */
package com.aks.api.serviceImpls;

import java.time.Instant;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

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

		try {
			TransactionRepository.addTransaction(tns);
		} catch (Exception e) {
			logger.error(String.format("Error while adding transaction %s to repository with error %s", tns,e.getMessage()));
		}

	}


	@Override
	public Transaction retrieveTransaction(Long tnsId) {
		Optional<Transaction> tns = null;
		try {
			tns =  TransactionRepository.getTnsRecords().parallelStream().filter(data -> data.getTnsId() == tnsId).findFirst();

		} catch (Exception e) {
			logger.error(String.format("Error while retrieving transaction %s from repository with error %s", tnsId,e.getMessage()));
		}
		return tns.isPresent() ? tns.get():null;

	}

}
