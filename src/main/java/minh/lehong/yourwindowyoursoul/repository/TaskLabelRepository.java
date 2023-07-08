package minh.lehong.yourwindowyoursoul.repository;

import minh.lehong.yourwindowyoursoul.model.entity.Label;
import minh.lehong.yourwindowyoursoul.model.entity.Task;
import minh.lehong.yourwindowyoursoul.model.entity.TaskLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskLabelRepository extends JpaRepository<TaskLabel, UUID> {
    Optional<TaskLabel> findByTaskAndLabel(Task task, Label label);

    List<TaskLabel> findAllByTaskAndIsDeleted(Task task, boolean isDeleted);

    List<TaskLabel> findAllByLabelAndIsDeleted(Label label, boolean isDeleted);
}
