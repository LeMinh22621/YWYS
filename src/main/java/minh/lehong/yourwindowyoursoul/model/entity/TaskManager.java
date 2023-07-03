package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "task_manager")
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskManager extends EntityCommon implements Serializable {
    private final long serialVersionUID = 1234567890l;
    @Id
    @Column(name = "task_manager_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID taskManagerId;

    @Column(name = "task_manager_title")
    private String taskManagerTitle;

    @OneToMany(mappedBy = "taskManager", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Room room;
}
