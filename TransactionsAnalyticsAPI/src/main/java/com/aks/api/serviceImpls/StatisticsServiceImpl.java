/**
 * 
 */
package com.aks.api.serviceImpls;

import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.aks.api.model.Statistics;
import com.aks.api.model.Transaction;
import com.aks.api.repository.TransactionRepository;
import com.aks.api.service.StatisticsService;
import com.aks.api.util.Constants;

/**
 * @author Amit
 *
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Override
	public Statistics retrieveStats() {
		List<Transaction> tnsRecords =  TransactionRepository.getTnsRecords();
		Statistics stats = null;

		if (tnsRecords != null) {
			DoubleSummaryStatistics calculatedStats = tnsRecords.parallelStream().
													filter(data -> (Instant.now().toEpochMilli() - data.getTimestamp()) < Constants.EXPIRY_TIME_THRESHOLD_IN_SECONDS*1000).
													collect(Collectors.summarizingDouble(Transaction::getAmount));


		
			stats = new Statistics();
			stats.setAvg(calculatedStats.getAverage());
			stats.setCount(calculatedStats.getCount());
			stats.setMax(calculatedStats.getMax());
			stats.setMin(calculatedStats.getMin());
			stats.setSum(calculatedStats.getSum());
		}

		return stats;
	}

}
