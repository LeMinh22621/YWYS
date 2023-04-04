package minh.lehong.yourwindowyoursoul.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimerDto {
    private String timerId;
    private Long shortBreak;
    private Long longBreak;
    private Long pomodoroTime;
}
