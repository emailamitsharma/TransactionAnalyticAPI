/**
 * 
 */
package com.aks.api.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author Amit
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages={"com.aks.api.controller"})
public class CustomResponseEntityExceptionHandler {

	public static final Logger logger = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);

	@ExceptionHandler(NoDataFoundException.class)
	public final ResponseEntity<ErrorDetails> handleNoDataFoundException(NoDataFoundException ex, WebRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (new ErrorDetails.ErrorDetailsBuilder(HttpStatus.OK,ErrorMessageConstants.noDataFoundErrorMessage,ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.noDataFoundErrorMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.OK);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ErrorDetails> handleBadRequest(BadRequestException ex, WebRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (new ErrorDetails.ErrorDetailsBuilder(HttpStatus.BAD_REQUEST,ErrorMessageConstants.defaultErrorMessage,ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.badRequestDeveloperMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (new ErrorDetails.ErrorDetailsBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessageConstants.defaultErrorMessage,ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.defaultErrorMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<?> handleAnyException(Throwable ex, HttpServletRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (new ErrorDetails.ErrorDetailsBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessageConstants.defaultErrorMessage,ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.defaultErrorMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UnknownException.class)
	public ResponseEntity<?> handleUnknownException(UnknownException ex, HttpServletRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (new ErrorDetails.ErrorDetailsBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessageConstants.defaultErrorMessage,ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.defaultErrorMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (new ErrorDetails.ErrorDetailsBuilder(HttpStatus.BAD_REQUEST, ErrorMessageConstants.defaultErrorMessage,ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.badRequestDeveloperMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)	
	public ResponseEntity<?> handleRequiredParameterMissing(MissingServletRequestParameterException ex, HttpServletRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (new ErrorDetails.ErrorDetailsBuilder(HttpStatus.BAD_REQUEST, ErrorMessageConstants.defaultErrorMessage,ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.badRequestDeveloperMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidDataProcessingException.class)
	public ResponseEntity<?> handleInvalidProcessingException(InvalidDataProcessingException ex, HttpServletRequest request) {
		MoreInfo moreInfo = new MoreInfo();
		ErrorDetails errorDetails = (new ErrorDetails.ErrorDetailsBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessageConstants.defaultErrorMessage,ex.getMessage() != null? ex.getMessage():ErrorMessageConstants.defaultErrorMessage)).setMoreInfo(moreInfo).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
