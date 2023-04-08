package minh.lehong.yourwindowyoursoul.converter.impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.constant.enums.Role;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.*;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.dto.payload.response.TaskResponse;
import minh.lehong.yourwindowyoursoul.model.entity.*;
import minh.lehong.yourwindowyoursoul.dto.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.UserResponse;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import minh.lehong.yourwindowyoursoul.service.TaskLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;

@Component
public class CommonConverterImpl implements CommonConverter {
    @Override
    public UserResponse convertUserEntityToUserData(final User user) throws ParseException {
        UserResponse userResponse = null;
        if(user != null){
            // convert to right format
            user.setCreateDate(this.convertToG7(user.getCreateDate()));
            user.setUpdatedDate(this.convertToG7(user.getUpdatedDate()));

            // set user entity to user response
            userResponse = new UserResponse();

            userResponse.setId(user.getUserId());
            userResponse.setEmail(user.getEmail());
            userResponse.setPassword(user.getPassword());
            userResponse.setDisabled(!user.isEnabled());
            userResponse.setFirstName(user.getFirstName());
            userResponse.setLastName(user.getLastName());
            userResponse.setDateCreated(user.getCreateDate());
            userResponse.setUrlAvatar(user.getUrlAvatar());
            userResponse.setDateModified(user.getUpdatedDate());
        }
        return userResponse;
    }
    @Override
    public User convertUserRequestToUserEntity(final UserRequest userRequest) {
        User user = null;
        if(userRequest != null){
            user = new User();
            user.setEmail(userRequest.getEmail());
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setUrlAvatar(userRequest.getUrlAvatar());
            user.setPassword(userRequest.getPassword());
            user.setCreateDate(userRequest.getDateCreated());
            user.setUpdatedDate(userRequest.getDateModified());
        }
        return user;
    }
    public User convertSignupRequestToUserEntity(final SignupRequest signupRequest)
    {
        User user = null;
        if(signupRequest != null){
            user = new User();
            user.setEmail(signupRequest.getEmail());
            user.setFirstName(signupRequest.getFirstName());
            user.setLastName(signupRequest.getLastName());
            user.setUrlAvatar(null);
            user.setPassword(signupRequest.getPassword());
            user.setCreateDate(new Date());
            user.setUpdatedDate(new Date());
        }
        return user;
    }

    @Override
    public Response convertToResponse(final Object data, final int returnCode, final String message, String... title) {
        final Response response = new Response();
        response.setData(data);
        response.setMessage(message);
        response.setStatus(returnCode == Constant.RETURN_OK);
        response.setReturnCode(returnCode);
        if (Objects.nonNull(title) && title.length > 0) {
            response.setTitle(title[0]);
        }
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
            roomDto.setBackgroundDto(this.convertBackgroundEntityToBackgroundDto(roomEntity.getBackground()));
            roomDto.setTimerDto(this.convertTimerEntityToTimerDto(roomEntity.getTimer()));
            roomDto.setMotivationalQuoteDto(this.convertMotivationalQuoteEntityToMotivationalQuoteDto(roomEntity.getMotivationalQuote()));
            roomDto.setUserDto(this.convertUserEntityToUserDto(roomEntity.getUser()));
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
        }
        return timerDto;
    }

    @Override
    public UserDto convertUserEntityToUserDto(User userEntity) {
        UserDto userDto = null;
        if(userEntity != null)
        {
            userDto = new UserDto();
            userDto.setUserId(userEntity.getUserId().toString());
            userDto.setRole(userEntity.getRole());
            userDto.setAvatarUrl(userEntity.getUrlAvatar());
            userDto.setLastName(userEntity.getLastName());
            userDto.setFirstName(userEntity.getFirstName());
            userDto.setEmail(userEntity.getEmail());
        }
        return userDto;
    }

    private TaskLevelDto convertTaskLevelEntityToTaskLevelDto(TaskLevel taskLevelEntity)
    {
        TaskLevelDto taskLevelDto = null;
        if(taskLevelEntity != null)
        {
            taskLevelDto = new TaskLevelDto();
            taskLevelDto.setTaskLevelDtoId(taskLevelEntity.getTaskLevelId().toString());
            taskLevelDto.setTaskLevelName(taskLevelEntity.getName());
        }
        return taskLevelDto;
    }
    private TaskLevel convertTaskLevelDtoToTaskLevelEntity(TaskLevelDto taskLevelDto)
    {
        TaskLevel taskLevelEntity = null;
        if(taskLevelDto != null)
        {
            taskLevelEntity = new TaskLevel();
            taskLevelEntity.setTaskLevelId(UUID.fromString(taskLevelDto.getTaskLevelDtoId()));
            taskLevelEntity.setName(taskLevelDto.getTaskLevelName());
        }
        return taskLevelEntity;
    }

    @Override
    public Task convertTaskDtoToTaskEntity(TaskDto taskDto) {
        Task task = null;
        if(taskDto != null)
        {
            task = new Task();
            task.setTaskContent(taskDto.getTaskContent());
            task.setTaskIntend(taskDto.getTaskIntend());
            task.setTaskPriority(task.getTaskPriority());
            task.setTaskLevel(taskDto.getTaskLevel());
            task.setRoom(taskDto.getRoom());
            task.setIsDone(taskDto.getIsDone());
            task.setCreateDate(taskDto.getCreateDate());
            task.setUpdatedDate(taskDto.getUpdateDate());
            task.setIsDeleted(taskDto.getIsDeleted());
        }
        return task;
    }

    @Override
    public void convertTaskRequestToTaskDto(TaskRequest taskRequest, TaskDto taskDto) throws ParseException {
        if(taskRequest == null || taskDto == null)
            return;
        taskDto.setTaskDtoId(UUID.randomUUID().toString());
        taskDto.setTaskContent(taskRequest.getTaskContent());
        taskDto.setTaskIntend(taskRequest.getTaskIntend());
        taskDto.setTaskPriority(taskRequest.getTaskPriority());
        taskDto.setIsDeleted(false);
        taskDto.setIsDone(false);
        taskDto.setCreateDate(this.convertToG7(new Date()));
        taskDto.setUpdateDate(this.convertToG7(new Date()));
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
            taskResponse.setTaskPriority(taskDto.getTaskPriority());
            taskResponse.setRoomId(taskDto.getRoom().getRoomId().toString());
            taskResponse.setTaskLevelDto(this.convertTaskLevelEntityToTaskLevelDto(taskDto.getTaskLevel()));
            taskResponse.setIsDone(taskDto.getIsDone());
            taskResponse.setIsDeleted(taskDto.getIsDeleted());
            taskResponse.setUpdateDate(taskDto.getUpdateDate());
            taskResponse.setCreateDate(taskDto.getCreateDate());
        }
        return  taskResponse;
    }
}
