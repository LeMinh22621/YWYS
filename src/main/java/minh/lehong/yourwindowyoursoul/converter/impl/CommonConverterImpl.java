package minh.lehong.yourwindowyoursoul.converter.impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.*;
import minh.lehong.yourwindowyoursoul.dto.payload.request.*;
import minh.lehong.yourwindowyoursoul.dto.payload.response.*;
import minh.lehong.yourwindowyoursoul.model.entity.*;
import minh.lehong.yourwindowyoursoul.service.LabelService;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import minh.lehong.yourwindowyoursoul.service.TaskManagerService;
import minh.lehong.yourwindowyoursoul.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Component
public class CommonConverterImpl implements CommonConverter {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskManagerService taskManagerService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private RoomService roomService;

    @Override
    public UserResponse convertUserEntityToUserResponse(final User user) throws ParseException {
        UserResponse userResponse = null;
        if(user != null){
            // set user entity to user response
            userResponse = new UserResponse();
            userResponse.setId(user.getUserId());
            userResponse.setEmail(user.getEmail());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setDateCreated(user.getCreateDate());
            userResponse.setUrlAvatar(user.getUrlAvatar());
            userResponse.setDeleted(user.getIsDeleted());
            userResponse.setDateCreated(this.convertToG7(user.getCreateDate()));
            userResponse.setDateModified(this.convertToG7(user.getUpdatedDate()));
        }
        return userResponse;
    }
//    @Override
//    public User convertUserRequestToUserEntity(final UserRequest userRequest) {
//        User user = null;
//        if(userRequest != null){
//            user = new User();
//            user.setEmail(userRequest.getEmail());
//            user.setFirstName(userRequest.getFirstName());
//            user.setLastName(userRequest.getLastName());
//            user.setUrlAvatar(userRequest.getUrlAvatar());
//            user.setPassword(userRequest.getPassword());
//            user.setCreateDate(userRequest.getDateCreated());
//            user.setUpdatedDate(userRequest.getDateModified());
//        }
//        return user;
//    }
//    public User convertSignupRequestToUserEntity(final SignupRequest signupRequest)
//    {
//        User user = null;
//        if(signupRequest != null){
//            user = new User();
//            user.setEmail(signupRequest.getEmail());
//            user.setFirstName(signupRequest.getFirstName());
//            user.setLastName(signupRequest.getLastName());
//            user.setUrlAvatar(null);
//            user.setPassword(signupRequest.getPassword());
//            user.setCreateDate(new Date());
//            user.setUpdatedDate(new Date());
//        }
//        return user;
//    }

    @Override
    public Response convertToResponse(final Object data, final int returnCode, final String message, String... title) {
        final Response response = new Response();
        response.setData(data);
        response.setMessage(message);
        response.setStatus(returnCode == Constant.RETURN_OK);
        response.setReturnCode(returnCode);
        return response;
    }

    @Override
    public Date convertStringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDate date = LocalDate.parse(dateString, formatter);

        return new Date(date.toEpochDay());
    }

    @Override
    public Date convertToG7(Date date) throws ParseException {
        date.setTime(date.getTime() + 7*60*60*1000);
        return date;
    }

    @Override
    public User convertOldUserToNewUser(User oldUser, User newUser) {
        oldUser.setUpdatedDate(new Date());
        oldUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));
        oldUser.setUrlAvatar(newUser.getUrlAvatar());
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());

        return oldUser;
    }

    @Override
    public RoomDto convertRoomEntityToRoomDto(Room roomEntity) {
        RoomDto roomDto = null;
        if(roomEntity != null)
        {
            roomDto = new RoomDto();
            roomDto.setRoomId(roomEntity.getRoomId().toString());
            roomDto.setTitle(roomEntity.getTitle());
            roomDto.setDescription(roomEntity.getDescription());
            roomDto.setMembers(roomEntity.getMembers());
            roomDto.setIsPublic(roomEntity.getIsPublic());
            roomDto.setBackgroundDto(this.convertBackgroundEntityToBackgroundDto(roomEntity.getBackground()));
            roomDto.setTimerDto(this.convertTimerEntityToTimerDto(roomEntity.getTimer()));
            roomDto.setMotivationalQuoteDto(this.convertMotivationalQuoteEntityToMotivationalQuoteDto(roomEntity.getMotivationalQuote()));
            roomDto.setUserDto(this.convertUserEntityToUserDto(roomEntity.getUser()));
            roomDto.setIsDeleted(roomEntity.getIsDeleted());
            roomDto.setCreateDate(roomEntity.getCreateDate());
            roomDto.setUpdateDate(roomEntity.getUpdatedDate());
        }
        return roomDto;
    }

    @Override
    public BackgroundDto convertBackgroundEntityToBackgroundDto(Background backgroundEntity) {
        BackgroundDto backgroundDto = null;
        if(backgroundEntity != null)
        {
            backgroundDto = new BackgroundDto();
            backgroundDto.setThemeDto(this.convertThemeEntityToThemeDto(backgroundEntity.getTheme()));
            backgroundDto.setBackgroundLink(backgroundEntity.getLink());
            backgroundDto.setImageLink(backgroundEntity.getImageLink());
            backgroundDto.setBackgroundId(backgroundEntity.getBackgroundId().toString());
        }
        return backgroundDto;
    }

    @Override
    public MotivationalQuoteDto convertMotivationalQuoteEntityToMotivationalQuoteDto(MotivationalQuote motivationalQuoteEntity) {
        MotivationalQuoteDto motivationalQuoteDto = null;
        if(motivationalQuoteEntity != null)
        {
            motivationalQuoteDto = new MotivationalQuoteDto();
            motivationalQuoteDto.setMotivationalQuoteId(motivationalQuoteEntity.getMotivationalQuoteId().toString());
            motivationalQuoteDto.setContent(motivationalQuoteEntity.getContent());
            motivationalQuoteDto.setAuthor(motivationalQuoteEntity.getAuthor());
        }
        return motivationalQuoteDto;
    }

    @Override
    public ThemeDto convertThemeEntityToThemeDto(Theme themeEntity) {
        ThemeDto themeDto = null;
        if(themeEntity != null)
        {
            themeDto = new ThemeDto();
            themeDto.setThemeId(themeEntity.getThemeId().toString());
            themeDto.setThemeName(themeEntity.getThemeName());
            themeDto.setImageLink(themeEntity.getImageLink());
        }
        return themeDto;
    }

    @Override
    public TimerDto convertTimerEntityToTimerDto(Timer timerEntity) {
        TimerDto timerDto = null;

        if(timerEntity != null)
        {
            timerDto = new TimerDto();
            timerDto.setTimerId(timerEntity.getTimerId().toString());
            timerDto.setShortBreak(timerEntity.getShortBreak());
            timerDto.setLongBreak(timerEntity.getLongBreak());
            timerDto.setPomodoroTime(timerEntity.getPomodoroTime());
            timerDto.setIsDeleted(timerEntity.getIsDeleted());
            timerDto.setCreateDate(timerEntity.getCreateDate());
            timerDto.setUpdateDate(timerEntity.getUpdatedDate());
        }
        return timerDto;
    }

    @Override
    public UserDto convertUserEntityToUserDto(User userEntity) {
        UserDto userDto = null;
        if(userEntity != null)
        {
            userDto = new UserDto();
            userDto.setUserId(userEntity.getUserId());
            userDto.setRole(userEntity.getRole());
            userDto.setUrlAvatar(userEntity.getUrlAvatar());
            userDto.setLastName(userEntity.getLastName());
            userDto.setFirstName(userEntity.getFirstName());
            userDto.setEmail(userEntity.getEmail());
            userDto.setPassword(userEntity.getPassword());
            userDto.setDateCreated(userEntity.getCreateDate());
            userDto.setDateModified(userEntity.getUpdatedDate());
        }
        return userDto;
    }

    @Override
    public LabelDto convertLabelEntityToLabelDto(Label labelEntity)
    {
        LabelDto labelDto = null;
        if(labelEntity != null)
        {
            labelDto = new LabelDto();
            labelDto.setLabelDtoId(labelEntity.getLabelId().toString());
            labelDto.setLabelName(labelEntity.getName());
            labelDto.setColor(labelEntity.getColor());
            labelDto.setIsDeleted(labelEntity.getIsDeleted());
            labelDto.setUpdateDate(labelEntity.getUpdatedDate());
            labelDto.setCreateDate(labelEntity.getCreateDate());
            labelDto.setRoomDto(this.convertRoomEntityToRoomDto(labelEntity.getRoom()));
        }
        return labelDto;
    }
    @Override
    public Label convertLabelDtoToLabelEntity(Label label, LabelDto labelDto)
    {
        if(labelDto != null)
        {
            if(labelDto.getLabelDtoId() != null)
                label.setLabelId(UUID.fromString(labelDto.getLabelDtoId()));
            label.setName(labelDto.getLabelName());
            label.setColor(labelDto.getColor());
            label.setIsDeleted(labelDto.getIsDeleted());
            label.setCreateDate(labelDto.getCreateDate());
            label.setUpdatedDate(labelDto.getUpdateDate());
            label.setRoom(this.convertRoomDtoToRoomEntity(new Room(), labelDto.getRoomDto()));
        }
        return label;
    }

    @Override
    public Task convertTaskDtoToTaskEntity(Task task, TaskDto taskDto) {
        if(taskDto != null)
        {
            if(taskDto.getTaskDtoId() != null)
                task.setTaskId(UUID.fromString(taskDto.getTaskDtoId()));
            task.setTaskContent(taskDto.getTaskContent());
            task.setTaskIntend(taskDto.getTaskIntend());
            task.setTaskStartDate(taskDto.getTaskStartDate());
            task.setTaskStartTime(taskDto.getTaskStartTime());
            task.setIsDone(taskDto.getIsDone());
            task.setCreateDate(taskDto.getCreateDate());
            task.setUpdatedDate(taskDto.getUpdateDate());
            task.setIsDeleted(taskDto.getIsDeleted());
            task.setTaskManager(this.convertTaskManagerDtoToTaskManagerEntity(new TaskManager(), taskDto.getTaskManagerDto()));
        }
        return task;
    }

    @Override
    public TaskDto convertTaskRequestToTaskDto(TaskDto taskDto, TaskRequest taskRequest) throws ParseException {
        if(taskRequest != null)
        {
            if(taskRequest.getTaskContent() != null)
                taskDto.setTaskContent(taskRequest.getTaskContent());
            if(taskRequest.getTaskIntend() != null)
                taskDto.setTaskIntend(taskRequest.getTaskIntend());
            if(taskRequest.getTaskStartDate() != null)
                taskDto.setTaskStartDate(taskRequest.getTaskStartDate());
            if(taskRequest.getTaskStartTime() != null)
                taskDto.setTaskStartTime(taskRequest.getTaskStartTime());
            if(taskRequest.getIsDone() != null)
                taskDto.setIsDone(taskRequest.getIsDone());
            // miss taskManager
            return taskDto;
        }
        return taskDto;
    }

    @Override
    public TaskResponse convertTaskDtoToTaskResponse(TaskDto taskDto) {
        TaskResponse taskResponse = null;
        if(taskDto != null)
        {
            taskResponse = new TaskResponse();
            taskResponse.setTaskId(taskDto.getTaskDtoId());
            taskResponse.setTaskContent(taskDto.getTaskContent());
            taskResponse.setTaskIntend(taskDto.getTaskIntend());
            taskResponse.setTaskStartDate(taskDto.getTaskStartDate());
            taskResponse.setTaskStartTime(taskDto.getTaskStartTime());
            taskResponse.setIsDone(taskDto.getIsDone());
            taskResponse.setIsDeleted(taskDto.getIsDeleted());
            taskResponse.setUpdateDate(taskDto.getUpdateDate());
            taskResponse.setCreateDate(taskDto.getCreateDate());
            taskResponse.setTaskManagerId(taskDto.getTaskManagerDto().getTaskManagerId());
        }
        return  taskResponse;
    }

    @Override
    public Timer convertTimerDtoToTimerEntity(Timer timer, TimerDto timerDto) {
        if(timerDto != null)
        {
            if(timerDto.getTimerId() != null)
                timer.setTimerId(UUID.fromString(timerDto.getTimerId()));
            timer.setPomodoroTime(timerDto.getPomodoroTime());
            timer.setLongBreak(timerDto.getLongBreak());
            timer.setShortBreak(timerDto.getShortBreak());
            timer.setIsDeleted(timerDto.getIsDeleted());
            timer.setCreateDate(timerDto.getCreateDate());
            timer.setUpdatedDate(timerDto.getUpdateDate());
        }
        return timer;
    }

    @Override
    public TaskManagerDto convertTaskManagerEntityToTaskManagerDto(TaskManager taskManager) {
        TaskManagerDto taskManagerDto = null;
        if(taskManager != null)
        {
            taskManagerDto = new TaskManagerDto();
            taskManagerDto.setTaskManagerId(taskManager.getTaskManagerId().toString());
            taskManagerDto.setTaskManagerTitle(taskManager.getTaskManagerTitle());
            taskManagerDto.setCreateDate(taskManager.getCreateDate());
            taskManagerDto.setUpdateDate(taskManager.getUpdatedDate());
            taskManagerDto.setIsDeleted(taskManager.getIsDeleted());
            taskManagerDto.setRoomDto(this.convertRoomEntityToRoomDto(taskManager.getRoom()));
        }
        return taskManagerDto;
    }

    @Override
    public TaskManagerDto convertTaskManagerRequestToTaskManagerDto(TaskManagerDto taskManagerDto, TaskManagerRequest taskManagerRequest) {
        if(taskManagerRequest != null)
        {
            if(taskManagerRequest.getTaskManagerTitle() != null)
                taskManagerDto.setTaskManagerTitle(taskManagerRequest.getTaskManagerTitle());
        }
        return taskManagerDto;
    }

    @Override
    public TaskManager convertTaskManagerDtoToTaskManagerEntity(TaskManager taskManager, TaskManagerDto taskManagerDto) {
        if(taskManagerDto != null)
        {
            if(taskManagerDto.getTaskManagerId() != null)
                taskManager.setTaskManagerId(UUID.fromString(taskManagerDto.getTaskManagerId()));
            taskManager.setTaskManagerTitle(taskManagerDto.getTaskManagerTitle());
            taskManager.setCreateDate(taskManagerDto.getCreateDate());
            taskManager.setUpdatedDate(taskManagerDto.getUpdateDate());
            taskManager.setIsDeleted(taskManagerDto.getIsDeleted());
            taskManager.setRoom(this.convertRoomDtoToRoomEntity(new Room(), taskManagerDto.getRoomDto()));
        }
        return taskManager;
    }
    @Override
    public User convertUserDtoToUserEntity(User user, UserDto userDto) {
        if(userDto != null)
        {
            if(userDto.getUserId() != null)
                user.setUserId(userDto.getUserId());
            user.setRole(userDto.getRole());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setUrlAvatar(userDto.getUrlAvatar());
            user.setUpdatedDate(userDto.getDateModified());
            user.setCreateDate(userDto.getDateCreated());
            user.setIsDeleted(userDto.isDeleted());
        }
        return user;
    }
    @Override
    public Theme convertThemeDtoToThemeEntity(Theme theme, ThemeDto themeDto) {
        if(themeDto != null)
        {
            if(themeDto.getThemeId() != null)
                theme.setThemeId(UUID.fromString(themeDto.getThemeId()));
            theme.setThemeName(themeDto.getThemeName());
            theme.setImageLink(themeDto.getImageLink());
        }
        return theme;
    }
    @Override
    public Background convertBackgroundDtoToBackGroundEntity(Background background, BackgroundDto backgroundDto) {
        if(backgroundDto != null)
        {
            if(backgroundDto.getBackgroundId() != null)
                background.setBackgroundId(UUID.fromString(backgroundDto.getBackgroundId()));
            background.setIsDeleted(false);
            background.setCreateDate(new Date());
            background.setUpdatedDate(new Date());
            background.setLink(backgroundDto.getBackgroundLink());
            background.setImageLink(backgroundDto.getImageLink());
            background.setTheme(this.convertThemeDtoToThemeEntity(new Theme(), backgroundDto.getThemeDto()));
        }
        return background;
    }

    @Override
    public MotivationalQuote convertMotivationalQuoteDtoToMotivationalQuoteEntity(MotivationalQuote motivationalQuote, MotivationalQuoteDto motivationalQuoteDto) {
        if(motivationalQuoteDto != null)
        {
            if(motivationalQuoteDto.getMotivationalQuoteId() != null)
                motivationalQuote.setMotivationalQuoteId(UUID.fromString(motivationalQuoteDto.getMotivationalQuoteId()));
            motivationalQuote.setAuthor(motivationalQuote.getAuthor());
            motivationalQuote.setContent(motivationalQuoteDto.getContent());
        }
        return motivationalQuote;
    }

    @Override
    public TaskDto convertTaskEntityToTaskDto(Task task) {
        TaskDto taskDto = null;
        if(task != null)
        {
            taskDto = new TaskDto();
            taskDto.setTaskDtoId(task.getTaskId().toString());
            taskDto.setTaskContent(task.getTaskContent());
            taskDto.setTaskIntend(task.getTaskIntend());
            taskDto.setTaskStartDate(task.getTaskStartDate());
            taskDto.setTaskStartTime(task.getTaskStartTime());
            taskDto.setIsDone(task.getIsDone());
            taskDto.setCreateDate(task.getCreateDate());
            taskDto.setUpdateDate(task.getUpdatedDate());
            taskDto.setIsDeleted(task.getIsDeleted());
            taskDto.setTaskManagerDto(this.convertTaskManagerEntityToTaskManagerDto(task.getTaskManager()));
        }
        return taskDto;
    }

    @Override
    public TimerDto convertTimerRequestToTimerDto(TimerDto timerDto, TimerRequest timerRequest) {
        if(timerRequest != null)
        {
            if(timerRequest.getLongBreak() != null)
                timerDto.setLongBreak(timerRequest.getLongBreak());
            if(timerRequest.getPomodoroTime() != null)
                timerDto.setPomodoroTime(timerRequest.getPomodoroTime());
            if(timerRequest.getShortBreak() != null)
                timerDto.setShortBreak(timerRequest.getShortBreak());
        }
        return timerDto;
    }

    @Override
    public TaskManagerResponse convertTaskManagerDtoTotaskManagerResponse(TaskManagerDto taskManagerDto) {
        TaskManagerResponse taskManagerResponse = null;
        if(taskManagerDto != null)
        {
            taskManagerResponse = new TaskManagerResponse();
            taskManagerResponse.setTaskManagerId(taskManagerDto.getTaskManagerId());
            taskManagerResponse.setTaskManagerTitle(taskManagerDto.getTaskManagerTitle());
            taskManagerResponse.setRoomId(taskManagerDto.getRoomDto().getRoomId());
            taskManagerResponse.setUpdateDate(taskManagerDto.getUpdateDate());
            taskManagerResponse.setCreateDate(taskManagerDto.getCreateDate());
            taskManagerResponse.setIsDeleted(taskManagerDto.getIsDeleted());
        }
        return taskManagerResponse;
    }

    @Override
    public LabelDto convertLabelRequestToLabelDto(LabelDto labelDto, LabelRequest labelRequest) {
        if(labelRequest != null)
        {
            if(labelRequest.getColor() != null)
                labelDto.setColor(labelRequest.getColor());
            if(labelRequest.getName() != null)
                labelDto.setLabelName(labelRequest.getName());
        }
        return labelDto;
    }

    @Override
    public LabelResponse convertLabelDtoToLabelResponse(LabelDto labelDto) {
        LabelResponse labelResponse = null;
        if(labelDto != null)
        {
            labelResponse = new LabelResponse();
            labelResponse.setLabelId(labelDto.getLabelDtoId());
            labelResponse.setName(labelDto.getLabelName());
            labelResponse.setColor(labelDto.getColor());
            labelResponse.setIsDeleted(labelDto.getIsDeleted());
            labelResponse.setCreateDate(labelDto.getCreateDate());
            labelResponse.setUpdateDate(labelDto.getUpdateDate());
            labelResponse.setRoomId(labelDto.getRoomDto().getRoomId());
        }
        return labelResponse;
    }

    @Override
    public UserDto convertSignupRequestToUserDto(UserDto userDto, SignupRequest request) throws ParseException {
        if(request != null)
        {
            userDto.setEmail(request.getEmail());
            userDto.setPassword(request.getPassword());
            userDto.setRole(Role.USER);
            userDto.setFirstName(request.getFirstName());
            userDto.setLastName(request.getLastName());
            userDto.setUrlAvatar("https://lhmfrontend.s3.amazonaws.com/default-avatar.jpg");
            userDto.setDeleted(false);
            userDto.setDateCreated(this.convertToG7(new Date()));
            userDto.setDateModified(userDto.getDateCreated());
            userDto.setDeleted(false);
        }
        return userDto;
    }

    @Override
    public UserResponse convertUserDtoToUserResponse(UserDto userDto) throws ParseException {
        UserResponse userResponse = null;
        if(userDto != null){
            userResponse = new UserResponse();

            userResponse.setId(userDto.getUserId());
            userResponse.setEmail(userDto.getEmail());
            userResponse.setFirstName(userDto.getFirstName());
            userResponse.setLastName(userDto.getLastName());
            userResponse.setDateCreated(userDto.getDateCreated());
            userResponse.setUrlAvatar(userDto.getUrlAvatar());
            userResponse.setDeleted(userDto.isDeleted());
            userResponse.setDateCreated(this.convertToG7(userDto.getDateCreated()));
            userResponse.setDateModified(this.convertToG7(userDto.getDateModified()));
        }
        return userResponse;
    }

    @Override
    public RoomResponse convertRoomDtoToRoomResponse(RoomDto roomDto) {
        RoomResponse response = null;
        if(roomDto != null)
        {
            response = new RoomResponse();
            response.setRoomId(roomDto.getRoomId());
            response.setMembers(roomDto.getMembers());
            response.setDescription(roomDto.getDescription());
            response.setIsPublic(roomDto.getIsPublic());
            response.setTitle(roomDto.getTitle());
            response.setBackgroundId(roomDto.getBackgroundDto().getBackgroundId());
            response.setTimerId(roomDto.getTimerDto().getTimerId());
            response.setUserId(roomDto.getUserDto().getUserId().toString());
            response.setMotivationalQuoteId(roomDto.getMotivationalQuoteDto().getMotivationalQuoteId());
            response.setUpdateDate(roomDto.getUpdateDate());
            response.setCreateDate(roomDto.getCreateDate());
            response.setIsDeleted(roomDto.getIsDeleted());
        }
        return response;
    }

    @Override
    public BackgroundResponse convertBackgroundDtoToBackGroundResponse(BackgroundDto backgroundDto) {
        BackgroundResponse backgroundResponse = null;

        if(backgroundDto != null)
        {
            backgroundResponse = new BackgroundResponse();
            backgroundResponse.setBackgroundId(backgroundDto.getBackgroundId());
            backgroundResponse.setBackgroundLink(backgroundDto.getBackgroundLink());
            backgroundResponse.setImageLink(backgroundDto.getImageLink());
            backgroundResponse.setThemeId(backgroundDto.getThemeDto().getThemeId());
        }

        return backgroundResponse;
    }

    @Override
    public RoomItemResponse convertRoomDtoToRoomItemResponse(RoomDto roomDto) {
        RoomItemResponse response = null;
        if(roomDto != null)
        {
            response = new RoomItemResponse();
            response.setRoomId(roomDto.getRoomId());
            response.setMembers(roomDto.getMembers());
            response.setDescription(roomDto.getDescription());
            response.setTitle(roomDto.getTitle());
            response.setIsPublic(roomDto.getIsPublic());
            response.setBackgroundResponse(this.convertBackgroundDtoToBackGroundResponse(roomDto.getBackgroundDto()));
            response.setTimerId(roomDto.getTimerDto().getTimerId());
            response.setUserId(roomDto.getUserDto().getUserId().toString());
            response.setMotivationalQuoteId(roomDto.getMotivationalQuoteDto().getMotivationalQuoteId());
            response.setUpdateDate(roomDto.getUpdateDate());
            response.setCreateDate(roomDto.getCreateDate());
            response.setIsDeleted(roomDto.getIsDeleted());
        }
        return response;
    }

    @Override
    public RoomDto convertRoomRequestToRoomDto(RoomRequest roomRequest) throws ParseException {
        RoomDto roomDto = null;
        if(roomRequest != null)
        {
            roomDto = new RoomDto();
            roomDto.setTitle(roomRequest.getTitle());
            roomDto.setDescription(roomRequest.getDescription());
            roomDto.setIsPublic(roomRequest.getIsPublic());
        }
        return roomDto;
    }


    @Override
    public Room convertRoomDtoToRoomEntity(Room room, RoomDto roomDto) {
        if(roomDto != null)
        {
            if(roomDto.getRoomId() != null)
                room.setRoomId(UUID.fromString(roomDto.getRoomId()));
            room.setTitle(roomDto.getTitle());
            room.setDescription(roomDto.getDescription());
            room.setMembers(roomDto.getMembers());
            room.setIsPublic(roomDto.getIsPublic());
            if(roomDto.getUserDto() != null)
                room.setUser(this.convertUserDtoToUserEntity(new User(), roomDto.getUserDto()));
            if(roomDto.getTimerDto() != null)
                room.setTimer(this.convertTimerDtoToTimerEntity(new Timer(), roomDto.getTimerDto()));
            if(roomDto.getBackgroundDto() != null)
                room.setBackground(this.convertBackgroundDtoToBackGroundEntity(new Background(), roomDto.getBackgroundDto()));
            if(roomDto.getMotivationalQuoteDto() != null)
                room.setMotivationalQuote(this.convertMotivationalQuoteDtoToMotivationalQuoteEntity(new MotivationalQuote(), roomDto.getMotivationalQuoteDto()));
            room.setCreateDate(roomDto.getCreateDate());
            room.setUpdatedDate(roomDto.getUpdateDate());
            room.setIsDeleted(roomDto.getIsDeleted());
        }
        return room;
    }
}
