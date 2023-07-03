package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.payload.request.RoomRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;

import java.text.ParseException;

public interface RoomFacade {
    Response getRoomFromRoomId(String roomId) throws IllegalArgumentException;

    Response createRoom(String authHeader, RoomRequest roomRequest) throws ParseException;

    Response getMyRooms(String authHeader);

    Response getPublicRoomsOrderByMembers();

    Response deleteRoomById(String roomId);

    Response updateRoomApart(String roomId, RoomRequest updateRoomRequest) throws ParseException;

    Response getMyRoomListByUserId(String userId);

    Response shuffleBackgroundByThemeId(String themeId);

    Response shuffleMotivationalQuote();
}
