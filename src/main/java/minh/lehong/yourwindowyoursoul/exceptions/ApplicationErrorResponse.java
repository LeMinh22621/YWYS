package minh.lehong.yourwindowyoursoul.exceptions;

import org.springframework.http.HttpStatus;


public class ApplicationErrorResponse {

	private String message;
	
	private HttpStatus status;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
