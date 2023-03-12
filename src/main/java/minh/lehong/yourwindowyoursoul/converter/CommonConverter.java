package minh.lehong.yourwindowyoursoul.converter;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.dto.common.Response;
import minh.lehong.yourwindowyoursoul.dto.response.UserResponse;
import minh.lehong.yourwindowyoursoul.entity.User;

import java.util.Objects;

public interface CommonConverter {
    UserResponse convertUserEntityToUserResponse(User employee);

    default Response convertToResponse(final Object data, final int returnCode, final String message, String... title) {
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
}
