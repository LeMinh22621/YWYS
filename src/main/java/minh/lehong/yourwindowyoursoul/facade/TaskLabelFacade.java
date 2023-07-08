package minh.lehong.yourwindowyoursoul.facade;

import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;

public interface TaskLabelFacade {
    Response deleteTaskLabelByLabelIdAndTaskId(String labelId, String taskId, boolean isDeleted);

    Response getAllLabelByTaskId(String taskId);
}
