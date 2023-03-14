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

	public DBException(String messsage) {
		super();
		this.messsage = messsage;
	}

	public DBException(String messsage, HttpStatus status) {
		super();
		this.messsage = messsage;
		this.status = status;
	}

	private String messsage;

	private HttpStatus status = HttpStatus.BAD_REQUEST;

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
