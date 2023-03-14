package minh.lehong.yourwindowyoursoul.exceptions;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.constant.enums.ExceptionEnums;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApplicationErrorResponse error = new ApplicationErrorResponse();
		error.setMessage(ex.getMessage());
		error.setStatus(status);
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApplicationErrorResponse error = new ApplicationErrorResponse();
		error.setMessage(ExceptionEnums.EXCEPTION_405.getDescription());
		error.setStatus(status);
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApplicationErrorResponse error = new ApplicationErrorResponse();
		error.setMessage("Invalid ID supplied");
		error.setStatus(status);
		return ResponseEntity.status(status).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApplicationErrorResponse error = new ApplicationErrorResponse();
		String message = "";
		if (ex.getBindingResult().hasErrors()) {
			for (FieldError e : ex.getBindingResult().getFieldErrors()) {
				message = message.concat(e.getField()).concat(" ").concat(Objects.requireNonNull(e.getDefaultMessage()).concat(". "));
			}
		}
		error.setMessage(message);
		error.setStatus(status);
		return ResponseEntity.status(status).body(error);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ApplicationErrorResponse> handlerBeneficiaryException(ServiceException exception,
			WebRequest request) {
		ApplicationErrorResponse error = new ApplicationErrorResponse();
		error.setMessage(exception.getMesssage());
		error.setStatus(exception.getStatus());
		return new ResponseEntity(error, exception.getStatus());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(DBException.class)
	public ResponseEntity<ApplicationErrorResponse> handlerDBException(ServiceException exception) {
		ApplicationErrorResponse error = new ApplicationErrorResponse();
		error.setMessage(exception.getMesssage());
		error.setStatus(exception.getStatus());
		return new ResponseEntity(error, exception.getStatus());
	}

//	@ExceptionHandler(NoSuchMessageException.class)
//	public ResponseEntity<Object> handleNoSuchMessageException(NoSuchMessageException noSuchMessageException) {
//		ApplicationErrorResponse error = new ApplicationErrorResponse();
//		error.setMessage(noSuchMessageException.getMessage());
//		error.setStatus(HttpStatus.EXPECTATION_FAILED);
//		return new ResponseEntity(error, );
//	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
		ApplicationErrorResponse error = new ApplicationErrorResponse();
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.UNAUTHORIZED);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ResponseStatus
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
		ApplicationErrorResponse error = new ApplicationErrorResponse();
		error.setMessage("File to large !");
		error.setStatus(HttpStatus.EXPECTATION_FAILED);
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(error);
	}
}
