package minh.lehong.yourwindowyoursoul.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class UserRequest implements Serializable {
	@JsonProperty("email")
	private String email;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("date_created")
	private Date dateCreated;

	@JsonProperty("password")
	private String password;

	@JsonProperty("date_modified")
	private Date dateModified;

	@JsonProperty("is_disabled")
	private boolean isDisabled;

	@JsonProperty("url_avatar")
	private String urlAvatar;
}
