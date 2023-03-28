package minh.lehong.yourwindowyoursoul.controller;

import lombok.RequiredArgsConstructor;
import minh.lehong.yourwindowyoursoul.facade.UserFacade;
import minh.lehong.yourwindowyoursoul.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.payload.response.AuthenticationResponse;
import minh.lehong.yourwindowyoursoul.payload.response.UserResponse;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody SignupRequest request) throws Exception
    {
        AuthenticationResponse authenticationResponse =  userService.register(request);
        return  ResponseEntity.ok(authenticationResponse);
    }

//    @PostMapping("authenticate")
//    public ResponseEntity<UserResponse>
}
