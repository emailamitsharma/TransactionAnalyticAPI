/**
 * 
 */
package com.aks.api.cucumber.configuration;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Amit
 *
 */


@RunWith(Cucumber.class) 
@CucumberOptions(
				 features={"classpath:cucumberFeatures"},
				 plugin = {"pretty", "json:target/cucumber/cucumber-report.json", "html:target/cucumber/plain-html-report.html"},
				 glue = {"com/aks/api/cucumber/stepDefinition/"},
				 tags ={"@DevTest"},
				 monochrome=true)

public class CucumberRunner {
	
}
