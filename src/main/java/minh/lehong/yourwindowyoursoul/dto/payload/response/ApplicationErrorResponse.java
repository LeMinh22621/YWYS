package minh.lehong.yourwindowyoursoul.dto.payload.response;

import org.springframework.http.HttpStatus;

import java.io.Serializable;


public class ApplicationErrorResponse implements Serializable {

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

//	@Override
//	public String toString() {
//		StringBuilder stringBuilder = new StringBuilder();
//		stringBuilder.append("{");
//		stringBuilder.append("message:");
//		stringBuilder.append(this.message);
//		stringBuilder.append("status:");
//		stringBuilder.append(this.status.value());
//		stringBuilder.append("}");
//		return stringBuilder.toString();
//	}
}
