package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import minh.lehong.yourwindowyoursoul.model.entity.Room;
import minh.lehong.yourwindowyoursoul.model.entity.TaskLevel;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    @JsonProperty("task_id")
    private String taskDtoId;

    @JsonProperty("task_content")
    private String taskContent;
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    @JsonProperty("is_done")
    private Boolean isDone;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("update_date")
    private Date updateDate;
    @JsonProperty("task_intend")
    private Long taskIntend;
    @JsonProperty("task_priority")
    private Long taskPriority;
    @JsonProperty("room")
    private Room room;
    @JsonProperty("task_level")
    private TaskLevel taskLevel;
}
