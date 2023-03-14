package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class Timestamps implements Serializable
{
	private static final long serialVersionUID = 1L;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public String getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(String createdAt)
	{
		this.createdAt = createdAt;
	}

	public String getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(String updateAt)
	{
		this.updatedAt = updateAt;
	}

	public Timestamps(String createdAt, String updatedAt)
	{
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Timestamps()
	{
		super();
	}


}
