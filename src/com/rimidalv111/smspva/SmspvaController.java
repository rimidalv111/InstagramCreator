package com.rimidalv111.smspva;

public class SmspvaController
{
	
	private String APIKEY = "MLfRKeSUGr0z4TCY8GuuN0jn7pSmQR";

	
	public void connect()
	{
		(new SmspvaConnectThread(this)).start();
		System.out.println("Connecting and validating account... ");
	}
	
	public String getAPIKEY()
    {
	    return APIKEY;
    }

	public void setAPIKEY(String aPIKEY)
    {
	    APIKEY = aPIKEY;
    }
	
	
}
