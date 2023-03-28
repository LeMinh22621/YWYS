package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.Response;
import minh.lehong.yourwindowyoursoul.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;

import java.text.ParseException;

public interface UserFacade {
    Response login(LoginRequest request) throws ParseException;
    Response signup(SignupRequest signupRequest);

    Response update(UserRequest userRequest);

}
