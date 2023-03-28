//package minh.lehong.yourwindowyoursoul.controller;
//
//import minh.lehong.yourwindowyoursoul.payload.request.LoginRequest;
//import minh.lehong.yourwindowyoursoul.payload.response.LoginResponse;
//import minh.lehong.yourwindowyoursoul.payload.response.TokenResponse;
//import minh.lehong.yourwindowyoursoul.security.jwt.JwtTokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/api/v1")
//public class CommonController {
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @PostMapping("/login")
//    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
//    {
//        // Authenticate email and password
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getEmail(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        // If exception is not occur, login request is valid
//        // Set authentication information to Security Context
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // return jwt to client
//        String jwtToken = jwtTokenProvider.generateToken((UserDetailsJWT) authentication.getPrincipal());
//        TokenResponse tokenResponse = new TokenResponse();
//        tokenResponse.setAccessToken(jwtToken);
//        tokenResponse.setExpiredTime(jwtTokenProvider.getExpiredTime(jwtToken));
//
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setTokenResponse(tokenResponse);
//        loginResponse.setUserResponse(null);
//
//        return  loginResponse;
//    }
//
//    @GetMapping("/random")
//    public String randomPath()
//    {
//        return "Only Valid JWT can be seen!";
//    }
//}
