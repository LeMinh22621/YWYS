package minh.lehong.yourwindowyoursoul.facade.Impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.RoomDto;
import minh.lehong.yourwindowyoursoul.dto.TaskManagerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskManagerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.FullTaskManagerResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.dto.payload.response.TaskManagerResponse;
import minh.lehong.yourwindowyoursoul.facade.TaskManagerFacade;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.model.entity.TaskManager;
import minh.lehong.yourwindowyoursoul.service.RoomService;
import minh.lehong.yourwindowyoursoul.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TaskManagerFacadeImpl implements TaskManagerFacade {
    @Autowired
    private CommonConverter commonConverter;
    @Autowired
    private RoomService roomService;
    @Autowired
    private TaskManagerService taskManagerService;


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
        taskManager = taskManagerService.save(taskManager);
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

        TaskManager taskManager = taskManagerService.findTaskManagerById(UUID.fromString(taskManagerId));
        TaskManagerDto taskManagerDto = commonConverter.convertTaskManagerEntityToTaskManagerDto(taskManager);
        taskManagerDto = commonConverter.convertTaskManagerRequestToTaskManagerDto(taskManagerDto, taskManagerRequest);
        // update date
        taskManagerDto.setUpdateDate(commonConverter.convertToG7(new Date()));
        taskManager = commonConverter.convertTaskManagerDtoToTaskManagerEntity(taskManager, taskManagerDto);

        try{
            taskManager = taskManagerService.save(taskManager);
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
            TaskManager taskManager = taskManagerService.findTaskManagerById(UUID.fromString(taskManagerId));
            taskManager.setIsDeleted(true);
            taskManager = taskManagerService.save(taskManager);

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

        TaskManager taskManager = taskManagerService.findTaskManagerById(UUID.fromString(taskManagerId));
        TaskManagerDto taskManagerDto = commonConverter.convertTaskManagerEntityToTaskManagerDto(taskManager);
        TaskManagerResponse taskManagerResponse = commonConverter.convertTaskManagerDtoTotaskManagerResponse(taskManagerDto);

        response = new Response(taskManagerResponse, true, "GET TaskManager By ID Success!", HttpStatus.OK.value());
        return response;
    }

    @Override
    public Response getTaskListManagerByRoomId(String roomId) {
        Response response;
        try
        {
            Room room = roomService.findRoomById(UUID.fromString(roomId));
            if(room != null)
            {
                List<TaskManager> taskManagers = taskManagerService.findAllByRoomAndIsDeleted(room);
                if(taskManagers == null || taskManagers.isEmpty())
                {
                    response = new Response(null, false, "Room Have No Task Manager", HttpStatus.NO_CONTENT.value());
                }
                else
                {
                    List<FullTaskManagerResponse> fullTaskManagerResponses = taskManagers
                            .stream()
                            .map(taskManager -> commonConverter.convertTaskManagerEntityToFullTaskManagerResponse(taskManager))
                            .collect(Collectors.toList());
                    response = new Response(fullTaskManagerResponses, true, "GET Task Manager By Room Id Success!", HttpStatus.OK.value());
                }
            }
            else {
                response = new Response(null, false, "Have no Room Id", HttpStatus.BAD_REQUEST.value());
            }
        }
        catch (Exception e)
        {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }
}
