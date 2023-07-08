package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Label;
import minh.lehong.yourwindowyoursoul.model.entity.Task;
import minh.lehong.yourwindowyoursoul.model.entity.TaskLabel;
import minh.lehong.yourwindowyoursoul.repository.TaskLabelRepository;
import minh.lehong.yourwindowyoursoul.service.TaskLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLabelServiceImpl implements TaskLabelService {
    @Autowired
    private TaskLabelRepository taskLabelRepository;
    @Override
    public TaskLabel save(TaskLabel taskLabel){
        return taskLabelRepository.save(taskLabel);
    }

    @Override
    public TaskLabel findByTaskAndLabel(Task task, Label label) {
        return taskLabelRepository.findByTaskAndLabel(task, label).orElse(null);
    }

    @Override
    public List<TaskLabel> findAllByLabel(Label label) {
        return taskLabelRepository.findAllByLabelAndIsDeleted(label, false);
    }

    @Override
    public List<TaskLabel> findAllByTask(Task task) {
        return taskLabelRepository.findAllByTaskAndIsDeleted(task, false);
    }
}
