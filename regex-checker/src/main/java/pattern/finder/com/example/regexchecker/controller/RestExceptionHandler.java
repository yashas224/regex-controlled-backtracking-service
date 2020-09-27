package pattern.finder.com.example.regexchecker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import pattern.finder.com.example.regexchecker.dto.RegexResponse;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<RegexResponse> handleException(Exception e) {
		RegexResponse response = new RegexResponse();
		response.setError(true);
		response.setMatch(null);
		return new ResponseEntity<RegexResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
