package minh.lehong.yourwindowyoursoul.facade.Impl;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.var;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.UserDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.AuthenticationResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.dto.payload.response.UserResponse;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.facade.UserFacade;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.service.JwtService;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Response register(SignupRequest request) throws DBException, Exception{
        Response response;
        if(userService.findByEmail(request.getEmail()) != null)
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
                user = userService.save(user);

                String jwtToken = jwtService.generateToken(user);

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
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);

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
        User user = userService.findByEmail(jwtService.extractUsername(token));

        Response response = new Response();
        response.setData(commonConverter.convertUserDtoToUserResponse(commonConverter.convertUserEntityToUserDto(user)));
        response.setStatus(jwtService.isTokenValid(token, user));
        response.setReturnCode(HttpStatus.OK.value());
        response.setMessage("Check Expired Token Success");

        return response;
    }

    @Override
    public Response findAllByIsDeletedAndRole(Role role) {
        Response response;

        try{
            List<User> users = userService.findAllByRole(role);
            if(users != null && !users.isEmpty())
            {
                List<UserResponse> userResponses = users
                        .stream()
                        .map(userEntity -> {
                            try {
                                return commonConverter.convertUserDtoToUserResponse(commonConverter.convertUserEntityToUserDto(userEntity));
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toList());
                response = new Response(userResponses, true, "GET All User Account Success!", HttpStatus.OK.value());
            }
            else {
                response = new Response(null, false, "Have No User Account!", HttpStatus.NO_CONTENT.value());
            }
        }
        catch (Exception e)
        {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }

    @Override
    public Response deleteUserByUserId(String userId) {
        Response response;

        try{
            User user = userService.findByUserId(UUID.fromString(userId));
            if(user != null)
            {
                user.setIsDeleted(true);
                user = userService.save(user);
                response = new Response(
                        commonConverter.convertUserDtoToUserResponse(commonConverter.convertUserEntityToUserDto(user)),
                        true, String.format("Deleted User By userId = %s ", userId), HttpStatus.OK.value());
            }
            else {
                response = new Response(null, false, "Have No That UserId", HttpStatus.NO_CONTENT.value());
            }
        }
        catch (Exception e)
        {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }
    @Override
    public Response updateUser(String userId, UserRequest userRequest) {
        Response response;
        try{
            User user = userService.findByUserId(UUID.fromString(userId));
            if(user != null)
            {
                UserDto userDto = commonConverter.convertUserEntityToUserDto(user);
                userDto = commonConverter.convertUserRequestToUserDto(userDto, userRequest);
                userDto.setDateModified(commonConverter.convertToG7(new Date()));
                user = commonConverter.convertUserDtoToUserEntity(user, userDto);
                user = userService.save(user);
                response = new Response(
                        commonConverter.convertUserEntityToUserResponse(user),
                        true,
                        "Update User Success!",
                        HttpStatus.OK.value());
            }
            else
                response = new Response(null, false, "Have No That UserId", HttpStatus.NO_CONTENT.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }
}
