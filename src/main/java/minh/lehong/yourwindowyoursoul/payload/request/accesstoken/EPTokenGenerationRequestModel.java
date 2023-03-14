package minh.lehong.yourwindowyoursoul.payload.request.accesstoken;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class EPTokenGenerationRequestModel implements Serializable {

	private static final long serialVersionUID = 975317107064436183L;
	
	@JsonProperty("data")
	private EPTokenGenerationUserModel data;

	public EPTokenGenerationRequestModel() {
		super();
	}

}
