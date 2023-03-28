package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.payload.response.AuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User findByUserId(UUID userId);
    User save(User user);
    boolean existsUserByEmail(String email);

    AuthenticationResponse register(SignupRequest request);

//    UserDetails loadUserDetailsJWT(UUID userId);
}
