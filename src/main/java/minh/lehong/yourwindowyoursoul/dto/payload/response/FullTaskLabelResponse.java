package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FullTaskLabelResponse implements Serializable {
    @JsonProperty("task_label_id")
    private String taskLabelId;

    @JsonProperty("task_id")
    private String taskId;

    @JsonProperty("label")
    private LabelResponse labelResponse;
}
