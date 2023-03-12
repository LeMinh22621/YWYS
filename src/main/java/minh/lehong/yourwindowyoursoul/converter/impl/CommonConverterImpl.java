package minh.lehong.yourwindowyoursoul.converter.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.response.UserResponse;
import minh.lehong.yourwindowyoursoul.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommonConverterImpl implements CommonConverter {

    @Override
    public UserResponse convertUserEntityToUserResponse(User user) {
        if (user != null) {
            final UserResponse userResponse = new UserResponse();
            userResponse.setPassword(user.getPassword());
            userResponse.setDateCreated(user.getCreateDate());
            userResponse.setDateModified(user.getUpdatedDate());
            userResponse.setDisabled(user.isDisabled());
            userResponse.setEmail(user.getEmail());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setId(user.getUserId());
            userResponse.setUrlAvatar(user.getUrlAvatar());
            return userResponse;
        }
        return null;
    }
}
