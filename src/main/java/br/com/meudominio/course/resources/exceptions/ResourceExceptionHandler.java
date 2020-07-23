package br.com.meudominio.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.meudominio.course.services.exceptions.DataBaseException;
import br.com.meudominio.course.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest req) {

		String error = "Resource not found.";

		HttpStatus status = HttpStatus.NOT_FOUND;

		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				req.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest req) {

		String error = "Database error.";

		HttpStatus status = HttpStatus.BAD_REQUEST;

		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				req.getRequestURI());

		return ResponseEntity.status(status).body(err);
	}

}
