package minh.lehong.yourwindowyoursoul.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskManagerDto implements Serializable {
    @JsonProperty("task_manager_id")
    private String taskManagerId;

    @JsonProperty("task_manager_title")
    private String taskManagerTitle;

    @JsonProperty("create_date")
    private Date createDate;

    @JsonProperty("update_date")
    private Date updateDate;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    @JsonProperty("room")
    private RoomDto roomDto;
}
