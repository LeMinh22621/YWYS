package minh.lehong.yourwindowyoursoul.facade.impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.constant.MessageConstant;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.common.Response;
import minh.lehong.yourwindowyoursoul.facade.UserFacade;
import minh.lehong.yourwindowyoursoul.dto.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.dto.response.LoginResponse;
import minh.lehong.yourwindowyoursoul.dto.response.TokenResponse;
import minh.lehong.yourwindowyoursoul.dto.response.UserResponse;
import minh.lehong.yourwindowyoursoul.enterprise.ep.utils.EPAccessTokenUtil;
import minh.lehong.yourwindowyoursoul.entity.User;
import minh.lehong.yourwindowyoursoul.jwt.service.JwtTokenService;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonConverter commonConverter;

    @Autowired
    private MessageSource message;

    @Resource
    private JwtTokenService jwtService;

    @Override
    public Response login(LoginRequest request) {
        Response response = new Response();
        final LoginResponse loginResponse = new LoginResponse();
        if (request != null) {
            final User user = userService.findByEmail(request.getEmail());
            final UserResponse userResponse = commonConverter.convertUserEntityToUserResponse(user);
            if (userResponse != null && userResponse.getPassword() != null
                    && new BCryptPasswordEncoder().matches(request.getPassword(), userResponse.getPassword())) {

                // get Access Token and CMS token
                final TokenResponse tokenResponse = new TokenResponse();
                final String accessToken = getAccessTokenUser(user);
                tokenResponse.setAccessToken(accessToken);
                tokenResponse.setExpiredTime(EPAccessTokenUtil.getExpiredTime(accessToken));

                if (userResponse.isDisabled()) {
                    response = commonConverter.convertToResponse(loginResponse, Constant.RETURN_NG ,
                            message.getMessage(MessageConstant.ACCOUNT_DISABLE, null, LocaleContextHolder.getLocale()));
                } else {
                    response.setStatus(true);
                    // build login response
                    loginResponse.setUserResponse(userResponse);
                    loginResponse.setTokenResponse(tokenResponse);

                    response = commonConverter.convertToResponse(loginResponse, Constant.RETURN_OK,
                            message.getMessage(MessageConstant.SUCCESS, null, LocaleContextHolder.getLocale()));
                }
            }
            else {
                response = commonConverter.convertToResponse(null, Constant.RETURN_NG,
                        message.getMessage(MessageConstant.LOGIN_ERROR, null, LocaleContextHolder.getLocale()));
            }
        } else {
            response = commonConverter.convertToResponse(null, Constant.RETURN_NG,
                    message.getMessage(MessageConstant.EXCEPTION_400, null, LocaleContextHolder.getLocale()));
        }
        return response;
    }

    private String getAccessTokenUser(final User user) {
        return jwtService.generateTokenLogin(user.getEmail());
    }
}
