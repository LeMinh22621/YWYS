package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.model.entity.Label;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LabelRepository extends JpaRepository<Label, UUID> {
    Optional<Label> findLabelByLabelIdAndIsDeleted(UUID labelId, boolean isDeleted);

    List<Label> findAllByRoom(Room room);
}
