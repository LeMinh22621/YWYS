package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.dto.payload.request.RoomRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")//{"http://localhost:3000", "http://192.168.1.19:3000"})
public class CommonController {

    @Autowired
    RoomService roomService;

    @GetMapping("/home")
    public String home(){
        return UUID.randomUUID().toString();
    }

    @GetMapping("/my-rooms")
    public ResponseEntity<?> getMyRooms(@RequestHeader("Authorization") String authHeader)
    {
        Response response = roomService.getMyRooms(authHeader);
        return ResponseEntity.ok(response);
    }
}
