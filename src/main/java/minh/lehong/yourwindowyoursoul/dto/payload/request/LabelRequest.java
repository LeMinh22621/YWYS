package minh.lehong.yourwindowyoursoul.dto.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LabelRequest implements Serializable {
    @JsonProperty("room_id")
    private String roomId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("color")
    private String color;
}
