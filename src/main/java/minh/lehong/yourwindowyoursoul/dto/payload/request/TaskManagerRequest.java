package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TaskManagerRequest implements Serializable {
    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("task_manager_title")
    private String taskManagerTitle;
}
