package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.TaskLevel;
import minh.lehong.yourwindowyoursoul.repository.TaskLevelRepository;
import minh.lehong.yourwindowyoursoul.service.TaskLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskLevelImpl implements TaskLevelService {

    @Autowired
    private TaskLevelRepository taskLevelRepository;

    @Override
    public TaskLevel findTaskLevelById(UUID taskLevelUuid) {
        return taskLevelRepository.findById(taskLevelUuid)
                .orElseThrow(() -> new DBException("Find TaskLevel Error!"));
    }
}
