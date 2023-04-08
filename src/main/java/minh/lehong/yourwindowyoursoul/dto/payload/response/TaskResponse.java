package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import minh.lehong.yourwindowyoursoul.dto.TaskLevelDto;
import minh.lehong.yourwindowyoursoul.model.entity.TaskLevel;

import java.util.Date;

@Data
public class TaskResponse {
    @JsonProperty("task_id")
    private String taskId;

    @JsonProperty("task_content")
    private String taskContent;

    @JsonProperty("task_intend")
    private Long taskIntend;

    @JsonProperty("task_priority")
    private Long taskPriority;

    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("task_level")
    private TaskLevelDto taskLevelDto;

    @JsonProperty("is_done")
    private Boolean isDone;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    @JsonProperty("create_date")
    private Date createDate;

    @JsonProperty("update_date")
    private Date updateDate;
}
