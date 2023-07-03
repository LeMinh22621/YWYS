package minh.lehong.yourwindowyoursoul.model.entity;

import lombok.*;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "user")
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends EntityCommon implements UserDetails, Serializable{
    private final long serialVersionUID = 1234567890l;
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID userId;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "url_avatar")
    private String urlAvatar;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_room", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Room> rooms;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
