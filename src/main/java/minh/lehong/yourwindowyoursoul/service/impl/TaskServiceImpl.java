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

    @Autowired
    private LabelService labelService;
    @Autowired
    private TaskManagerService taskManagerService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CommonConverter commonConverter;

    @Override
    public Response save(TaskRequest taskRequest) throws ParseException {
        if(taskRequest == null)
            return null;
        TaskDto taskDto = commonConverter.convertTaskRequestToTaskDto(new TaskDto(), taskRequest);
        // set common
        taskDto.setCreateDate(commonConverter.convertToG7(new Date()));
        taskDto.setUpdateDate(commonConverter.convertToG7(new Date()));
        taskDto.setIsDeleted(false);
        // set taskManager to taskDto
        if(taskRequest.getTaskManagerId() != null)
        {
            TaskManager taskManager = taskManagerService.findTaskManagerById(UUID.fromString(taskRequest.getTaskManagerId()));
            taskDto.setTaskManagerDto(commonConverter.convertTaskManagerEntityToTaskManagerDto(taskManager));
        }

        Task task = commonConverter.convertTaskDtoToTaskEntity(new Task(), taskDto);
        task = taskRepository.save(task);

        Response response = new Response();
        response.setData(commonConverter.convertTaskDtoToTaskResponse(commonConverter.convertTaskEntityToTaskDto(task)));
        response.setMessage("create task success!");
        response.setReturnCode(HttpStatus.OK.value());
        response.setStatus(true);

        return response;
    }

    @Override
    public Response findByTaskIdAndIsDeleted(String taskId, boolean isDeleted) {
        Response response;
        Task task = taskRepository.findByTaskIdAndIsDeleted(UUID.fromString(taskId), false).orElseThrow(() -> new DBException("find task by Id error!"));
        if(task != null)
        {
            TaskDto taskDto = commonConverter.convertTaskEntityToTaskDto(task);
            response = new Response(commonConverter.convertTaskDtoToTaskResponse(taskDto), true, "Get task by id success!", HttpStatus.OK.value());
        }
        else{
            response = new Response(null, true, "Have no that task!", HttpStatus.BAD_REQUEST.value());
        }
        return response;
    }

    @Override
    public Response updateTask(String taskId, TaskRequest taskRequest) throws ParseException {
        Response response;
        Task task = taskRepository.findByTaskIdAndIsDeleted(UUID.fromString(taskId), false).orElseThrow(()-> new DBException("find task by Id error!"));
        TaskDto taskDto = commonConverter.convertTaskEntityToTaskDto(task);
        taskDto = commonConverter.convertTaskRequestToTaskDto(taskDto, taskRequest);
        taskDto.setUpdateDate(commonConverter.convertToG7(new Date()));
        task = commonConverter.convertTaskDtoToTaskEntity(task, taskDto);
        try
        {
            response = new Response();
            task = taskRepository.save(task);
            response.setStatus(true);
            response.setReturnCode(HttpStatus.OK.value());
            response.setMessage("Success update task!");
            response.setData(commonConverter.convertTaskDtoToTaskResponse(commonConverter.convertTaskEntityToTaskDto(task)));
        }
        catch (Exception e)
        {
            response = new Response(null, false, "Update Task Failed!", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    @Override
    public Response deleteTask(String taskId) {
        Response response = null;
        Task task = taskRepository.findByTaskIdAndIsDeleted(UUID.fromString(taskId), false).orElseThrow(()-> new DBException("have no that taskId!"));

        if(!Objects.isNull(task))
        {
            try
            {
                task.setIsDeleted(true);
                taskRepository.save(task);

                response = new Response();
                response.setStatus(true);
                response.setReturnCode(HttpStatus.OK.value());
                response.setMessage("Success delete task");
                response.setData(commonConverter.convertTaskDtoToTaskResponse(commonConverter.convertTaskEntityToTaskDto(task)));
            }
            catch (Exception exception)
            {
                response = new Response(null, false, "Delete error!", HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
        }
        else{
            response = new Response(null, false, "have no that taskId", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }
}

/***
 * Response response = null;
 * 		try {
 * 			SiteParameter siteParameter = siteParameterService.findById(id);
 * 			if (Objects.isNull(siteParameter)) {
 * 				return coreUtils.contentNotFound();
 *                        }
 * 			siteParameter.setIsDeleted(Constant.IS_DELETED);
 * 			siteParameter = siteParameterService.update(siteParameter);
 * 			return coreUtils.convertToResponse(siteParameter);* 		} catch (Exception e) {
 * 			logger.error(this.getClass().getName().concat(" {} " + e.getMessage()));
 * 			response = commonConverter.convertToResponse(null, Constant.RETURN_NG, e.getMessage());
 * 		}
 * 		return response;
 */