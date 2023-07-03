package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.TaskDto;
import minh.lehong.yourwindowyoursoul.dto.TaskManagerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Task;
import minh.lehong.yourwindowyoursoul.model.entity.TaskManager;
import minh.lehong.yourwindowyoursoul.repository.TaskRepository;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import minh.lehong.yourwindowyoursoul.service.LabelService;
import minh.lehong.yourwindowyoursoul.service.TaskManagerService;
import minh.lehong.yourwindowyoursoul.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired

    private TaskRepository taskRepository;

    @Override
    @CachePut(key = "#result.taskId", value = "task")
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Cacheable(key = "#uuid", value = "task")
    public Task findByTaskId(UUID uuid) {
        return taskRepository.findByTaskIdAndIsDeleted(uuid, false).orElseThrow(() -> new DBException("Have No Task Id" + uuid.toString()));
    }
}