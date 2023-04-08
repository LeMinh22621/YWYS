package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.MotivationalQuote;
import minh.lehong.yourwindowyoursoul.model.entity.Timer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    @JsonProperty("room_id")
    private String roomId;
    @JsonProperty("background")
    private BackgroundDto backgroundDto;
    @JsonProperty("timer")
    private TimerDto timerDto;
    @JsonProperty("motivational_quote")
    private MotivationalQuoteDto motivationalQuoteDto;
    @JsonProperty("user")
    private UserDto userDto;
}
