package minh.lehong.yourwindowyoursoul.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.java.Log;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "timer")
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Timer extends EntityCommon {

    public Timer()
    {
        this.timerId = UUID.randomUUID();
        this.longBreak = 900l;
        this.shortBreak = 300l;
        this.pomodoroTime = 1500l;
        this.loopTimes = 3l;
        this.grLongBreak = 900l;
        this.grShortBreak = 300l;
        this.grPomodoroTime = 1500l;
        this.grLoopTimes = 3l;
        setIsDeleted(false);
    }
    @Id
    @Column(name = "timer_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID timerId;

    @Column(name = "long_break")
    private Long longBreak;
    @Column(name = "short_break")
    private Long shortBreak;
    @Column(name = "pomodoro_time")
    private Long pomodoroTime;
    @Column(name = "loop_times")
    private Long loopTimes;

    @Column(name = "gr_long_break")
    private Long grLongBreak;
    @Column(name = "gr_short_break")
    private Long grShortBreak;
    @Column(name = "gr_pomodoro_time")
    private Long grPomodoroTime;
    @Column(name = "gr_loop_times")
    private Long grLoopTimes;
}
