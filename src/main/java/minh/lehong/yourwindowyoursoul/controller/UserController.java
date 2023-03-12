package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.config.LocalVariable;
import minh.lehong.yourwindowyoursoul.dto.common.Response;
import minh.lehong.yourwindowyoursoul.facade.UserFacade;
import minh.lehong.yourwindowyoursoul.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private MessageSource message;

    @Autowired
    private LocalVariable localVariable;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody final LoginRequest request) {
        final Response response = userFacade.login(request);

        if (response != null && response.isStatus()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.setMessage(message.getMessage("bo.login.fail.message", null, localVariable.getLocale()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
