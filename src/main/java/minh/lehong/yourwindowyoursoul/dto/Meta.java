package minh.lehong.yourwindowyoursoul.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class Meta implements Serializable
{
	private static final long serialVersionUID = 1L;

	@JsonProperty("timestamps")
	private Timestamps timestamps;

	public Timestamps getTimestamps()
	{
		return timestamps;
	}

	public void setTimestamps(Timestamps timestamps)
	{
		this.timestamps = timestamps;
	}

	public Meta(Timestamps timestamps)
	{
		super();
		this.timestamps = timestamps;
	}

	public Meta()
	{
		super();
	}



}
