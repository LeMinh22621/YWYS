package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "label")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Label extends EntityCommon implements Serializable {
    @Id
    @Column(name = "label_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID labelId;

    @Column
    private String name;

    @Column
    private String color;

    @OneToMany(mappedBy = "label", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<TaskLabel> taskLabels;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Room room;
}
