package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.TaskDto;
import minh.lehong.yourwindowyoursoul.dto.TaskLevelDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.model.entity.Task;
import minh.lehong.yourwindowyoursoul.model.entity.TaskLevel;
import minh.lehong.yourwindowyoursoul.repository.TaskRepository;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import minh.lehong.yourwindowyoursoul.service.TaskLevelService;
import minh.lehong.yourwindowyoursoul.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskDecorator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskLevelService taskLevelService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CommonConverter commonConverter;

    private TaskDto newTaskDtoFromTaskRequest(TaskRequest taskRequest)
    {
        TaskDto taskDto = new TaskDto();
        TaskLevel taskLevel = taskLevelService.findTaskLevelById(UUID.fromString(taskRequest.getTaskLevelId()));
        Room room = roomService.findRoomById(UUID.fromString(taskRequest.getRoomId()));
        taskDto.setRoom(room);
        taskDto.setTaskLevel(taskLevel);

        return taskDto;
    }
    @Override
    public Response save(TaskRequest taskRequest) throws ParseException {
        if(taskRequest == null)
            return null;

        TaskDto taskDto = newTaskDtoFromTaskRequest(taskRequest);

        commonConverter.convertTaskRequestToTaskDto(taskRequest, taskDto);

        Task task = commonConverter.convertTaskDtoToTaskEntity(taskDto);
        taskRepository.save(task);

        Response response = new Response();
        response.setData(commonConverter.convertTaskDtoToTaskResponse(taskDto));
        response.setMessage("create task success!");
        response.setReturnCode(HttpStatus.OK.value());
        response.setTitle(HttpStatus.OK.name());
        response.setStatus(true);

        return response;
    }
}
