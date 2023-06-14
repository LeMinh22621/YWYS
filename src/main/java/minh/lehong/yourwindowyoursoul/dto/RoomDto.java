package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;
import minh.lehong.yourwindowyoursoul.model.entity.Timer;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    @JsonProperty("room_id")
    private String roomId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("members")
    private Integer members;
    @JsonProperty("is_public")
    private Boolean isPublic;
    @JsonProperty("background")
    private BackgroundDto backgroundDto;
    @JsonProperty("timer")
    private TimerDto timerDto;
    @JsonProperty("motivational_quote")
    private MotivationalQuoteDto motivationalQuoteDto;
    @JsonProperty("user")
    private UserDto userDto;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("update_date")
    private Date updateDate;
}
