package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "task_level")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskLevel implements Serializable {
    @Id
    @Column(name = "task_level_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID taskLevelId;

    @Column
    private String name;

    @OneToMany(mappedBy = "taskLevel", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Task> tasks;
}
