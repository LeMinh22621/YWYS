package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
public class UserResponse implements Serializable {
    @JsonProperty("id")
    private UUID id;

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

    @JsonProperty("is_deleted")
    private boolean isDeleted;

    @JsonProperty("url_avatar")
    private String urlAvatar;

    @JsonProperty("role")
    private Role role;
}
