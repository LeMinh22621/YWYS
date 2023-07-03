package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimerRequest implements Serializable {
    @JsonProperty("timer_id")
    private String timerId;
    @JsonProperty("short_break")
    private Long shortBreak;
    @JsonProperty("long_break")
    private Long longBreak;
    @JsonProperty("pomodoro_time")
    private Long pomodoroTime;
    @JsonProperty("loop_times")
    private Long loopTimes;

    @JsonProperty("gr_long_break")
    private Long grLongBreak;
    @JsonProperty("gr_short_break")
    private Long grShortBreak;
    @JsonProperty("gr_pomodoro_time")
    private Long grPomodoroTime;
    @JsonProperty("gr_loop_times")
    private Long grLoopTimes;
}
