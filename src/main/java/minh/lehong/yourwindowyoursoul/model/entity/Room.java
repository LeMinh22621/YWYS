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
    private final long serialVersionUID = 1234567890l;

    public Room(User user, Background background, MotivationalQuote motivationalQuote, Timer timer)
    {
        this.user = user;
        this.background = background;
        this.motivationalQuote = motivationalQuote;
        this.timer = timer;
    }

    @Id
    @Column(name = "room_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID roomId;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    @Lob
    private String description;
    @Column(name = "members")
    private int members;
    @Column(name = "is_public")
    private Boolean isPublic;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "background_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Background background;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "timer_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Timer timer;

    @ManyToOne
    @JoinColumn(name = "motivational_quote_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private MotivationalQuote motivationalQuote;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<TaskManager> taskManagers;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Label> labels;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "room_sound", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "sound_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Sound> sounds;

    @ManyToMany(mappedBy = "rooms", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<User> users;
}
