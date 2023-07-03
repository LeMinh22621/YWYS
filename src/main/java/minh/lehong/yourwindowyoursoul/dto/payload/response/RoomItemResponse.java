package minh.lehong.yourwindowyoursoul.dto.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RoomItemResponse implements Serializable {
    @JsonProperty("room_id")
    private String roomId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("members")
    private int members;
    @JsonProperty("is_public")
    private Boolean isPublic;
    @JsonProperty("background")
    private BackgroundResponse backgroundResponse;
    @JsonProperty("timer_id")
    private String timerId;
    @JsonProperty("motivational_quote_id")
    private String motivationalQuoteId;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    @JsonProperty("create_date")
    private Date createDate;
    @JsonProperty("update_date")
    private Date updateDate;
}
