/**
 * 
 */
package com.aks.api.controller;


import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.aks.api.exception.ErrorDetails;
import com.aks.api.exception.ErrorMessageConstants;
import com.aks.api.exception.MoreInfo;

/**
 * @author Amit
 *
 */
@RestController
@RestControllerAdvice
public class ErrorHandlingController implements ErrorController{


	@Order(Ordered.HIGHEST_PRECEDENCE)
	@ExceptionHandler(NoHandlerFoundException.class)
	public final ResponseEntity<ErrorDetails> handleNoHanlderFoundException(NoHandlerFoundException ex, WebRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (ErrorDetails.getErrorBuilder(HttpStatus.NOT_FOUND, ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.defaultErrorMessage, ErrorMessageConstants.resourceNotFoundDeveloperMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public final ResponseEntity<ErrorDetails> handleGlobalUnHandledException(Exception ex, WebRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (ErrorDetails.getErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR,ErrorMessageConstants.defaultErrorMessage,ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.defaultErrorMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

    @Override
    public String getErrorPath() {
        return PATH;
    }
}