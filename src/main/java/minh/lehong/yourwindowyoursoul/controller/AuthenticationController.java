package minh.lehong.yourwindowyoursoul.controller;

import lombok.RequiredArgsConstructor;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.AuthenticationResponse;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;

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

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) throws SignatureException, Exception
    {
        AuthenticationResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthenticationResponse> logout(@RequestHeader("Authorization") String tokenHeader) throws Exception
    {
        AuthenticationResponse response = userService.logout(tokenHeader);
        return ResponseEntity.ok().header("Authorization", "Bearer " + response.getToken()).body(response);
    }
}
