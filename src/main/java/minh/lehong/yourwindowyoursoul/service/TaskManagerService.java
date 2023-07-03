package minh.lehong.yourwindowyoursoul.service;

import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskManagerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.model.entity.TaskManager;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public interface TaskManagerService {
    TaskManager findTaskManagerById(UUID taskManagerId);

    TaskManager save(TaskManager taskManager);

    List<TaskManager> findAllByRoomAndIsDeleted(Room room);
}
