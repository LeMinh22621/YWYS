package minh.lehong.yourwindowyoursoul.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotivationalQuoteDto implements Serializable {
    private String motivationalQuoteId;
    private String author;
    private String content;
}
