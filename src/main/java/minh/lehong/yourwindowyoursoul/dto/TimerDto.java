package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimerDto implements Serializable {
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
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("update_date")
    private Date updateDate;
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
