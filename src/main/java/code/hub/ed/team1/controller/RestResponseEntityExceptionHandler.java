package code.hub.ed.team1.controller;

import code.hub.ed.team1.exception.MovieNotFoundException;
import code.hub.ed.team1.exception.PersonNotFoundException;
import code.hub.ed.team1.exception.ProfessionDoesNotExistException;
import code.hub.ed.team1.exception.TvShowNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(
      value = {
        MovieNotFoundException.class,
        TvShowNotFoundException.class,
        PersonNotFoundException.class
      })
  public ResponseEntity<Object> handleNotFound(RuntimeException exception, WebRequest webRequest) {
    return handleExceptionInternal(
        exception, exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
  }

  @ExceptionHandler(value = {ProfessionDoesNotExistException.class})
  public ResponseEntity<Object> handleNotExists(RuntimeException exception, WebRequest webRequest) {
    return handleExceptionInternal(
        exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
  }
}
