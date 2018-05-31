/**
 * 
 */
package com.aks.api.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.aks.api.model.Transaction;

/**
 * @author Amit
 *
 */
public class TransactionRepository {

	private static List<Transaction> tnsRecords;

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
		Collections.synchronizedList(tnsRecords).add(tns);
	}

	public static void displayAllTransactions(){
		if(tnsRecords != null){

			tnsRecords.forEach(System.out::print);
		}
	}

}
