package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.Response;
import minh.lehong.yourwindowyoursoul.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;

public interface UserFacade {
    Response login(LoginRequest request);
    Response signup(SignupRequest signupRequest);
}
