package minh.lehong.yourwindowyoursoul.payload.request.accesstoken;

import lombok.Data;

import java.io.Serializable;


@Data
public class EpAccessTokenDto implements Serializable {

	private static final long serialVersionUID = 9167791459473972159L;

	private int userId;

	private String accessToken;

	private int type;
}
