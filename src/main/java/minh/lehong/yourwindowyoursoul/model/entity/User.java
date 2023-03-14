package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user")
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends EntityCommon implements Serializable {
    @Id
    @Column(length = 50, name = "user_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "url_avatar")
    private String urlAvatar;

    @Column(name = "is_disabled")
    private boolean isDisabled;
}
