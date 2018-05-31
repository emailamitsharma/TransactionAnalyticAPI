/**
 * 
 */
package com.aks.api.repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.aks.api.model.Transaction;
import com.aks.api.util.Constants;

/**
 * @author Amit
 *
 */
public class TransactionRepository {

	private static List<Transaction> tnsRecords;

	private static List<Transaction> tnsBackupStorage;

	private static volatile int sequence=1;

	public static List<Transaction> getTnsRecords() {
		return tnsRecords != null ? Collections.unmodifiableList(tnsRecords):null;//return un-modifiable list for various other processing.
	}

	public static void setTnsRecords(List<Transaction> tnsRecords) {
		TransactionRepository.tnsRecords = tnsRecords;
	}

	public static void addTransaction(Transaction tns){
		if(tnsRecords == null){
			tnsRecords = new ArrayList<Transaction>(); //for thread safety as numerous request reaching out to add objects in this list within a second time.
		}

		tns.setTnsId(sequence++);

		// if size is threshold	then remove previous element (conditionally) which is of no use with backup policy
		//other way is to assign a task scheduler.
		if(tnsRecords.size()%Constants.PRIMARY_REPOSITORY_EVICTION_SIZE_THRESHOLD == 0){
			synchronized (tnsRecords) {
				
				if(tnsBackupStorage == null)
					tnsBackupStorage = new ArrayList<>();
				
				tnsBackupStorage.addAll(
						tnsRecords.parallelStream().
						filter(data -> data.getTimestamp() < Instant.now().toEpochMilli() - Constants.EXPIRY_TIME_THRESHOLD_IN_SECONDS*1000)
						.collect(Collectors.toList()));

				tnsRecords.removeIf(data -> data.getTimestamp() < Instant.now().toEpochMilli() - Constants.EXPIRY_TIME_THRESHOLD_IN_SECONDS*1000);

			}
		}

		Collections.synchronizedList(tnsRecords).add(tns);

	}

	public static void displayAllTransactions(){
		if(tnsRecords != null){

			tnsRecords.forEach(System.out::print);
		}
	}

}
