package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.model.entity.TaskLevel;

import java.util.UUID;

public interface TaskLevelService {
    TaskLevel findTaskLevelById(UUID taskLevelUuid);
}
