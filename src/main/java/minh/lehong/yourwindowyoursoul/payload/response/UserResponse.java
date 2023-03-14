package minh.lehong.yourwindowyoursoul.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserResponse implements Serializable {
    @JsonProperty("id")
    private String id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("date_created")
    private Date dateCreated;

    @JsonProperty("date_modified")
    private Date dateModified;

    @JsonIgnore
    private String password;

    @JsonProperty("is_disabled")
    private boolean isDisabled;

    @JsonProperty("url_avatar")
    private String urlAvatar;
}
