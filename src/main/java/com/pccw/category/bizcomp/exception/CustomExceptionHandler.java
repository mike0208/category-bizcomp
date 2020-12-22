package com.pccw.category.bizcomp.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pccw.category.bizcomp.constants.Constants;



/**
 * This is Custom Exceptions handler class
 * 
 * @author 20023424
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * This method returns the exception message and details
	 * 
	 * @param ex
	 * @return res
	 */
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Object> handleInternalServerErrorException(CustomException ex) {
		Map<String, String> res = new HashMap<>();
		CustomException exceptionResponse = new CustomException(ex.getMessage(), ex.getDetails());
		res.put(Constants.MESSAGE, exceptionResponse.getMessage());
		res.put(Constants.DETAILS, exceptionResponse.getDetails());
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * This method returns the query exception message and details
	 * 
	 * @param ex
	 * @return res
	 */
	@ExceptionHandler(value = QueryException.class)
	public ResponseEntity<Object> handleQueryException(QueryException ex) {
		Map<String, String> res = new HashMap<>();
		QueryException exceptionResponse = new QueryException(ex.getMessage(), ex.getDetails(),HttpStatus.INTERNAL_SERVER_ERROR);
		res.put(Constants.MESSAGE, exceptionResponse.getMessage());
		res.put(Constants.DETAILS, exceptionResponse.getDetails());
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * This method returns the persistence exception message and details
	 * 
	 * @param ex
	 * @return res
	 */
	@ExceptionHandler(value = PersistenceException.class)
	public ResponseEntity<Object> handlePersistenceException(PersistenceException ex) {
		Map<String, String> res = new HashMap<>();
		PersistenceException exceptionResponse = new PersistenceException(ex.getMessage(), ex.getDetails(),HttpStatus.INTERNAL_SERVER_ERROR);
		res.put(Constants.MESSAGE, exceptionResponse.getMessage());
		res.put(Constants.DETAILS, exceptionResponse.getDetails());
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}