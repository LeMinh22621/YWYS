package minh.lehong.yourwindowyoursoul.model.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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

    @Column(name = "task_content")
    private String taskContent;

    @Column(name = "task_start_date")
    private Date taskStartDate;

    @Column(name = "task_start_time")
    private Long taskStartTime;

    @Column(name = "task_intend")
    private Long taskIntend;

    @Column(name = "is_done")
    private Boolean isDone;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<TaskLabel> taskLabels;

    @ManyToOne
    @JoinColumn(name = "task_manager_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TaskManager taskManager;
}
