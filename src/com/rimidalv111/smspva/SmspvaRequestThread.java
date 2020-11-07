package com.rimidalv111.smspva;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.rimidalv111.smspva.gson.ActivationNumberCountBean;
import com.rimidalv111.smspva.gson.BalanceInquiryBean;
import com.rimidalv111.smspva.gson.BanNumberBean;
import com.rimidalv111.smspva.gson.ConfirmNumberBean;
import com.rimidalv111.smspva.gson.NewNumberBean;

public class SmspvaRequestThread extends Thread
{
	private SmspvaController instance;

	private String getNewNumberCall = "";
	private String getStillAvailableCall = "";
	private String getBanCall = "";
	
	//netbean
	private NewNumberBean nnb;
	private ConfirmNumberBean cnb;
	private BanNumberBean bnb;
	

	public SmspvaRequestThread(SmspvaController i)
	{
		instance = i;

		getNewNumberCall = "http://smspva.com/priemnik.php?metod=get_number&country=RU&service=opt16&id=1&apikey=" + instance.getAPIKEY();

		getStillAvailableCall = "http://smspva.com/priemnik.php?metod=get_proverka&service=opt16&number=%number%&apikey=" + instance.getAPIKEY();
		
		getBanCall = "http://smspva.com/priemnik.php?metod=ban&service=opt16&id=%id%&apikey=" + instance.getAPIKEY();
	}

	public void run()
	{
		try
		{

			//get a new number
			if(requestNewNumber())
			{
				Thread.sleep(2000);
				
				System.out.println("Got new number, verifying...");
				
				if(confirmNewNumber())
				{
					
				} else
				{
					System.out.println("Number not valid anymore, fetching new one");
//############# Fetch new number
				}
			} else
			{
				System.out.println("Could not request a new number...");
				
				//############# Try again		
			}
			
		} catch(Exception io)
		{
			System.out.println("SmspvaRequestThread Error");
			io.printStackTrace();
		}
	}

	public boolean requestNewNumber()
	{
		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(getNewNumberCall);
			HttpResponse response;
			String result = null;
			try
			{
				response = client.execute(request);
				HttpEntity entity = response.getEntity();

				if(entity != null)
				{

					Gson gson = new Gson();

					String json = EntityUtils.toString(response.getEntity());

					nnb = gson.fromJson(json, NewNumberBean.class);

					System.out.println("Response: " + nnb.getResponse() + "  Number: " + nnb.getNumber() + "  ID: " + nnb.getId());

					System.out.println("BAN URL: " + getBanCall.replace("%id%", nnb.getId()));
					
					//did we successfully get a number?
					if(Integer.parseInt(nnb.getResponse()) == 1)
					{
						return true;
					} else
					{
						//we didn't so run this method again in 60 seconds until we do get a new number
						Thread.sleep(15000);

						return requestNewNumber();
					}

				}

			} catch(ClientProtocolException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			} catch(IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
		} catch(Exception io)
		{
			System.out.println("Received Error while trying to request number");
			io.printStackTrace();
		}
		return false;
	}
	
	public boolean confirmNewNumber()
	{
		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(getStillAvailableCall.replace("%number%", nnb.getNumber()));
			HttpResponse response;
			String result = null;
			try
			{
				response = client.execute(request);
				HttpEntity entity = response.getEntity();

				if(entity != null)
				{

					Gson gson = new Gson();

					String json = EntityUtils.toString(response.getEntity());

					cnb = gson.fromJson(json, ConfirmNumberBean.class);

					System.out.println("Response: " + cnb.getResponse() + "  Number: " + cnb.getNumber() + "  ID: " + cnb.getId());

					//did we successfully get a number?
					if(cnb.getResponse().equalsIgnoreCase("ok"))
					{
						return true;
					} else
					{
						//fetch new number again?
						return false;
					}

				}

			} catch(ClientProtocolException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			} catch(IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
		} catch(Exception io)
		{
			System.out.println("Received Error while trying to confirm new number (error message from api)");
			io.printStackTrace();
		}
		return false;
	}
	
	public boolean banNumber()
	{
		try
		{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(getBanCall.replace("%id%", nnb.getId()));
			HttpResponse response;
			String result = null;
			try
			{
				response = client.execute(request);
				HttpEntity entity = response.getEntity();

				if(entity != null)
				{

					Gson gson = new Gson();

					String json = EntityUtils.toString(response.getEntity());

					bnb = gson.fromJson(json, BanNumberBean.class);

					System.out.println("Response: " + bnb.getResponse() + "  Number: " + bnb.getNumber() + "  ID: " + bnb.getId());

					//did we successfully get a number?
					if(Integer.parseInt(bnb.getResponse()) == 1)
					{
						return true;
					} else
					{
						//fetch new number again?
						return false;
					}

				}

			} catch(ClientProtocolException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			} catch(IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}
		} catch(Exception io)
		{
			System.out.println("Received Error while trying to confirm new number (error message from api)");
			io.printStackTrace();
		}
		return false;
	}
	
}
