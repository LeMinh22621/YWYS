package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

@Data
public class FullThemeResponse implements Serializable {

    @JsonProperty("theme_id")
    private String themeId;
    @JsonProperty("theme_name")
    private String themeName;
    @JsonProperty("image_link")
    private String imageLink;

    @JsonProperty("background_list")
    private List<BackgroundResponse> backgroundResponses;
}
