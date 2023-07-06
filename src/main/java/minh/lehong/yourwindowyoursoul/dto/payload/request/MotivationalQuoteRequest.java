package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class MotivationalQuoteRequest implements Serializable {
    @JsonProperty("content")
    private String content;
    @JsonProperty("author")
    private String author;
}
