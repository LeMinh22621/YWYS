package minh.lehong.yourwindowyoursoul.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class EPTokenGenerationModel implements Serializable {

	private static final long serialVersionUID = -4640230653539493827L;
	
	@JsonProperty("id")
	private String id;

	@JsonProperty("type")
	private String type;

	@JsonProperty("user_id")
	private String customerId;

	@JsonProperty("token")
	private String token;

	@JsonProperty("expires")
	private Integer expires;

}
