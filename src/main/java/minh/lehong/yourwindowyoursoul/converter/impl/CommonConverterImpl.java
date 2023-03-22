package minh.lehong.yourwindowyoursoul.converter.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.Response;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.payload.response.UserResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@Component
public class CommonConverterImpl implements CommonConverter {
    @Override
    public UserResponse convertUserEntityToUserData(final User user) {
        UserResponse userResponse = null;
        if(user != null){
            userResponse = new UserResponse();

            userResponse.setId(user.getUserId());
            userResponse.setEmail(user.getEmail());
            userResponse.setPassword(user.getPassword());
            userResponse.setDisabled(user.isDisabled());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setDateCreated(user.getCreateDate());
            userResponse.setUrlAvatar(user.getUrlAvatar());
            userResponse.setDateModified(user.getUpdatedDate());
        }
        return userResponse;
    }
    @Override
    public User convertUserRequestToUserEntity(final UserRequest userRequest) {
        User user = null;
        if(userRequest != null){
            user = new User();
            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setUrlAvatar(userRequest.getUrlAvatar());
            user.setPassword(userRequest.getPassword());
            user.setDisabled(userRequest.isDisabled());
            user.setCreateDate(userRequest.getDateCreated());
            user.setUpdatedDate(userRequest.getDateModified());
        }
        return user;
    }
    public User convertSignupRequestToUserEntity(final SignupRequest signupRequest)
    {
        User user = null;
        if(signupRequest != null){
            user = new User();
            user.setEmail(signupRequest.getEmail());
            user.setFirstName(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());
            user.setUrlAvatar(null);
            user.setPassword(signupRequest.getPassword());
            user.setDisabled(false);
            user.setCreateDate(new Date());
            user.setUpdatedDate(new Date());
        }
        return user;
    }

    @Override
    public Response convertToResponse(final Object data, final int returnCode, final String message, String... title) {
        final Response response = new Response();
        response.setData(data);
        response.setMessage(message);
        response.setStatus(returnCode == Constant.RETURN_OK);
        response.setReturnCode(returnCode);
        if (Objects.nonNull(title) && title.length > 0) {
            response.setTitle(title[0]);
        }
        return response;
    }

    @Override
    public Date convertStringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDate date = LocalDate.parse(dateString, formatter);

        return new Date(date.toEpochDay());
    }
}
