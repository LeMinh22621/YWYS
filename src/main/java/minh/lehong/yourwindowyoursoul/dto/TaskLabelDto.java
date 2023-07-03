package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskLabelDto implements Serializable {
    @JsonProperty("task_label_id")
    private String taskLabelDtoId;

    @JsonProperty("task")
    private TaskDto taskDto;

    @JsonProperty("label")
    private LabelDto labelDto;
}
