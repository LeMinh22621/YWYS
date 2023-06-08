package minh.lehong.yourwindowyoursoul.service.impl;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.var;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.UserDto;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.swing.text.html.HTML;
import java.text.ParseException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    PasswordEncoder passwordEncoder;
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
    public Response register(SignupRequest request) throws DBException, Exception{
        Response response;
        if(userRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new DBException("This email has register!", HttpStatus.CONFLICT);
        }
        else {
            AuthenticationResponse authenticationResponse;
            try
            {
                UserDto userDto = commonConverter.convertSignupRequestToUserDto(new UserDto(), request);
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                User user = commonConverter.convertUserDtoToUserEntity(new User(), userDto);
                user = userRepository.save(user);

                var jwtToken = jwtService.generateToken(user);

                authenticationResponse = AuthenticationResponse.builder()
                        .token(jwtToken)
                        .user(commonConverter.convertUserDtoToUserResponse(commonConverter.convertUserEntityToUserDto(user)))
                        .build();
                response = new Response(authenticationResponse, true, "Register Success!", HttpStatus.OK.value());
            }
            catch (Exception e)
            {
                response = new Response(null, true, "Register Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
        return response;
    }

    @Override
    public Response login(LoginRequest request) throws Exception
    {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                ));
        User user = (User) authentication.getPrincipal();
        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(jwtToken)
                .user(commonConverter.convertUserDtoToUserResponse(commonConverter.convertUserEntityToUserDto(user)))
                .build();
        return new Response(authenticationResponse, true, "Login Success!", HttpStatus.OK.value());
    }

    @Override
    public Response logout(String tokenHeader) throws ParseException, ExpiredJwtException, Exception {
        String newToken = jwtService.invalidToken(tokenHeader);
        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token(newToken)
                .user(null)
                .build();
        return new Response(authenticationResponse, true, "Logout Success!", HttpStatus.OK.value());
    }

    @Override
    public Response checkExpiredToken(String token) throws ParseException {
        User user = userRepository.findByEmail(jwtService.extractUsername(token)).orElseThrow(() -> new DBException("not Found User by Email extracted from token"));

        Response response = new Response();
        response.setData(commonConverter.convertUserDtoToUserResponse(commonConverter.convertUserEntityToUserDto(user)));
        response.setStatus(jwtService.isTokenValid(token, user));
        response.setReturnCode(HttpStatus.OK.value());
        response.setMessage("Check Expired Token Success");

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).get();
    }
}
