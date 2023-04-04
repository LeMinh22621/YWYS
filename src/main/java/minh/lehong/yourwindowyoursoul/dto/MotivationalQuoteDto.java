package minh.lehong.yourwindowyoursoul.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotivationalQuoteDto {
    private String motivationalQuoteId;
    private String author;
    private String content;
}
