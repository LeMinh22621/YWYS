package minh.lehong.yourwindowyoursoul.converter;

import minh.lehong.yourwindowyoursoul.dto.*;
import minh.lehong.yourwindowyoursoul.dto.payload.request.*;
import minh.lehong.yourwindowyoursoul.dto.payload.response.*;
import minh.lehong.yourwindowyoursoul.model.entity.*;

import java.text.ParseException;
import java.util.Date;

public interface CommonConverter {
    public UserResponse convertUserEntityToUserResponse(final User user) throws ParseException;

//    User convertUserRequestToUserEntity(final UserRequest userRequest);

//    User convertSignupRequestToUserEntity(final SignupRequest signupRequest);

    Response convertToResponse(final Object data, final int returnCode, final String message, String... title);

    Date convertStringToDate(String dateString);

    Date convertToG7(Date slowDate) throws ParseException;

    User convertOldUserToNewUser(User oldUser, User newUser);

    Label convertLabelDtoToLabelEntity(Label label, LabelDto labelDto);
    RoomDto convertRoomEntityToRoomDto(Room roomEntity);
    BackgroundDto convertBackgroundEntityToBackgroundDto(Background backgroundEntity);
    MotivationalQuoteDto convertMotivationalQuoteEntityToMotivationalQuoteDto(MotivationalQuote motivationalQuoteEntity);
    ThemeDto convertThemeEntityToThemeDto(Theme themeEntity);
    TimerDto convertTimerEntityToTimerDto(Timer timerEntity);
    UserDto convertUserEntityToUserDto(User userEntity);

    Task convertTaskDtoToTaskEntity(Task task, TaskDto taskDto);

    TaskDto convertTaskRequestToTaskDto(TaskDto taskDto, TaskRequest taskRequest) throws ParseException;

    TaskResponse convertTaskDtoToTaskResponse(TaskDto taskDto);

    Timer convertTimerDtoToTimerEntity(Timer Timer, TimerDto timerDto);

    LabelDto convertLabelEntityToLabelDto(Label label);

    TaskManagerDto convertTaskManagerEntityToTaskManagerDto(TaskManager taskManager);

    TaskManagerDto convertTaskManagerRequestToTaskManagerDto(TaskManagerDto taskManagerDto, TaskManagerRequest taskManagerRequest);

    TaskManager convertTaskManagerDtoToTaskManagerEntity(TaskManager taskManager, TaskManagerDto taskManagerDto);

    Room convertRoomDtoToRoomEntity(Room room, RoomDto roomDto);

    User convertUserDtoToUserEntity(User user, UserDto userDto);

    Theme convertThemeDtoToThemeEntity(Theme theme, ThemeDto themeDto);

    Background convertBackgroundDtoToBackGroundEntity(Background background, BackgroundDto backgroundDto);

    MotivationalQuote convertMotivationalQuoteDtoToMotivationalQuoteEntity(MotivationalQuote motivationalQuote, MotivationalQuoteDto motivationalQuoteDto);

    TaskDto convertTaskEntityToTaskDto(Task task);

    TimerDto convertTimerRequestToTimerDto(TimerDto timerDto, TimerRequest timerRequest);

    TaskManagerResponse convertTaskManagerDtoTotaskManagerResponse(TaskManagerDto taskManagerDto);

    LabelDto convertLabelRequestToLabelDto(LabelDto labelDto, LabelRequest labelRequest);

    LabelResponse convertLabelDtoToLabelResponse(LabelDto labelDto);

    UserDto convertSignupRequestToUserDto(UserDto userDto, SignupRequest request) throws ParseException;

    UserResponse convertUserDtoToUserResponse(UserDto convertUserEntityToUserDto) throws ParseException;

    RoomResponse convertRoomDtoToRoomResponse(RoomDto roomDto);

    BackgroundResponse convertBackgroundDtoToBackGroundResponse(BackgroundDto backgroundDto);

    RoomItemResponse convertRoomDtoToRoomItemResponse(RoomDto roomDto);

    RoomDto convertRoomRequestToRoomDto(RoomRequest roomRequest) throws ParseException;
}
