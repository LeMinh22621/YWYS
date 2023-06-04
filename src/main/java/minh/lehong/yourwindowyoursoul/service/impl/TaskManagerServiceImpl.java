package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.RoomDto;
import minh.lehong.yourwindowyoursoul.dto.TaskManagerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskManagerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.dto.payload.response.TaskManagerResponse;
import minh.lehong.yourwindowyoursoul.exceptions.DBException;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.model.entity.TaskManager;
import minh.lehong.yourwindowyoursoul.repository.TaskManagerRepository;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import minh.lehong.yourwindowyoursoul.service.TaskManagerService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {
    @Autowired
    private CommonConverter commonConverter;
    @Autowired
    private RoomService roomService;
    @Autowired
    private TaskManagerRepository taskManagerRepository;
    @Override
    public TaskManager findTaskManagerById(UUID taskManagerId) {
        return taskManagerRepository.findTaskManagerByTaskManagerIdAndIsDeleted(taskManagerId, false)
                .orElseThrow(() -> new DBException("Find TaskManager Error!"));
    }

    @Override
    public Response save(TaskManagerRequest taskManagerRequest) throws ParseException {
        Response response = new Response();
        TaskManagerDto taskManagerDto = commonConverter.convertTaskManagerRequestToTaskManagerDto(new TaskManagerDto(), taskManagerRequest);
        taskManagerDto.setCreateDate(commonConverter.convertToG7(new Date()));
        taskManagerDto.setUpdateDate(commonConverter.convertToG7(new Date()));
        taskManagerDto.setIsDeleted(false);
        // set roomDto for taskManagerDto
        if(!taskManagerRequest.getRoomId().isEmpty())
        {
            Room room = roomService.findRoomById(UUID.fromString(taskManagerRequest.getRoomId()));
            RoomDto roomDto = commonConverter.convertRoomEntityToRoomDto(room);
            taskManagerDto.setRoomDto(roomDto);
        }
        TaskManager taskManager = commonConverter.convertTaskManagerDtoToTaskManagerEntity(new TaskManager(), taskManagerDto);
        taskManager = taskManagerRepository.save(taskManager);
        if(taskManager != null)
        {
            response.setMessage(Constant.SUCCESS);
            response.setData(commonConverter.convertTaskManagerDtoTotaskManagerResponse(commonConverter.convertTaskManagerEntityToTaskManagerDto(taskManager)));
            response.setStatus(true);
            response.setReturnCode(HttpStatus.OK.value());
        }
        return response;
    }

    @Override
    public Response updateTaskManager(String taskManagerId, TaskManagerRequest taskManagerRequest) throws ParseException {
        Response response;

        TaskManager taskManager = this.findTaskManagerById(UUID.fromString(taskManagerId));
        TaskManagerDto taskManagerDto = commonConverter.convertTaskManagerEntityToTaskManagerDto(taskManager);
        taskManagerDto = commonConverter.convertTaskManagerRequestToTaskManagerDto(taskManagerDto, taskManagerRequest);
        // update date
        taskManagerDto.setUpdateDate(commonConverter.convertToG7(new Date()));
        taskManager = commonConverter.convertTaskManagerDtoToTaskManagerEntity(taskManager, taskManagerDto);

        try{
            taskManager = taskManagerRepository.save(taskManager);
            response = new Response(commonConverter.convertTaskManagerDtoTotaskManagerResponse(commonConverter.convertTaskManagerEntityToTaskManagerDto(taskManager)),
                    true,
                    "update Task Manager Success", HttpStatus.OK.value());
        }
        catch (Exception ex)
        {
            response = new Response(null,
                    false,
                    "update fail!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response deleteTaskManager(String taskManagerId) {
        Response response;
        try{
            TaskManager taskManager = this.findTaskManagerById(UUID.fromString(taskManagerId));
            taskManager.setIsDeleted(true);
            taskManager = taskManagerRepository.save(taskManager);

            TaskManagerResponse taskManagerResponse = commonConverter.convertTaskManagerDtoTotaskManagerResponse(commonConverter.convertTaskManagerEntityToTaskManagerDto(taskManager));
            response = new Response(taskManagerResponse, true, "delete task manager success!", HttpStatus.OK.value());
        }
        catch (Exception ex)
        {
            response = new Response(null, false, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }

    @Override
    public Response getTaskManagerById(String taskManagerId) {
        Response response;

        TaskManager taskManager = this.findTaskManagerById(UUID.fromString(taskManagerId));
        TaskManagerDto taskManagerDto = commonConverter.convertTaskManagerEntityToTaskManagerDto(taskManager);
        TaskManagerResponse taskManagerResponse = commonConverter.convertTaskManagerDtoTotaskManagerResponse(taskManagerDto);

        response = new Response(taskManagerResponse, true, "GET TaskManager By ID Success!", HttpStatus.OK.value());
        return response;
    }
}
