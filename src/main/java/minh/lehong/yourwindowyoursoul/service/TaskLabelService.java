package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.model.entity.Label;
import minh.lehong.yourwindowyoursoul.model.entity.Task;
import minh.lehong.yourwindowyoursoul.model.entity.TaskLabel;

import java.util.List;

public interface TaskLabelService {
    TaskLabel save(TaskLabel taskLabel);

    TaskLabel findByTaskAndLabel(Task task, Label label);

    List<TaskLabel> findAllByLabel(Label labelId);

    List<TaskLabel> findAllByTask(Task task);
}
