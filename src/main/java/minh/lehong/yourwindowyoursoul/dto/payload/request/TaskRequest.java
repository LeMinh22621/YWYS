package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TaskRequest implements Serializable {
    @JsonProperty("is_done")
    private Boolean isDone;
    @JsonProperty("task_intend")
    private Long taskIntend;
    @JsonProperty("task_content")
    private String taskContent;
    @JsonProperty("task_start_date")
    private Date taskStartDate;
    @JsonProperty("task_start_time")
    private Long taskStartTime;
    @JsonProperty("task_manager_id")
    private String taskManagerId;
}
