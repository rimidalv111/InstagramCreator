package com.rimidalv111.smspva.gson;

import java.io.Serializable;

public class NewNumberBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String response;
	private String number;
	private String id;

	public String getResponse()
	{
		return response;
	}

	public String getNumber()
	{
		return number;
	}

	public String getId()
	{
		return id;
	}

	public void setResponse(String response)
	{
		this.response = response;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public void setId(String id)
	{
		this.id = id;
	}

}
