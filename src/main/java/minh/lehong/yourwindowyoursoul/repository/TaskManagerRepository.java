package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.model.entity.TaskManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskManagerRepository extends JpaRepository<TaskManager, UUID> {
    Optional<TaskManager> findTaskManagerByTaskManagerIdAndIsDeleted(UUID taskManagerId, boolean isDeleted);
}
