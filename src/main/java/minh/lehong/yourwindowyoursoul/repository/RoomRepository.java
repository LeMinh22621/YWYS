package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository  extends JpaRepository<Room, UUID> {
    Optional<Room> findRoomByRoomIdAndIsDeleted(UUID roomId, boolean isDeleted);

    @Query("SELECT r.roomId FROM Room r WHERE r.user.userId = ?1")
    Collection<UUID> findRoomIdByUserId(UUID userId);

    List<Room> findRoomsByUserAndIsDeleted(User user, boolean isDeleted);
}
