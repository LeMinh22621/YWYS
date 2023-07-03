package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoomRequest implements Serializable {
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("is_public")
    private Boolean isPublic;
    @JsonProperty("members")
    private Integer members;
    @JsonProperty("background_id")
    private String backgroundId;
    @JsonProperty("timer")
    private TimerRequest timerRequest;
    @JsonProperty("motivational_quote_id")
    private String motivationalQuoteId;
}
