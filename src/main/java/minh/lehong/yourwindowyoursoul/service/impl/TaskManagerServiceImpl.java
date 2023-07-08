package minh.lehong.yourwindowyoursoul.service.impl;

import minh.lehong.yourwindowyoursoul.constant.Constant;
import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.RoomDto;
import minh.lehong.yourwindowyoursoul.dto.TaskManagerDto;
import minh.lehong.yourwindowyoursoul.dto.payload.request.TaskManagerRequest;
import minh.lehong.yourwindowyoursoul.dto.payload.response.FullTaskManagerResponse;
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
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {
    @Autowired
    private TaskManagerRepository taskManagerRepository;
    @Override
    @Cacheable(value = "taskManager", key = "#taskManagerId")
    public TaskManager findTaskManagerById(UUID taskManagerId) {
        return taskManagerRepository.findTaskManagerByTaskManagerIdAndIsDeleted(taskManagerId, false)
                .orElseThrow(() -> new DBException("Find TaskManager Error!"));
    }

    @Override
    @CachePut(value = "taskManager", condition = "#result != null && #result.taskManagerId != null", key = "#result.taskManagerId")
    public TaskManager save(TaskManager taskManager) {
        return taskManagerRepository.save(taskManager);
    }

    @Override
    public List<TaskManager> findAllByRoomAndIsDeleted(Room room) {
        return taskManagerRepository.findAllByRoomAndIsDeleted(room, false);
    }
}
