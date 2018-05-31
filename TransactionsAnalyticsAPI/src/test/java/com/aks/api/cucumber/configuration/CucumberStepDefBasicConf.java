/**
 * 
 */
package com.aks.api.cucumber.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.aks.api.app.ExecuteApplication;

/**
 * @author Amit
 *
 */

@SpringBootTest(classes=ExecuteApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes=ExecuteApplication.class)
@AutoConfigureMockMvc
public class CucumberStepDefBasicConf {

	@Value("${baseURLOfAPI}")
	public String baseURLForAPI;
}
