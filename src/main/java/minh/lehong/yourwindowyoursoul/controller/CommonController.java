package minh.lehong.yourwindowyoursoul.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class CommonController {

    @GetMapping("/home")
    public String home(){
        return "Home page";
    }
}
