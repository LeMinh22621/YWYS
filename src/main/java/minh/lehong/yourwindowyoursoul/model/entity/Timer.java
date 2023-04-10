package minh.lehong.yourwindowyoursoul.model.entity;

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
        this.longBreak = 900000l;
        this.shortBreak = 300000l;
        this.pomodoroTime = 1500000l;
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
}
