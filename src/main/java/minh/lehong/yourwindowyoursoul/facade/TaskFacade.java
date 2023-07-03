package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;

import java.text.ParseException;

public interface TaskFacade {
    Response save(TaskRequest taskRequest) throws ParseException;

    Response findByTaskIdAndIsDeleted(String taskId, boolean isDeleted);

    Response updateTask(String taskId, TaskRequest taskRequest) throws ParseException;

    Response deleteTask(String taskId);

}
