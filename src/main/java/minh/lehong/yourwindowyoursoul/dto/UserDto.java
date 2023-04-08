package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("email")
    private String email;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("role")
    private Role role;
    @JsonProperty("avatar_link")
    private String avatarUrl;
}
