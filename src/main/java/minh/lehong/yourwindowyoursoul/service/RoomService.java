package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.request.RoomRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import minh.lehong.yourwindowyoursoul.repository.RoomRepository;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomService {
    Room saveRoom(Room room);

    Room findRoomById(UUID roomId);

    Collection<UUID> findRoomIdByUserId(UUID userId);

    List<Room> findRoomsByIsPublicAndIsDeletedOrderByMembers(boolean b, boolean b1);

    List<Room> findRoomsByUserAndIsDeleted(User user, boolean b);
}
