/**
 * 
 */
package com.aks.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aks.api.exception.NoDataFoundException;
import com.aks.api.model.Statistics;
import com.aks.api.service.StatisticsService;

/**
 * @author Amit
 *
 */
@RestController
@RequestMapping("/api/statistics/")
public class StatisticsController {
	
	public static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

	@Autowired
	private StatisticsService statsService;
	
	@RequestMapping(value="",method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Statistics> retrieveStatistics() throws Exception{
		
		Statistics stats = statsService.retrieveStats();
		
		if(stats==null)
			throw new NoDataFoundException();
		
		return new ResponseEntity<Statistics>(stats, HttpStatus.OK);
	}

}
