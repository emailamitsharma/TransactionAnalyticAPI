package com.aks.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.aks.api.exception.BadRequestException;
import com.aks.api.model.Transaction;
import com.aks.api.service.TransactionService;
import com.aks.api.util.CustomErrorType;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	public static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService tnsService; //Service which will do all data retrieval/manipulation work


	// -------------------Create a transaction-------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "", method = RequestMethod.POST , consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createTransaction(@RequestBody Transaction tns, UriComponentsBuilder ucBuilder) throws Exception {
		logger.info("Creating transaction : {}", tns);

		if (tns == null || tns.getAmount() < 0d || tns.getTimestamp() <= 0l) { //amount < 0d depends upon business requirement.

			logger.error("Unable to create transaction as not having sufficient information ",tns);

			throw new BadRequestException(); // Error 400
		}

		if (tnsService.isExpiredTransaction(tns)) {//greater than 1 second
			logger.error("Unable to create transaction as already expired ",tns);
			return new ResponseEntity(new CustomErrorType("Unable to create transaction as already expired ."),HttpStatus.NO_CONTENT);//as per requirement
		}
		
		tnsService.saveTransaction(tns);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/transactions/{id}").buildAndExpand(tns.getTnsId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
}