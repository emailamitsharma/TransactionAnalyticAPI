package com.aks.api.cucumber.stepDefinition.controller;

import java.util.HashMap;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.aks.api.cucumber.configuration.CucumberStepDefBasicConf;
import com.aks.api.model.Statistics;

import cucumber.api.Scenario;
import cucumber.api.java8.En;

/**
 * @author Amit
 *
 */
public class TransactionAPIControllerTest  extends CucumberStepDefBasicConf implements En {

	public static final Logger logger = LoggerFactory.getLogger(TransactionAPIControllerTest.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	private ResponseEntity<Object> testRestCallResponse; 

	private String restApiUrl = baseURLForAPI;
		

	TransactionAPIControllerTest() {
		
		 Before(new String[]{"@DevTest"}, 0, 1, (Scenario scenario) -> {
			 testRestCallResponse = null;
	        });
	
		Before(()-> {logger.info("Nothing to setup in before any of test case is fired.But you can add your stuff in future");});

		
		Given("^REST-API end point is available as baseURL$", () -> {
			Assert.assertTrue(restApiUrl!=null && restApiUrl.length()>0);
		});

		When("^Connection is tried to reach out with given request$", () -> {
			testRestCallResponse = this.testRestTemplate.getForEntity(restApiUrl, Object.class, new HashMap<>());
		});

		Then("^Response is returned$", () -> {
		   Assert.assertTrue(testRestCallResponse!=null);
		});

		Then("^status code is (\\d+)$", (Integer arg1) -> {
			   Assert.assertTrue(testRestCallResponse!=null && testRestCallResponse.getStatusCode().toString().equalsIgnoreCase(String.valueOf(arg1)));
		});

		Then("^stats are matching$", () -> {
		    Assert.assertTrue(testRestCallResponse!=null);
		});

		
		After(new String[]{"@DevTest"},0,1,(Scenario sc)-> {logger.info("Nothing to close out after all test case have been fired.But you can add your stuff in future");});
	}
}
