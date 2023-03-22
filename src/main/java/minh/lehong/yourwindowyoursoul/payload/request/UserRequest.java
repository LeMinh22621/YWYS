package minh.lehong.yourwindowyoursoul.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.lang.Nullable;

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
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Date dateCreated;

	@JsonProperty("password")
	private String password;

	@JsonProperty("date_modified")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Date dateModified;

	@JsonProperty("is_disabled")
	private boolean isDisabled;

	@JsonProperty("url_avatar")
	@Nullable
	private String urlAvatar;
}
