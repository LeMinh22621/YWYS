package minh.lehong.yourwindowyoursoul.controller;

import minh.lehong.yourwindowyoursoul.dto.TimerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.LabelRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskManagerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TimerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Background;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.model.entity.TaskManager;
import minh.lehong.yourwindowyoursoul.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/room")
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {
    @Autowired
    private BackgroundService backgroundService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskManagerService taskManagerService;
    @Autowired
    private TimerService timerService;
    @Autowired
    private LabelService labelService;
    /***
     * Label
     */
    @PostMapping("create-label")
    public ResponseEntity<?> createLabel(@RequestBody LabelRequest labelRequest) throws ParseException {
        Response response = labelService.createLabel(labelRequest);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("update-label/{label_id}")
    public ResponseEntity<?> updateLabel(@PathVariable("label_id") String labelId, @RequestBody LabelRequest labelRequest) throws ParseException {
        Response response = labelService.updateLabel(labelId, labelRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("delete-label/{label_id}")
    public ResponseEntity<?> deleteLabel(@PathVariable("label_id") String labelId){
        Response response = labelService.deleteLabel(labelId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("label")
    public ResponseEntity<?> getLabel(@RequestParam("label_id") String labelId){
        Response response = labelService.getLabel(labelId);
        return ResponseEntity.ok(response);
    }
    /***
     * Background
     */
    @GetMapping("/backgrounds")
    public ResponseEntity<?> getBackgroundByTheme(@RequestParam("theme_id") String themeId){
        Response response = backgroundService.getBackgroundListByThemeId(themeId);
        return ResponseEntity.ok(response);
    }
    /***
     * Room
     */
    @GetMapping()
    public ResponseEntity<?> getRoom(@RequestParam("room_id") String roomId){
        Response response = roomService.getRoomFromRoomId(roomId);

        return ResponseEntity.ok(response);
    }
    /***
     * Motivational Quote
     */
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
    /***
     * Task
     */
    @PostMapping("create-task")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) throws ParseException {
        Response response = taskService.save(taskRequest);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("update-task/{task_id}")
    public ResponseEntity<?> updateTask(@PathVariable("task_id") String taskId, @RequestBody TaskRequest taskRequest) throws ParseException {
        Response response = taskService.updateTask(taskId, taskRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("delete-task/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable("task_id") String taskId){
        Response response = taskService.deleteTask(taskId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("task")
    public ResponseEntity<?> getTaskById(@RequestParam("task_id") String taskId){
        Response response = taskService.findByTaskIdAndIsDeleted(taskId, false);

        return ResponseEntity.ok(response);
    }
    /***
     * Task Manager
     */
    @GetMapping("task-manager")
    public ResponseEntity<?> getTaskManagerById(@RequestParam("task_manager_id") String taskManagerId){
        Response response = taskManagerService.getTaskManagerById(taskManagerId);

        return ResponseEntity.ok(response);
    }
    @PostMapping("create-task-manager")
    public ResponseEntity<?> createTaskManager(@RequestBody TaskManagerRequest taskManagerRequest) throws ParseException {
        Response response = taskManagerService.save(taskManagerRequest);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("update-task-manager/{task_manager_id}")
    public ResponseEntity<?> updateTaskManager(@PathVariable("task_manager_id") String taskManagerId, @RequestBody TaskManagerRequest taskManagerRequest) throws ParseException {
        Response response = taskManagerService.updateTaskManager(taskManagerId, taskManagerRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("delete-task-manager/{task_manager_id}")
    public ResponseEntity<?> deleteTaskManager(@PathVariable("task_manager_id") String taskManagerId){
        Response response = taskManagerService.deleteTaskManager(taskManagerId);
        return ResponseEntity.ok(response);
    }
    /***
     * Timer
     */
    @PatchMapping("update-timer/{timer_id}")
    public ResponseEntity<?> updateTimer(@PathVariable("timer_id") String timerId, @RequestBody TimerRequest timerRequest) throws ParseException {
        Response response = timerService.updateTimer(timerId, timerRequest);
        return ResponseEntity.ok(response);
    }
}
