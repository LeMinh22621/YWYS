package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.Response;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;

public interface UserService {

    User findByEmail(String email);
    User save(User user);

    boolean existsUserByEmail(String email);
}
