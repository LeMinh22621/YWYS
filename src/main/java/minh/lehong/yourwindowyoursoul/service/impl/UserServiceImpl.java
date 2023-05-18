package minh.lehong.yourwindowyoursoul.service.impl;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.var;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.AuthenticationResponse;
import minh.lehong.yourwindowyoursoul.repository.UserRepository;
import minh.lehong.yourwindowyoursoul.service.JwtService;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CommonConverter commonConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private  JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User findByUserId(UUID userId) {
        return userRepository.findUserByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public AuthenticationResponse register(SignupRequest request) throws DBException, Exception{
        if(userRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new DBException("This email has register!", HttpStatus.CONFLICT);
        }
        else {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();

            userRepository.save(user);

            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        }
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) throws UsernameNotFoundException, Exception
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));

        var user = userRepository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user.get());

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse logout(String tokenHeader) throws ParseException, ExpiredJwtException, Exception {
        String newToken = jwtService.invalidToken(tokenHeader);
        return AuthenticationResponse.builder().token(newToken).build();
    }

    @Override
    public Response checkExpiredToken(String token) {
        UserDetails userDetails = userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow(() -> new DBException("not Found User by Email extracted from token"));

        Response response = new Response();
        response.setData(userDetails);
        response.setStatus(jwtService.isTokenValid(token, userDetails));
        response.setReturnCode(HttpStatus.OK.value());
        response.setTitle(HttpStatus.OK.name());
        response.setMessage("Check Expired Token Success");

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).get();
    }
}
