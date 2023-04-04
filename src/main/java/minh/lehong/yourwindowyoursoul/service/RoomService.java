package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.repository.RoomRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomService {
    Room saveRoom(Room room);

    Room findRoomById(UUID roomUuid);

    Response createRoom(String authHeader);
}
