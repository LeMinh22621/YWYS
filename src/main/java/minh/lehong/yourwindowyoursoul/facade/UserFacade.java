package minh.lehong.yourwindowyoursoul.facade;

import io.jsonwebtoken.ExpiredJwtException;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;

import java.text.ParseException;

public interface UserFacade {
    Response register(SignupRequest request) throws DBException, Exception;

    Response login(LoginRequest request) throws Exception;

    Response logout(String tokenHeader) throws ParseException, ExpiredJwtException, Exception;

    Response checkExpiredToken(String token) throws ParseException;

    Response findAllByIsDeletedAndRole(Role user);

    Response deleteUserByUserId(String userId);

    Response updateUser(String userId, UserRequest userRequest);
}
