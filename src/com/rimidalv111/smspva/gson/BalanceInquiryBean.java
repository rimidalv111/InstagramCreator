package com.rimidalv111.smspva.gson;

import java.io.Serializable;

public class BalanceInquiryBean implements Serializable
{
	private static final long serialVersionUID = 1L;

	public String response;
	public String balance;

	
	public String getResponse()
	{
		return response;
	}

	public String getBalance()
	{
		return balance;
	}

	public void setResponse(String response)
	{
		this.response = response;
	}

	public void setBalance(String balance)
	{
		this.balance = balance;
	}
}
