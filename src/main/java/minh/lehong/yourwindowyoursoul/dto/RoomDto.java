package minh.lehong.yourwindowyoursoul.dto;

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
    private BackgroundDto backgroundDto;
    private TimerDto timerDto;
    private MotivationalQuoteDto motivationalQuoteDto;
    private UserDto userDto;
}
