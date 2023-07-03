package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "theme")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Theme implements Serializable
{
    private final long serialVersionUID = 1234567890l;
    @Id
    @Column(name = "theme_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID themeId;

    @Column(name = "theme_name")
    private String themeName;
    @Column(name = "image_link")
    private String imageLink;

    @OneToMany(mappedBy = "theme", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Background> backgrounds;
}
