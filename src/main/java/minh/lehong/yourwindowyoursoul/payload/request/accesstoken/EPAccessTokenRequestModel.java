package minh.lehong.yourwindowyoursoul.payload.request.accesstoken;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class EPAccessTokenRequestModel implements Serializable {

	private static final long serialVersionUID = -8265367940268511095L;

	@JsonProperty("client_id")
	private String clientId;

	@JsonProperty("client_secret")
	private String clientSecret;

	@JsonProperty("grant_type")
	private String grantType;


	public String getClientId()
	{
		return clientId;
	}

	public void setClientId(final String clientId)
	{
		this.clientId = clientId;
	}

	public String getClientSecret()
	{
		return clientSecret;
	}

	public void setClientSecret(final String clientSecret)
	{
		this.clientSecret = clientSecret;
	}

	public String getGrantType()
	{
		return grantType;
	}

	public void setGrantType(final String grantType)
	{
		this.grantType = grantType;
	}

	@Override
	public String toString()
	{
		return "EPAccessTokenRequestModel{" + "clientId='" + clientId + '\'' + ", clientSecret='" + clientSecret + '\''
				+ ", grantType='" + grantType + '\'' + '}';
	}
}
