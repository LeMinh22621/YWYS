package minh.lehong.yourwindowyoursoul.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable {
    @JsonProperty("tokens")
    private TokenResponse tokenResponse;

    @JsonProperty("user")
    private UserResponse userResponse;
}
