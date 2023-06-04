package minh.lehong.yourwindowyoursoul.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@NoArgsConstructor
	public class DBException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DBException(String message) {
		super();
		this.message = message;
	}

	public DBException(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}

	private String message;

	private HttpStatus status = HttpStatus.BAD_REQUEST;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
