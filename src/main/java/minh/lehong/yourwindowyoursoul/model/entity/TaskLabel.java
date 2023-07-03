package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "task_label")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskLabel extends EntityCommon implements Serializable {
    private final long serialVersionUID = 1234567890l;
    @Id
    @Column(name = "task_label_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID taskLabelId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Task task;

    @ManyToOne
    @JoinColumn(name = "label_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Label label;
}
