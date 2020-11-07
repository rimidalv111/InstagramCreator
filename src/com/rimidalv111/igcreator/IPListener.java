package com.rimidalv111.igcreator;

import java.net.InetAddress;

public class IPListener extends Thread
{
	
	private BrowserHandler instance;
	
	private String lastIP = "";
	private String myIP = "";
	
	
	public IPListener(BrowserHandler in)
	{
		instance = in;
		try
		{
			InetAddress addr = InetAddress.getLocalHost(); 
			myIP = addr.toString();
		} catch (Exception io)
		{
			io.printStackTrace();
		}
		
	}
	
	public void run()
	{
		int i = 0;
		while(i == 0)
		{
			try
			{
				Thread.sleep(5000);
				
				InetAddress addr = InetAddress.getLocalHost();            
				System.out.println(addr.getHostAddress());
				
				if(!lastIP.equalsIgnoreCase(addr.toString()))
				{
					if(!myIP.equalsIgnoreCase(addr.toString()))
					{
						System.out.println("IP Changed.. Letting Handler Know...");
						//.instance.newIpArrival();
					} else
					{
						System.out.println("Please use a proxy so work can begin!");
						instance.updateLogString("Please use a proxy so work can begin!");
					}
				}
				
				lastIP = addr.toString();
				
				
				
				
			} catch (Exception io)
			{
				io.printStackTrace();
			}
		}
	}
}
