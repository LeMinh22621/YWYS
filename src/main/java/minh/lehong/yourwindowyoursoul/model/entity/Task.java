package minh.lehong.yourwindowyoursoul.model.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task")
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task extends EntityCommon implements Serializable {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID taskId;

    @Column(name = "task_priority")
    private Integer taskPriority;

    @Column(name = "task_intend")
    private Long taskIntend;

    @Column(name = "is_done")
    private Long isDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_level_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TaskLevel taskLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Room room;
}
