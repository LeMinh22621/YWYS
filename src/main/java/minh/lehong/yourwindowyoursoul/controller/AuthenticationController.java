package minh.lehong.yourwindowyoursoul.controller;

import lombok.RequiredArgsConstructor;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.AuthenticationResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.dto.payload.response.UserResponse;
import minh.lehong.yourwindowyoursoul.facade.UserFacade;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.SignatureException;
import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody SignupRequest request) throws Exception
    {
        Response response = userFacade.register(request);
        return  ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginRequest request) throws Exception
    {
        Response response = userFacade.login(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/logout")
    public ResponseEntity<Response> logout(@RequestHeader("Authorization") String tokenHeader) throws Exception
    {
        Response response = userFacade.logout(tokenHeader);
        return ResponseEntity.ok().header("Authorization", "Bearer " + ((AuthenticationResponse)response.getData()).getToken()).body(response);
    }
    @GetMapping("/verify-token")
    public ResponseEntity<?> checkExpiredToken(@RequestParam("token") String token) throws ParseException {
        Response response = userFacade.checkExpiredToken(token);
        return ResponseEntity.ok(response);
    }
}
