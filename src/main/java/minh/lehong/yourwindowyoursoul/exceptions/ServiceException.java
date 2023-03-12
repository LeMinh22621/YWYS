package minh.lehong.yourwindowyoursoul.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	private String messsage;

	private HttpStatus status;

	public ServiceException(String messsage) {
		super();
		this.messsage = messsage;
	}

	public ServiceException(String messsage, HttpStatus status) {
		super();
		this.messsage = messsage;
		this.status = status;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
