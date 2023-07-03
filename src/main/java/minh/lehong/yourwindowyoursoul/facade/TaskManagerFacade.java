package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskManagerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;

import java.text.ParseException;

public interface TaskManagerFacade {
    Response save(TaskManagerRequest taskManagerRequest) throws ParseException;

    Response updateTaskManager(String taskManagerId, TaskManagerRequest taskManagerRequest) throws ParseException;

    Response deleteTaskManager(String taskManagerId);

    Response getTaskManagerById(String taskManagerId);

    Response getTaskListManagerByRoomId(String roomId);
}
