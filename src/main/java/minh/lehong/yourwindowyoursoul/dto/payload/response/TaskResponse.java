package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import minh.lehong.yourwindowyoursoul.dto.LabelDto;
import minh.lehong.yourwindowyoursoul.model.entity.TaskManager;

import java.util.Date;

@Data
public class TaskResponse {
    @JsonProperty("task_id")
    private String taskId;

    @JsonProperty("task_content")
    private String taskContent;

    @JsonProperty("task_intend")
    private Long taskIntend;
    @JsonProperty("task_start_date")
    private Date taskStartDate;
    @JsonProperty("task_start_time")
    private Long taskStartTime;
    @JsonProperty("task_manager_id")
    private String taskManagerId;
    @JsonProperty("is_done")
    private Boolean isDone;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    @JsonProperty("create_date")
    private Date createDate;

    @JsonProperty("update_date")
    private Date updateDate;
}
