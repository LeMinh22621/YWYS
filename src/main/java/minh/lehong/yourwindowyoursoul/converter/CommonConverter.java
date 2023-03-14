package minh.lehong.yourwindowyoursoul.converter;

import minh.lehong.yourwindowyoursoul.dto.Response;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.payload.response.UserResponse;

public interface CommonConverter {
    public UserResponse convertUserEntityToUserData(final User user);

    User convertUserRequestToUserEntity(final UserRequest userRequest);

    Response convertToResponse(final Object data, final int returnCode, final String message, String... title);
}
