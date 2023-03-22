package minh.lehong.yourwindowyoursoul.converter;

import minh.lehong.yourwindowyoursoul.dto.Response;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.payload.response.UserResponse;

import java.time.LocalDate;
import java.util.Date;

public interface CommonConverter {
    public UserResponse convertUserEntityToUserData(final User user);

    User convertUserRequestToUserEntity(final UserRequest userRequest);

    User convertSignupRequestToUserEntity(final SignupRequest signupRequest);

    Response convertToResponse(final Object data, final int returnCode, final String message, String... title);

    Date convertStringToDate(String dateString);
}
