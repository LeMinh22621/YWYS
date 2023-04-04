package minh.lehong.yourwindowyoursoul.converter;

import minh.lehong.yourwindowyoursoul.dto.*;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.*;
import minh.lehong.yourwindowyoursoul.dto.payload.request.SignupRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.request.UserRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.UserResponse;

import java.text.ParseException;
import java.util.Date;

public interface CommonConverter {
    public UserResponse convertUserEntityToUserData(final User user) throws ParseException;

    User convertUserRequestToUserEntity(final UserRequest userRequest);

    User convertSignupRequestToUserEntity(final SignupRequest signupRequest);

    Response convertToResponse(final Object data, final int returnCode, final String message, String... title);

    Date convertStringToDate(String dateString);

    Date convertToG7(Date slowDate) throws ParseException;

    User convertOldUserToNewUser(User oldUser, User newUser);

    RoomDto convertRoomEntityToRoomDto(Room roomEntity);
    BackgroundDto convertBackgroundEntityToBackgroundDto(Background backgroundEntity);
    MotivationalQuoteDto convertMotivationalQuoteEntityToMotivationalQuoteDto(MotivationalQuote motivationalQuoteEntity);
    ThemeDto convertThemeEntityToThemeDto(Theme themeEntity);
    TimerDto convertTimerEntityToTimerDto(Timer timerEntity);
    UserDto convertUserEntityToUserDto(User userEntity);
}
