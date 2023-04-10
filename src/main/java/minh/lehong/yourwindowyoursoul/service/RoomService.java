package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.repository.RoomRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomService {
    Room saveRoom(Room room);

    Response getRoomFromRoomId(String roomId);
    Room findRoomById(UUID roomId);

    Response createRoom(String authHeader);

    Response getMyRooms(String authHeader);
}
