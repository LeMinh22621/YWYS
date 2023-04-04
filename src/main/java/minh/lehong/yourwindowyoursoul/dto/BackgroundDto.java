package minh.lehong.yourwindowyoursoul.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackgroundDto {
    private String backgroundId;
    private String backgroundLink;
    private ThemeDto themeDto;
}
