//package minh.lehong.yourwindowyoursoul.controller;
//
//import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
//import minh.lehong.yourwindowyoursoul.dto.Response;
//import minh.lehong.yourwindowyoursoul.exceptions.ServiceException;
//import minh.lehong.yourwindowyoursoul.facade.UserFacade;
//import minh.lehong.yourwindowyoursoul.payload.request.SignupRequest;
//import minh.lehong.yourwindowyoursoul.payload.request.UserRequest;
//import minh.lehong.yourwindowyoursoul.payload.request.LoginRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.text.ParseException;
//
//@RestController
//@RequestMapping("api/v1/user")
//public class UserController {
//    @Autowired
//    private UserFacade userFacade;
//    @Autowired
//    CommonConverter commonConverter;
//
//    @PostMapping("/login")
//    public ResponseEntity<Response> login(@RequestBody final LoginRequest request) throws ServiceException, ParseException {
//
//        Response response = userFacade.login(request);
//
//        if (response != null && response.isStatus()) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//        else if(response == null)
//        {
//            response = new Response();
//            response.setMessage("Log-in failed because null response");
//        }
//        else if(!response.isStatus())
//        {
//            response.setMessage("Log-in failed because wrong username or password");
//            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//        }
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<Response> signup(@RequestBody final SignupRequest signupRequest) throws ServiceException{
//
//        Response response = userFacade.signup(signupRequest);
//
//        if (response != null && response.isStatus()) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//        else if(response == null)
//        {
//            response = new Response();
//            response.setMessage("Sign-up failed because null response");
//        }
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Response> update(@RequestBody final UserRequest userRequest){
//        Response response = userFacade.update(userRequest);
//
//        if (response != null && response.isStatus()) {
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        }
//        else if(response == null)
//        {
//            response = new Response();
//            response.setMessage("Update failed because null response");
//        }
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
