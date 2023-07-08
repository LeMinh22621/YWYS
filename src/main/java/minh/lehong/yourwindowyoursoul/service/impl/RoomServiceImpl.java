package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.converter.impl.CommonConverterImpl;
import minh.lehong.yourwindowyoursoul.dto.RoomDto;
import minh.lehong.yourwindowyoursoul.dto.TimerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.RoomRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.dto.payload.response.RoomItemResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.RoomResponse;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.*;
import minh.lehong.yourwindowyoursoul.model.entity.Timer;
import minh.lehong.yourwindowyoursoul.repository.RoomRepository;
import minh.lehong.yourwindowyoursoul.service.*;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CacheManager cacheManager;

    @Override
    @CachePut(value = "room", condition = "#result != null && #result.roomId != null", key = "#result.roomId")
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    @Cacheable(key = "#roomId", value = "room")
    public Room findRoomById(UUID roomId) {
        return roomRepository.findRoomByRoomIdAndIsDeleted(roomId, false)
                .orElseThrow(() -> new DBException("GET Room by roomId error!"));
    }

    @Override
    public Collection<UUID> findRoomIdByUserId(UUID userId) {
        return roomRepository.findRoomIdByUserId(userId);
    }

    @Override
    public List<Room> findRoomsByIsPublicAndIsDeletedOrderByMembers(boolean isPublic, boolean isDeleted) {
        return roomRepository.findRoomsByIsPublicAndIsDeletedOrderByMembers(isPublic, isDeleted);
    }

    private boolean updatePublicRooms (Room roomUpdated){
        try
        {
            List<Room> publicPopularRooms = cacheManager.getCache("rooms").get("publicPopularRooms", List.class);
            publicPopularRooms = publicPopularRooms
                    .stream()
                    .filter(room -> room.getRoomId().equals(roomUpdated.getRoomId()))
                    .map(room -> roomUpdated)
                    .collect(Collectors.toList());
            cacheManager.getCache("rooms").put("publicPopularRooms", publicPopularRooms);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Room> findRoomsByUserAndIsDeleted(User user, boolean isDeleted) {
        return roomRepository.findRoomsByUserAndIsDeleted(user, isDeleted);
    }
}
