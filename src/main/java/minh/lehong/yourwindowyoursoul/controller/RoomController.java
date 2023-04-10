package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.service.BackgroundService;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import minh.lehong.yourwindowyoursoul.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private BackgroundService backgroundService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private TaskService taskService;
    @GetMapping("/backgrounds")
    public ResponseEntity<?> getBackgroundByTheme(@RequestParam("theme_id") String themeId){
        Response response = backgroundService.getBackgroundListByThemeId(themeId);
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    public ResponseEntity<?> getRoom(@RequestParam("room_id") String roomId){
        Response response = roomService.getRoomFromRoomId(roomId);

        return ResponseEntity.ok(response);
    }
    @PostMapping("create-task")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) throws ParseException {
        Response response = taskService.save(taskRequest);
        return ResponseEntity.ok(response);
    }
}
