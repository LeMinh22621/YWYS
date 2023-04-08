package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskRequest {
    @JsonProperty("task_intend")
    private Long taskIntend;

    @JsonProperty("task_content")
    private String taskContent;
    @JsonProperty("task_priority")
    private Long taskPriority;
    @JsonProperty("room_id")
    private String roomId;
    @JsonProperty("task_level_id")
    private String taskLevelId;
}
