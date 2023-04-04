package minh.lehong.yourwindowyoursoul.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private String avatarUrl;
}
