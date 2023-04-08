package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimerDto {
    @JsonProperty("timer_id")
    private String timerId;
    @JsonProperty("short_break")
    private Long shortBreak;
    @JsonProperty("long_break")
    private Long longBreak;
    @JsonProperty("pomodoro_time")
    private Long pomodoroTime;
}
