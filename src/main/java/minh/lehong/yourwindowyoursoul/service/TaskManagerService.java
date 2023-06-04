package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskManagerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.TaskManager;

import java.text.ParseException;
import java.util.UUID;

public interface TaskManagerService {
    TaskManager findTaskManagerById(UUID taskManagerId);

    Response save(TaskManagerRequest taskManagerRequest) throws ParseException;

    Response updateTaskManager(String taskManagerId, TaskManagerRequest taskManagerRequest) throws ParseException;

    Response deleteTaskManager(String taskManagerId);

    Response getTaskManagerById(String taskManagerId);
}
