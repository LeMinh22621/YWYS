package minh.lehong.yourwindowyoursoul.facade.Impl;

import minh.lehong.yourwindowyoursoul.converter.CommonConverter;
import minh.lehong.yourwindowyoursoul.dto.payload.response.LabelResponse;
import minh.lehong.yourwindowyoursoul.dto.payload.response.Response;
import minh.lehong.yourwindowyoursoul.facade.TaskLabelFacade;
import minh.lehong.yourwindowyoursoul.model.entity.Label;
import minh.lehong.yourwindowyoursoul.model.entity.Task;
import minh.lehong.yourwindowyoursoul.model.entity.TaskLabel;
import minh.lehong.yourwindowyoursoul.service.LabelService;
import minh.lehong.yourwindowyoursoul.service.TaskLabelService;
import minh.lehong.yourwindowyoursoul.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TaskLabelFacadeImpl implements TaskLabelFacade {
    @Autowired
    private TaskLabelService taskLabelService;
    @Autowired
    private CommonConverter commonConverter;
    @Autowired
    private LabelService labelService;
    @Autowired
    private TaskService taskService;

    @Override
    public Response deleteTaskLabelByLabelIdAndTaskId(String labelId, String taskId, boolean isDeleted) {
        Response response;
        try
        {
            if(taskId != null && labelId != null)
            {
                Task task = taskService.findByTaskId(UUID.fromString(taskId));
                Label label = labelService.findLabelById(UUID.fromString(labelId));

                TaskLabel taskLabel = taskLabelService.findByTaskAndLabel(task, label);
                if(taskLabel != null)
                {
                    taskLabel.setIsDeleted(isDeleted);
                    taskLabel = taskLabelService.save(taskLabel);
                    if(taskLabel != null)
                        response = new Response(
                                commonConverter.convertLabelDtoToLabelResponse(commonConverter.convertLabelEntityToLabelDto(labelService.findLabelById(UUID.fromString(labelId)))),
                                true,
                                "Change isDeleted TaskLabel Success!",
                                HttpStatus.OK.value()
                        );
                    else
                        response = new Response(null, false, "Delete TaskLabel Fail!", HttpStatus.INTERNAL_SERVER_ERROR.value());
                }
                else
                {
                    TaskLabel newTaskLabel = new TaskLabel();
                    newTaskLabel.setLabel(label);
                    newTaskLabel.setTask(task);
                    newTaskLabel.setIsDeleted(false);
                    newTaskLabel.setUpdatedDate(commonConverter.convertToG7(new Date()));
                    newTaskLabel.setCreateDate(commonConverter.convertToG7(new Date()));

                    newTaskLabel = taskLabelService.save(newTaskLabel);
                    response = new Response(
                            commonConverter.convertLabelDtoToLabelResponse(commonConverter.convertLabelEntityToLabelDto(labelService.findLabelById(UUID.fromString(labelId)))),
                            true,
                            "Add Label To Task Success!",
                            HttpStatus.OK.value()
                    );
                }
            }
            else
                response = new Response(null, false, "Missing taskId or LabelId", HttpStatus.BAD_REQUEST.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }

    @Override
    public Response getAllLabelByTaskId(String taskId) {

        Response response;
        try
        {
            Task task = taskService.findByTaskId(UUID.fromString(taskId));
            List<TaskLabel> taskLabels = taskLabelService.findAllByTask(task);
            if(taskLabels != null && !taskLabels.isEmpty())
            {
                List<Label> labels = taskLabels
                        .stream()
                        .filter(taskLabel -> taskLabel.getIsDeleted() == false)
                        .map(taskLabel -> taskLabel.getLabel())
                        .collect(Collectors.toList());

                if(labels != null &&!labels.isEmpty())
                {
                    List<LabelResponse> labelResponses = labels
                            .stream()
                            .map(label -> commonConverter.convertLabelDtoToLabelResponse(commonConverter.convertLabelEntityToLabelDto(label)))
                            .collect(Collectors.toList());

                    response = new Response(labelResponses, true, "Get All Labels in Task Success!", HttpStatus.OK.value());
                }
                else
                    response = new Response(null, false, "Have no any labels 1!", HttpStatus.NO_CONTENT.value());
            }
            else
                response = new Response(null, false, "Have no any labels 2!", HttpStatus.NO_CONTENT.value());
        }
        catch (Exception e)
        {
            response = new Response(null, false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;
    }
}
