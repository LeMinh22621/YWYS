package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BackgroundRequest implements Serializable {
    @JsonProperty("background_link")
    private String backgroundLink;
    @JsonProperty("theme_id")
    private String themeId;
    @JsonProperty("background_image")
    private String backgroundImage;
}
