package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import minh.lehong.yourwindowyoursoul.dto.ThemeDto;

@Data
public class BackgroundResponse {
    @JsonProperty("background_id")
    private String backgroundId;
    @JsonProperty("background_link")
    private String backgroundLink;
    @JsonProperty("image_link")
    private String imageLink;
    @JsonProperty("theme_id")
    private String themeId;
}
