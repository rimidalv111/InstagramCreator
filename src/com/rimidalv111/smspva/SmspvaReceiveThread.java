package com.rimidalv111.smspva;

public class SmspvaReceiveThread extends Thread
{
	private SmspvaController instance;
	
	public SmspvaReceiveThread(SmspvaController i)
	{
		instance = i;
	}
	
	public void run()
	{
		try
		{
			
		} catch(Exception io)
		{
			System.out.println("SmspvaReceiveThread Error");
			io.printStackTrace();
		}
	}
}
