package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Task;
import minh.lehong.yourwindowyoursoul.repository.TaskRepository;

import java.text.ParseException;
import java.util.UUID;

public interface TaskService {

    Task save(Task task);

    Task findByTaskId(UUID fromString);
}
