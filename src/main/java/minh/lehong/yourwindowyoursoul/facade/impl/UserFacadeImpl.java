package minh.lehong.yourwindowyoursoul.facade.impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.constant.MessageConstant;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.Response;
import minh.lehong.yourwindowyoursoul.facade.UserFacade;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.payload.response.LoginResponse;
import minh.lehong.yourwindowyoursoul.payload.response.TokenResponse;
import minh.lehong.yourwindowyoursoul.payload.response.UserResponse;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

@Service
public class UserFacadeImpl implements UserFacade {
    @Autowired
    CommonConverter commonConverter;

    @Autowired
    UserService userService;

    @Override
    public Response login(LoginRequest request) throws ParseException {
        Response response = null;
        if(request != null)
        {
            final User user = userService.findByEmail(request.getEmail());
            UserResponse userResponse = commonConverter.convertUserEntityToUserData(user);

            LoginResponse loginResponse = new LoginResponse();

            if(userResponse != null &&
                    userResponse.getPassword() != null
                    && new BCryptPasswordEncoder().matches(request.getPassword(), userResponse.getPassword()))
            {
                if(userResponse.isDisabled())
                {
                    response = commonConverter.convertToResponse(loginResponse, Constant.IS_DISABLE, MessageConstant.LOGIN_ERROR);
                }
                else{
//                    final TokenResponse tokenResponse = new TokenResponse();
//                    final String accessToken = getAccessTokenUser(user.getEmail());
//
//                    tokenResponse.setAccessToken(accessToken);
//                    tokenResponse.setExpiredTime(EPAccessTokenUtil.getExpiredTime(accessToken));

//                    loginResponse.setTokenResponse(tokenResponse);
                    loginResponse.setUserResponse(userResponse);

                    response = commonConverter.convertToResponse(loginResponse, Constant.LOGIN_SUCCESS, MessageConstant.LOGIN_SUCCESS);
                }
            }
            else {
                response = commonConverter.convertToResponse(loginResponse, Constant.LOGIN_ERROR, MessageConstant.LOGIN_ERROR);
            }
        }
        return response;
    }
//    private String getAccessTokenUser(String email) {
//        return jwtService.generateTokenLogin(email);
//    }

    @Override
    public Response signup(SignupRequest signupRequest) {
        Response response = null;
        if(signupRequest != null)
        {
            if(signupRequest.getPassword() != null)
            {
                signupRequest.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
                User user = commonConverter.convertSignupRequestToUserEntity(signupRequest);
                if(userService.existsUserByEmail(user.getEmail()) == false)
                {
                    if(userService.save(user) != null)
                    {
                        response = commonConverter.convertToResponse("Sign-up Success", Constant.SIGNUP_SUCCESS, MessageConstant.SIGNUP_SUCCESS);
                    }
                    else
                    {
                        response = commonConverter.convertToResponse("Sign-up Error", Constant.SIGNUP_ERROR, MessageConstant.SIGNUP_ERROR);
                    }
                }
                else {
                    response = commonConverter.convertToResponse("Sign-up Error", Constant.HAD_ACCOUNT, MessageConstant.HAD_ACCOUNT);
                }
            }
            else {
                response = commonConverter.convertToResponse("Sign-up Error", Constant.PASSWORD_NULL, MessageConstant.PASSWORD_NULL);
            }
        }
        return response;
    }

    @Override
    public Response update(UserRequest userRequest) {
        Response response = null;

        if(userRequest != null)
        {
            User newUser = commonConverter.convertUserRequestToUserEntity(userRequest);
            if(newUser != null)
            {
                User oldUser = userService.findByEmail(newUser.getEmail());
                if(oldUser != null)
                {
                    oldUser = commonConverter.convertOldUserToNewUser(oldUser, newUser);

                    if(userService.save(oldUser) != null) {
                        response = commonConverter.convertToResponse("Update Success", HttpStatus.OK.value(), MessageConstant.UPDATE_SUCCESS);

                        return response;
                    }
                }
            }
        }
        response = commonConverter.convertToResponse("Update FAILED", HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageConstant.UPDATE_ERROR);
        return response;
    }


}
