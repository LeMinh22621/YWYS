package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LabelResponse {
    @JsonProperty("label_id")
    private String labelId;
    @JsonProperty("room_id")
    private String roomId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("color")
    private String color;
    //Common
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("update_date")
    private Date updateDate;
}
