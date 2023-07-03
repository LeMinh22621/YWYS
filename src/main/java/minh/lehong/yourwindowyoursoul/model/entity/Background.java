package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "background")
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Background extends EntityCommon implements Serializable {
    private final long serialVersionUID = 1234567890l;
    @Id
    @Column(name = "background_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID backgroundId;
    @Column(name = "link")
    private String link;
    @Column(name = "image_link")
    private String imageLink;
    @ManyToOne
    @JoinColumn(name = "theme_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Theme theme;

    @OneToMany(mappedBy = "background", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Room> rooms;
}
