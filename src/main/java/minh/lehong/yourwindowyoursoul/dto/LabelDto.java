package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LabelDto implements Serializable {
    @JsonProperty("id")
    private String labelDtoId;
    @JsonProperty("name")
    private String labelName;
    @JsonProperty("color")
    private String color;
    @JsonProperty("room")
    private RoomDto roomDto;
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("update_date")
    private Date updateDate;
}
