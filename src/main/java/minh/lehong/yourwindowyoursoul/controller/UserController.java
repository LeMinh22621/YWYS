package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.Response;
import minh.lehong.yourwindowyoursoul.exceptions.ServiceException;
import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.payload.request.LoginRequest;
import minh.lehong.yourwindowyoursoul.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    CommonConverter commonConverter;

    @PostMapping("/login")
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> login(@RequestBody final LoginRequest request) {

        Response response = userService.login(request);

        if (response != null && response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else if(response == null)
        {
            response = new Response();
            response.setMessage("Log-in failed because null response");
        }
        else if(!response.isStatus())
        {
            response.setMessage("Log-in failed because wrong username or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/signup")
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Response> signup(@RequestBody final UserRequest userRequest) {

        Response response = userService.signup(userRequest);

        if (response != null && response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else if(response == null)
        {
            response = new Response();
            response.setMessage("Sign-up failed because null response");
        }
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
