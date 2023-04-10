package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CommonController {

    @Autowired
    RoomService roomService;

    @GetMapping("/home")
    public String home(){
        return UUID.randomUUID().toString();
    }

    @PostMapping("/create-room")
    public ResponseEntity<?> createRoom(@RequestHeader("Authorization") String authHeader)
    {
        Response response = roomService.createRoom(authHeader);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/my-rooms")
    public ResponseEntity<?> getMyRooms(@RequestHeader("Authorization") String authHeader)
    {
        Response response = roomService.getMyRooms(authHeader);
        return ResponseEntity.ok(response);
    }
}
