/**
 * 
 */
package com.aks.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Amit
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {
	private static final long serialVersionUID = 1L;

	
	public BadRequestException(String exception) {
	    super(exception);
	  }

	public BadRequestException() {
	}
}
