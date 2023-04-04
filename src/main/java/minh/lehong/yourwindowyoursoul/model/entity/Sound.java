package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "sound")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sound implements Serializable {
    @Id
    @Column(name = "sound_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID soundId;

    @Column
    private String link;

    @Column
    private String name;

    @ManyToMany(mappedBy = "sounds",  fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Room> rooms;
}
