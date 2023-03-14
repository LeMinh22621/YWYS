package minh.lehong.yourwindowyoursoul.payload.request.accesstoken;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class EPTokenGenerationUserModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;

	@JsonProperty("type")
	private String type;

	@JsonProperty("authentication_mechanism")
	private String authenticationMechanism;

	@Override
	public String toString()
	{
		return "TokenGenerationRequestModel{" + "email='" + email + '\'' + ", password='" + password + '\'' + ", type='" + type
				+ '\'' + ", authentication_mechanism='" + authenticationMechanism + '\'' + '}';
	}
}
