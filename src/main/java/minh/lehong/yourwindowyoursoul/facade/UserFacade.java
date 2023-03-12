package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.common.Response;
import minh.lehong.yourwindowyoursoul.dto.request.LoginRequest;

public interface UserFacade {
    /**
     * login
     *
     * @param request
     * @return Response
     */
    Response login(LoginRequest request);

    /**
     * request access token
     *
     * @param request
     * @return Response
     */
//    Response requestAccessToken(AuthenRequest request);
//
//    /**
//     * create new user
//     *
//     * @param requestData
//     * @return
//     */
//    Response createUser(UserRequest requestData);
//
//    /**
//     * update user
//     *
//     * @param requestData
//     * @param employeeId
//     * @return
//     */
//    Response updateUser(UserRequest requestData, String employeeId);

}
