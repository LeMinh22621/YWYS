package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeDto {
    @JsonProperty("theme_id")
    private String themeId;
    @JsonProperty("theme_name")
    private String themeName;
    @JsonProperty("image_link")
    private String imageLink;
}
