/**
 * 
 */
package com.aks.api.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Amit
 *
 */
public class ErrorDetails {

	private String code;

	private String message;

	private String developerMessage;

	private MoreInfo moreInfo;

	private ErrorDetails(ErrorDetailsBuilder builder){
		setCode(builder.code);
		this.message = builder.message;
		this.developerMessage = builder.developerMessage;
		this.moreInfo = builder.moreInfo;
	}
	

	public String getCode() {
		return code;
	}

	private void setCode(HttpStatus code) {
		this.code = code.toString();
	}

	public static class ErrorDetailsBuilder {
		private HttpStatus code;

		private String message;

		private String developerMessage;

		private MoreInfo moreInfo;

		public ErrorDetailsBuilder(HttpStatus buildHttpStatusCode, String friendlyMessage, String buildDeveloperMessage){
			this.code = buildHttpStatusCode;
			this.message = friendlyMessage;
			this.developerMessage = buildDeveloperMessage;
		}

		public ErrorDetailsBuilder() {
			super();
		}

		public ErrorDetailsBuilder setMoreInfo(MoreInfo buildMoreInfo){
			this.moreInfo = buildMoreInfo;
			return this;
		}

		public ErrorDetails build(){
			return new ErrorDetails(this);
		}
	}

	public String getMessage() {
		return message;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public MoreInfo getMoreInfo() {
		return moreInfo;
	}
	
	public static ErrorDetailsBuilder getErrorBuilder(HttpStatus buildHttpStatusCode, String friendlyMessage, String buildDeveloperMessage){
		return new ErrorDetailsBuilder(buildHttpStatusCode,friendlyMessage,buildDeveloperMessage);
	}

}
