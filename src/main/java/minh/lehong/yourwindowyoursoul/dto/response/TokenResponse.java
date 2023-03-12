package minh.lehong.yourwindowyoursoul.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TokenResponse implements Serializable {
    @JsonProperty("expires")
    private long expiredTime;

    @JsonProperty("token")
    private String accessToken;
}
