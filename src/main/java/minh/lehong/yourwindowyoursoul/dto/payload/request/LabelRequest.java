package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LabelRequest {
    @JsonProperty("room_id")
    private String roomId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("color")
    private String color;
}
