package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("update_date")
    private Date updateDate;
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
}
