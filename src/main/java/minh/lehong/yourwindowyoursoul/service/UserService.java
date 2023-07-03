package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User findByUserId(UUID userId);
    User save(User user);
    boolean existsUserByEmail(String email);

    List<User> findAllByRole(Role role);
}
