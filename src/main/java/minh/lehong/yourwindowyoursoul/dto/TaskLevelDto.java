package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskLevelDto {
    @JsonProperty("task_level_id")
    private String taskLevelDtoId;
    @JsonProperty("task_level_name")
    private String taskLevelName;
}
