package minh.lehong.yourwindowyoursoul.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import minh.lehong.yourwindowyoursoul.constant.enums.ExceptionEnums;
import minh.lehong.yourwindowyoursoul.dto.payload.response.ApplicationErrorResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.SignatureException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//@ControllerAdvice
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		Response error = new Response(null, false, ex.getMessage(), status.value());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		Response error = new Response(null, false, ex.getMessage(), status.value());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		Response error = new Response(null, false, ex.getMessage(), status.value());
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ex.printStackTrace();
		String message = "";
		if (ex.getBindingResult().hasErrors()) {
			for (FieldError e : ex.getBindingResult().getFieldErrors()) {
				message = message.concat(e.getField()).concat(" ").concat(Objects.requireNonNull(e.getDefaultMessage()).concat(". "));
			}
		}
		ex.printStackTrace();
		Response error = new Response(null, false, message, status.value());
		return ResponseEntity.status(status).body(error);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(DBException.class)
	public ResponseEntity<Response> handlerDBException(DBException ex) {
		ex.printStackTrace();
		Response error = new Response(null, false, ex.getMessage(), ex.getStatus().value());
		return ResponseEntity.status(ex.getStatus()).body(error);
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Response> handleAccessDeniedException(AuthenticationException ex, WebRequest request) {
		ex.printStackTrace();
		Response error = new Response(null, false, ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleAccessDeniedException(Exception ex, WebRequest request) {
		ex.printStackTrace();
		Response error = new Response(null, false, ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpiredException(ExpiredJwtException ex) {
		ex.printStackTrace();
		Response error = new Response(null, false, ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<Object> handleAccessDeniedException(SignatureException ex) {
		ex.printStackTrace();
		Response error = new Response(null, false, ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler(ParseException.class)
	public ResponseEntity<Object> handleAccessDeniedException(ParseException ex) {
		ex.printStackTrace();
		Response error = new Response(null, false, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
