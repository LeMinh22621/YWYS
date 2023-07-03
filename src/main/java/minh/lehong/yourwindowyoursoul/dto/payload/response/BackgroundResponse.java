package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import minh.lehong.yourwindowyoursoul.dto.ThemeDto;

import java.io.Serializable;

@Data
public class BackgroundResponse implements Serializable {
    @JsonProperty("background_id")
    private String backgroundId;
    @JsonProperty("background_link")
    private String backgroundLink;
    @JsonProperty("image_link")
    private String imageLink;
    @JsonProperty("theme_id")
    private String themeId;
}
