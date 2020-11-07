package com.rimidalv111.smspva.gson;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ActivationNumberCountBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	public String response;

	@SerializedName("counts Instagram")
	public String count;

	public String getResponse()
	{
		return response;
	}

	public String getCount()
	{
		return count;
	}

	public void setResponse(String response)
	{
		this.response = response;
	}

	public void setCount(String count)
	{
		this.count = count;
	}

}
