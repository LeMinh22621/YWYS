package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.AuthenticationResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.text.ParseException;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);
    User findByUserId(UUID userId);
    User save(User user);
    boolean existsUserByEmail(String email);

    AuthenticationResponse register(SignupRequest request) throws Exception;

    AuthenticationResponse login(LoginRequest request) throws Exception;

    AuthenticationResponse logout(String tokenHeader) throws ParseException, Exception;
}
