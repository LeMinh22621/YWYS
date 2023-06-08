package minh.lehong.yourwindowyoursoul.controller;

import lombok.RequiredArgsConstructor;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.AuthenticationResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.dto.payload.response.UserResponse;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.SignatureException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.19:3000"})
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody SignupRequest request) throws Exception
    {
        Response response = userService.register(request);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginRequest request) throws Exception
    {
        Response response = userService.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@RequestHeader("Authorization") String tokenHeader) throws Exception
    {
        Response response = userService.logout(tokenHeader);
        return ResponseEntity.ok().header("Authorization", "Bearer " + ((AuthenticationResponse)response.getData()).getToken()).body(response);
    }
    @GetMapping("/verify-token")
    public ResponseEntity<?> checkExpiredToken(@RequestParam("token") String token) throws ParseException {
        Response response = userService.checkExpiredToken(token);
        return ResponseEntity.ok(response);
    }
}
