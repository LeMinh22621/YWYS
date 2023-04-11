package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.dto.TimerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.service.BackgroundService;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import minh.lehong.yourwindowyoursoul.service.TaskService;
import minh.lehong.yourwindowyoursoul.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private BackgroundService backgroundService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TimerService timerService;

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

    @GetMapping("shuffle-motivational-quote")
    public ResponseEntity<?> shuffleMotivationalQuote(){
        Response response = roomService.shuffleMotivationalQuote();

        return ResponseEntity.ok(response);
    }
    @GetMapping("shuffle-background-by-theme-id")
    public ResponseEntity<?> shuffleBackgroundByThemeId(@RequestParam("theme_id") String themeId){
        Response response = roomService.shuffleBackgroundByThemeId(themeId);

        return ResponseEntity.ok(response);
    }

    @PostMapping("create-task")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) throws ParseException {
        Response response = taskService.save(taskRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("update-timer")
    public ResponseEntity<?> updateTimer(@RequestParam("timer_id") String timerId, @RequestBody TimerDto timerDto) throws ParseException {
        Response response = timerService.updateTimer(timerId, timerDto);
        return ResponseEntity.ok(response);
    }
}
