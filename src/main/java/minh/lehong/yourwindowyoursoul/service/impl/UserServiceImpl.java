package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.constant.MessageConstant;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.Response;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.payload.response.LoginResponse;
import minh.lehong.yourwindowyoursoul.payload.response.TokenResponse;
import minh.lehong.yourwindowyoursoul.payload.response.UserResponse;
import minh.lehong.yourwindowyoursoul.repository.UserRepository;
import minh.lehong.yourwindowyoursoul.security.jwt.service.JwtTokenService;
import minh.lehong.yourwindowyoursoul.service.UserService;
import minh.lehong.yourwindowyoursoul.utils.EPAccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CommonConverter commonConverter;
    @Resource
    private JwtTokenService jwtService;

    @Override
    public Response login(LoginRequest request)
    {
        Response response = null;
        if(request != null)
        {
            final User user = this.findByEmail(request.getEmail());
            UserResponse userResponse = commonConverter.convertUserEntityToUserData(user);

            LoginResponse loginResponse = new LoginResponse();

            if(userResponse != null &&
                    userResponse.getPassword() != null &&
                    new BCryptPasswordEncoder().matches(userResponse.getPassword(), request.getPassword()))
            {
                if(userResponse.isDisabled())
                {
                    response = commonConverter.convertToResponse(loginResponse, Constant.IS_DISABLE, MessageConstant.LOGIN_ERROR);
                }
                else{
                    final TokenResponse tokenResponse = new TokenResponse();
                    final String accessToken = getAccessTokenUser(user.getEmail());

                    tokenResponse.setAccessToken(accessToken);
                    tokenResponse.setExpiredTime(EPAccessTokenUtil.getExpiredTime(accessToken));

                    loginResponse.setTokenResponse(tokenResponse);
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
    private String getAccessTokenUser(String email) {
        return jwtService.generateTokenLogin(email);
    }

    @Override
    public Response signup(UserRequest userRequest) {
        Response response = null;
        if(userRequest != null)
        {
            User user = commonConverter.convertUserRequestToUserEntity(userRequest);

            if(this.save(user) != null)
            {
                response = commonConverter.convertToResponse("Sign-in Success", Constant.LOGIN_SUCCESS, MessageConstant.LOGIN_SUCCESS);
            }
            else
            {
                response = commonConverter.convertToResponse("Sign-in Error", Constant.LOGIN_ERROR, MessageConstant.LOGIN_ERROR);
            }
        }
        return response;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
