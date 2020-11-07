package com.rimidalv111.igcreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import com.rimidalv111.random.RandomNameGenerator;
import com.rimidalv111.smspva.SmspvaController;

public class BrowserHandler
{
	public InstagramIndexer instance;

	private String captchaKey;
	private ArrayList<String> urls = new ArrayList<String>();
	private ArrayList<String> successfullAccount = new ArrayList<String>();
	private int imageCounter = 1;
	private ArrayList<Account> accounts = new ArrayList<Account>();

	private Thread igCreatorThread;
	
	public BrowserHandler(InstagramIndexer in)
	{
		instance = in;
//#################### ENABLE WHEN READY TO RUN TESTS		
		//(new IPListener(this)).start();
	}

	public void start()
	{
		
		//(new BrowserThread(this, getWhiteListedAccount())).start();
		igCreatorThread = new InstagramCreator(this, instance.getProxyField().getText());
		igCreatorThread.start();

//###### SMSPVA TESTING
//		SmspvaController sc = new SmspvaController();
//		sc.connect();
	}

	public void forceSave(String saveInfo)
	{
		save(saveInfo);
	}

	public void save(String info)
	{
		System.out.println("Saving: " + info);
		try
		{
			File dir = new File(".");
			String loc = dir.getCanonicalPath() + File.separator + "accounts.txt";
			
			FileWriter fstream = new FileWriter(loc, true);
			BufferedWriter writer = null;
			try
			{
				writer = new BufferedWriter(fstream);
	
				writer.write("\r\n" + info);

				writer.flush();
				writer.close();
			} catch(IOException e1)
			{
				e1.printStackTrace();
			}
		} catch (Exception io)
		{
			io.printStackTrace();
		}
	}

	public void stop()
	{
		((InstagramCreator) igCreatorThread).killThread();
	}

	public void updateNextImageNumber()
	{
		instance.getLblUploadImage().setText("Uploading Next Image: " + imageCounter + ".jpg");
	}
	
//	public void newIpArrival()
//	{
//		(new InstagramCreator(this, getWhiteListedAccount())).start();
//	}

	public void updateLogString(String string)
	{
		instance.getLogUpdate().setText(string);
		instance.revalidate();
		instance.repaint();
	}

	public Account getWhiteListedAccount()
	{
		for(Account account : accounts)
		{
			if(!account.isBlacklisted())
			{
				return account;
			}
		}
		return null;
	}

	public InstagramIndexer getInstance()
	{
		return instance;
	}

	public String getCaptchaKey()
	{
		return captchaKey;
	}

	public ArrayList<String> getUrls()
	{
		return urls;
	}

	public ArrayList<Account> getAccounts()
	{
		return accounts;
	}

	public void setInstance(InstagramIndexer instance)
	{
		this.instance = instance;
	}

	public void setCaptchaKey(String captchaKey)
	{
		this.captchaKey = captchaKey;
	}

	public void setUrls(ArrayList<String> urls)
	{
		this.urls = urls;
	}

	public void setAccounts(ArrayList<Account> accounts)
	{
		this.accounts = accounts;
	}

	public ArrayList<String> getSuccessfullAccount()
	{
		return successfullAccount;
	}

	public void setSuccessfullAccount(ArrayList<String> successfullAccount)
	{
		this.successfullAccount = successfullAccount;
	}

	public int getImageCounter()
    {
	    return imageCounter;
    }

	public void setImageCounter(int imageCounter)
    {
	    this.imageCounter = imageCounter;
    }
}
