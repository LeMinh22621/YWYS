package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class FullTaskResponse implements Serializable {
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
    @JsonProperty("label_list")
    private List<FullTaskLabelResponse> fullTaskLabelResponses;
}
