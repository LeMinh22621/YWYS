package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Task;
import minh.lehong.yourwindowyoursoul.repository.TaskRepository;

import java.text.ParseException;
import java.util.UUID;

public interface TaskService {
    Response save(TaskRequest task) throws ParseException;
    Response findByTaskIdAndIsDeleted(String id, boolean isDeleted);
    Response updateTask(String taskId, TaskRequest taskRequest) throws ParseException;
    Response deleteTask(String taskId);
}
