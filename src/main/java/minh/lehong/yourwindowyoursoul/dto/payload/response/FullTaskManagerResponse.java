package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class FullTaskManagerResponse implements Serializable {
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

    @JsonProperty("roomId")
    private String roomId;

    @JsonProperty("task_list")
    private List<FullTaskResponse> taskResponseList;
}
