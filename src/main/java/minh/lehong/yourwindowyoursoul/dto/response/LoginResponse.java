package minh.lehong.yourwindowyoursoul.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponse implements Serializable {
    @JsonProperty("tokens")
    private TokenResponse tokenResponse;

    @JsonProperty("user")
    private UserResponse userResponse;
}
