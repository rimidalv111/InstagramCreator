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

public class SmspvaConnectThread extends Thread
{
	private SmspvaController instance;
	private String getBalanceCall = "";
	private String getAvailableNumbersCall = "";
	
	//netbean
	private BalanceInquiryBean bib;
	private ActivationNumberCountBean ancb;
	
	public SmspvaConnectThread(SmspvaController i)
	{
		instance = i;

		getBalanceCall = "http://smspva.com/priemnik.php?metod=get_balance&service=opt16&apikey=" + instance.getAPIKEY();

		getAvailableNumbersCall = "http://smspva.com/priemnik.php?metod=get_count&service=opt16&service_id=instagram&apikey=" + instance.getAPIKEY();
			
	}

	public void run()
	{
		try
		{
			
			//make sure we have money
			if(getBalance())
			{
				Thread.sleep(2000); //wait 2 seconds before next call
				
				//make sure we can purchase new numbers
				if(getAvailableNumberCount() > 1)
				{
					Thread.sleep(2000); //wait 2 seconds before next call
					
					(new SmspvaRequestThread(instance)).start();
					
				} else
				{
					System.out.println("No available numbers!");
				}
			} else
			{
				System.out.println("Cannot connect to your SMSPVA balance!!");
			}
			
			
		} catch(Exception io)
		{
			System.out.println("SmspvaConnectThread Error");
			io.printStackTrace();
		}
	}
	
	public boolean getBalance()
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(getBalanceCall);
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
				
				bib = gson.fromJson(json, BalanceInquiryBean.class);

				System.out.println("Response: " + bib.getResponse() +  "  Balance: " + bib.getBalance());
				
				if(Double.parseDouble(bib.getBalance()) > 0.04)
				{
					return true;
				} else
				{
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
		return false;
	}
	
	public Integer getAvailableNumberCount()
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(getAvailableNumbersCall);
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
				
				ancb = gson.fromJson(json,ActivationNumberCountBean.class);

				System.out.println("Response: " + ancb.getResponse() +  "  Count: " + ancb.getCount());
				
				return Integer.parseInt(ancb.getCount());
			
			}

		} catch(ClientProtocolException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return -1;
		} catch(IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return -1;
		}
		return -1;
	}
}
