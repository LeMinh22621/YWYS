package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskManagerRequest {
    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("task_manager_title")
    private String taskManagerTitle;
}
