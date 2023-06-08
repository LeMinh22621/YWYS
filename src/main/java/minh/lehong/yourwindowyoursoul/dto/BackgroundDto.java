package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackgroundDto {
    @JsonProperty("background_id")
    private String backgroundId;
    @JsonProperty("background_link")
    private String backgroundLink;
    @JsonProperty("image_link")
    private String imageLink;
    @JsonProperty("theme")
    private ThemeDto themeDto;

}
