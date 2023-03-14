package minh.lehong.yourwindowyoursoul.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class EpUserResponse implements Serializable {

	private static final long serialVersionUID = -9213268584292524651L;

	private String id;

	private boolean password;

	private String type;

	private String name;

	private String email;

	@JsonProperty("authentication_mechanism")
	private String authenticationMechanism;
}
