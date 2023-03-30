package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "room")
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room extends EntityCommon implements Serializable {

    @Id
    @Column(name = "room_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID roomId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "background_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Background background;

    @ManyToOne
    @JoinColumn(name = "timer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Timer timer;

    @ManyToOne
    @JoinColumn(name = "motivational_quote_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MotivationalQuote motivationalQuote;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Task> tasks;

    @ManyToMany
    @JoinTable(name = "room_sound", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "sound_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Sound> sounds;
}
