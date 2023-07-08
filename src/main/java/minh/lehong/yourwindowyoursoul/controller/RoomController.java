package minh.lehong.yourwindowyoursoul.controller;

import io.jsonwebtoken.ExpiredJwtException;
import minh.lehong.yourwindowyoursoul.dto.payload.request.*;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.facade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/room")
@CrossOrigin(origins = "*")
public class RoomController {
    @Autowired
    private TaskFacade taskFacade;
    @Autowired
    private TimerFacade timerFacade;
    @Autowired
    private LabelFacade labelFacade;
    @Autowired
    private ThemeFacade themeFacade;
    @Autowired
    private RoomFacade roomFacade;
    @Autowired
    private BackgroundFacade backgroundFacade;
    @Autowired
    private TaskManagerFacade taskManagerFacade;
    @Autowired
    private TaskLabelFacade taskLabelFacade;
    /***
     * Label
     */
    @PostMapping("create-label/{task_id}")
    public ResponseEntity<?> createLabel(@PathVariable("task_id") String task_id, @RequestBody LabelRequest labelRequest) throws ParseException {
        Response response = labelFacade.createLabel(task_id, labelRequest);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("update-label/{label_id}")
    public ResponseEntity<?> updateLabel(@PathVariable("label_id") String labelId, @RequestBody LabelRequest labelRequest) {
        Response response = labelFacade.updateLabel(labelId, labelRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("delete-label/{label_id}")
    public ResponseEntity<?> deleteLabel(@PathVariable("label_id") String labelId){
        Response response = labelFacade.deleteLabel(labelId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("label")
    public ResponseEntity<?> getLabel(@RequestParam("label_id") String labelId){
        Response response = labelFacade.getLabel(labelId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("labels")
    public ResponseEntity<?> getAllLabelsByRoomId(@RequestParam("room_id") String roomId){
        Response response = labelFacade.getAllLabelsByRoomId(roomId);
        return ResponseEntity.ok(response);
    }
    /**
     * TaskLabel
     */
    @PatchMapping("task-labels/{task_id}/{label_id}/{is_deleted}")
    public ResponseEntity<?> updateIsDeletedTaskLabel(@PathVariable("task_id") String taskId, @PathVariable("label_id") String labelId, @PathVariable("is_deleted") boolean isDeleted ){
        Response response = taskLabelFacade.deleteTaskLabelByLabelIdAndTaskId(labelId, taskId, isDeleted);
        return ResponseEntity.ok(response);
    }
    @GetMapping("task-labels")
    public ResponseEntity<?> getAllLabelsByTaskId(@RequestParam("task_id") String taskId){
        Response response = taskLabelFacade.getAllLabelByTaskId(taskId);
        return ResponseEntity.ok(response);
    }
    /***
     * Background
     */
    @GetMapping("/backgrounds")
    public ResponseEntity<?> getBackgroundByTheme(@RequestParam("theme_id") String themeId){
        Response response = backgroundFacade.getBackgroundListByThemeId(themeId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/background")
    public ResponseEntity<?> getBackgroundById(@RequestParam("background_id") String backgroundId){
        Response response = backgroundFacade.getBackgroundByBackgroundId(backgroundId);
        return ResponseEntity.ok(response);
    }
    /***
     * Room
     */
    @PatchMapping("update-room-apart/{room_id}")
    public ResponseEntity<?> updateRoomApart(@PathVariable("room_id") String roomId, @RequestBody RoomRequest updateRoomRequest) throws ParseException {
        Response response = roomFacade.updateRoomApart(roomId, updateRoomRequest);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/create-room")
    public ResponseEntity<?> createRoom(@RequestHeader("Authorization") String authHeader, @RequestBody RoomRequest roomRequest) throws ParseException {
        Response response = roomFacade.createRoom(authHeader, roomRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping("{userId}/my-rooms")
    public ResponseEntity<?> getMyRoomList(@PathVariable("userId") String userId) throws ExpiredJwtException
    {
        Response response = roomFacade.getMyRoomListByUserId(userId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/public-rooms")
    public ResponseEntity<?> getPublicRoomsOrderByMembers()
    {
        Response response = roomFacade.getPublicRoomsOrderByMembers();
        return ResponseEntity.ok(response);
    }
    @GetMapping()
    public ResponseEntity<?> getRoom(@RequestParam("room_id") String roomId){
        Response response = roomFacade.getRoomFromRoomId(roomId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("delete/{room_id}")
    public ResponseEntity<?> deleteRoomById(@PathVariable("room_id") String roomId){
        Response response = roomFacade.deleteRoomById(roomId);
        return ResponseEntity.ok(response);
    }
    /***
     * Motivational Quote
     */
    @GetMapping("shuffle-motivational-quote")
    public ResponseEntity<?> shuffleMotivationalQuote(){
        Response response = roomFacade.shuffleMotivationalQuote();
        return ResponseEntity.ok(response);
    }
    @GetMapping("shuffle-background-by-theme-id")
    public ResponseEntity<?> shuffleBackgroundByThemeId(@RequestParam("theme_id") String themeId){
        Response response = roomFacade.shuffleBackgroundByThemeId(themeId);
        return ResponseEntity.ok(response);
    }
    /***
     * Task
     */
    @PostMapping("create-task")
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest) throws ParseException {
        Response response = taskFacade.save(taskRequest);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("update-task/{task_id}")
    public ResponseEntity<?> updateTask(@PathVariable("task_id") String taskId, @RequestBody TaskRequest taskRequest) throws ParseException {
        Response response = taskFacade.updateTask(taskId, taskRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("delete-task/{task_id}")
    public ResponseEntity<?> deleteTask(@PathVariable("task_id") String taskId){
        Response response = taskFacade.deleteTask(taskId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("task")
    public ResponseEntity<?> getTaskById(@RequestParam("task_id") String taskId){
        Response response = taskFacade.findByTaskIdAndIsDeleted(taskId, false);

        return ResponseEntity.ok(response);
    }
    /***
     * Task Manager
     */
    @GetMapping("task-manager")
    public ResponseEntity<?> getTaskManagerById(@RequestParam("task_manager_id") String taskManagerId){
        Response response = taskManagerFacade.getTaskManagerById(taskManagerId);

        return ResponseEntity.ok(response);
    }
    @GetMapping("task-manager-list")
    public ResponseEntity<?> getTaskListManagerByRoomId(@RequestParam("room_id") String roomId){
        Response response = taskManagerFacade.getTaskListManagerByRoomId(roomId);

        return ResponseEntity.ok(response);
    }
    @PostMapping("create-task-manager")
    public ResponseEntity<?> createTaskManager(@RequestBody TaskManagerRequest taskManagerRequest) throws ParseException {
        Response response = taskManagerFacade.save(taskManagerRequest);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("update-task-manager/{task_manager_id}")
    public ResponseEntity<?> updateTaskManager(@PathVariable("task_manager_id") String taskManagerId, @RequestBody TaskManagerRequest taskManagerRequest) throws ParseException {
        Response response = taskManagerFacade.updateTaskManager(taskManagerId, taskManagerRequest);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("delete-task-manager/{task_manager_id}")
    public ResponseEntity<?> deleteTaskManager(@PathVariable("task_manager_id") String taskManagerId){
        Response response = taskManagerFacade.deleteTaskManager(taskManagerId);
        return ResponseEntity.ok(response);
    }
    /***
     * Timer
     */
    @PatchMapping("update-timer/{timer_id}")
    public ResponseEntity<?> updateTimer(@PathVariable("timer_id") String timerId, @RequestBody TimerRequest timerRequest) throws ParseException {
        Response response = timerFacade.updateTimer(timerId, timerRequest);
        return ResponseEntity.ok(response);
    }
    /***
     * Theme
     */
    @GetMapping("themes")
    public ResponseEntity<?> getAllThemes(){
        Response response = themeFacade.findAllThemes();
        return ResponseEntity.ok(response);
    }
}