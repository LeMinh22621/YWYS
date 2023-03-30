package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "motivational_quote")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MotivationalQuote implements Serializable {
    @Id
    @Column(name = "motivational_quote_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID motivationalQuoteId;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    @OneToMany(mappedBy = "motivationalQuote", fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Room> rooms;
}
