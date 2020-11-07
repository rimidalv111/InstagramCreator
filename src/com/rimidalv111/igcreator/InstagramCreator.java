package com.rimidalv111.igcreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import com.rimidalv111.random.RandomNameGenerator;

public class InstagramCreator extends Thread
{
	private BrowserHandler instance;
	private WebDriver driver;
	private String proxy;

	private String name = "{{Easy|Straightforward|Simple}{ |}{Fast|Quick|Swift|New}{ |}Fame|{Become|Get}{ |}{Insta{ |}Fame|Insta{ |}Famous|Insta Famus|IG{ |}Famous|IG{ |}Fame}|{{Become|Get}{ |}{Easy|Straightforward|Simple}{ |}Fame}}";
	private String bio = "";
	private String bioSpintax = "{Not {many people|many individuals} {know how|understand how} {easy|straightforward|simple} {it is|it's} to get new {friends|pals|associates|buddies|mates}{!|!!|!!!}|{Learn how|Find out how} {easy|straightforward|simple} {it is|it's} to get new {friends|pals|associates|buddies|mates} in {picture|image}{!|!!|!!!}|{Find out how|Learn how} {your friends|your mates|your folks|your pals} are getting their fame in {image|picture}{!|!!|!!!}|{Super|Tremendous} {easy|straightforward|simple} {way to|method to|approach to|strategy to|technique to} get {a lot of|lots of|plenty of|loads of|numerous|a whole lot of|a number of|a variety of|quite a lot of} new {friends|pals|associates|buddies|mates}, {check out|take a look at|try} {image|picture} {below|under|beneath}{!|!!|!!!}|{Just|Simply} do {all of the|all the|the entire} {instructions|directions} {below|under|beneath}{!|!!|!!!}|The {instructions|directions} {below|under|beneath} will {tell you|inform you|let you know} how{!|!!|!!!}|Never been {simpler|easier|less complicated} to {become|become super} {famous|well-known}, see {image|picture} {below|under|beneath}{!|!!|!!!}}";
	private String email = "";
	private String emailHosts = "{theta-move.com|aeropeans.com|homefromiraqnow.org|boobiesandbeer.net|fv911truth.org|miltracker.com|washingtonforimpeachment.org|uggsbootshotsale.com|decisivewin.com|pdaohio.org|katzforcongress.com|joanmaxcy.com|projectflaunt.com|flagindiepress.com|dailybiomed.com|freethinkersasylum.com|newlifecenterforhealth.com|reservepilot.com|ronandhermionesource.com|theicy.net|wynkenblynk.com|otibrown.com|republicansforimpeachment.com|dubiousprofundity.com|civilrightsdefence.org|dj-shane.net|robertsoloway.com|wosdgroup.com|impeachment-sunday.org|michaelsteinbach.org|instituteofpaperhats.org|magicdinner.org|jupiterpluvius.com|compromisoong.org|fragilemusings.net}";
	private String username = "{get|claim|receive|discover|find|seek}{.|_|}%num%{.|_|}{new|best|}{.|_|}{friends|pals}";
			
	private String[] userAgentPhone = new String [] {"Mozilla/5.0 (Linux; Android 6.0.1; SM-G920V Build/MMB29K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36","Mozilla/5.0 (Linux; Android 5.1.1; SM-G928X Build/LMY47X) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.83 Mobile Safari/537.36","Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Microsoft; Lumia 950) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Mobile Safari/537.36 Edge/13.10586","Mozilla/5.0 (Linux; Android 6.0.1; Nexus 6P Build/MMB29P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.83 Mobile Safari/537.36","Mozilla/5.0 (Linux; Android 6.0.1; E6653 Build/32.2.A.0.253) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36","Mozilla/5.0 (Linux; Android 6.0; HTC One M9 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Mobile Safari/537.36","Mozilla/5.0 (Linux; Android 7.0; Pixel C Build/NRD90M; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.98 Safari/537.36","Mozilla/5.0 (Linux; Android 6.0.1; SGP771 Build/32.2.A.0.253; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.98 Safari/537.36","Mozilla/5.0 (Linux; Android 5.1.1; SHIELD Tablet Build/LMY48C) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.98 Safari/537.36","Mozilla/5.0 (Linux; Android 5.0.2; SAMSUNG SM-T550 Build/LRX22G) AppleWebKit/537.36 (KHTML, like Gecko) SamsungBrowser/3.3 Chrome/38.0.2125.102 Safari/537.36","Mozilla/5.0 (Linux; Android 4.4.3; KFTHWI Build/KTU84M) AppleWebKit/537.36 (KHTML, like Gecko) Silk/47.1.79 like Chrome/47.0.2526.80 Safari/537.36","Mozilla/5.0 (Linux; Android 5.0.2; LG-V410/V41020c Build/LRX22G) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/34.0.1847.118 Safari/537.36","Mozilla/5.0 (CrKey armv7l 1.5.16041) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.0 Safari/537.36","Mozilla/5.0 (Linux; U; Android 4.2.2; he-il; NEO-X5-116A Build/JDQ39) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30","Mozilla/5.0 (Linux; Android 4.2.2; AFTB Build/JDQ39) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.173 Mobile Safari/537.22","Dalvik/2.1.0 (Linux; U; Android 6.0.1; Nexus Player Build/MMB29T)","Mozilla/5.0 (Nintendo WiiU) AppleWebKit/536.30 (KHTML, like Gecko) NX/3.0.4.2.12 NintendoBrowser/4.3.1.11264.US","Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Xbox; Xbox One) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Mobile Safari/537.36 Edge/13.10586","Mozilla/5.0 (PlayStation 4 3.11) AppleWebKit/537.73 (KHTML, like Gecko)","Mozilla/5.0 (PlayStation Vita 3.61) AppleWebKit/537.73 (KHTML, like Gecko) Silk/3.2","Mozilla/5.0 (Nintendo 3DS; U; ; en) Version/1.7412.EU","Mozilla/5.0 (X11; U; Linux armv7l like Android; en-us) AppleWebKit/531.2+ (KHTML, like Gecko) Version/5.0 Safari/533.2+ Kindle/3.0+","Mozilla/5.0 (Linux; U; en-US) AppleWebKit/528.5+ (KHTML, like Gecko, Safari/528.5+) Version/4.0 Kindle/3.0 (screen 600x800; rotate)"};
	private String[] userAgent = new String[] {"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.246","Mozilla/5.0 (X11; CrOS x86_64 8172.45.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.64 Safari/537.36","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/601.3.9 (KHTML, like Gecko) Version/9.0.2 Safari/601.3.9","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.111 Safari/537.36","Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:15.0) Gecko/20100101 Firefox/15.0.1"};
	
	public InstagramCreator(BrowserHandler bhi, String pr)
	{
		instance = bhi;
		proxy = pr;
		
		instance.getInstance().setRunning(true);
		
		System.out.println("Initializing browser thread...");

		Random rn = new Random();
		int pick = rn.nextInt(999);
		
		username = parseSpintax(new Random(), username).replace("%num%", "" + pick);

		name = parseSpintax(new Random(), name);
		
		bio = parseSpintax(new Random(), bioSpintax);
		email = username.replace("[^A-Za-z0-9-]", "").substring(0, 3);
		
		if(email.contains("."))
		{
			email = email.replace(".", "i");
		}
		if(email.contains("_"))
		{
			email = email.replace("_", "g");
		}
		email = email + pick + "@" + parseSpintax(new Random(), emailHosts);
		
		
		if(username.contains(".."))
		{
			username = username.replace("..", ".");
		}
		if(username.contains("__"))
		{
			username = username.replace("__", "_");
		}
		
		if(username.startsWith(".") || username.startsWith("_"))
		{
			username = username.substring(1);
		}
		
		System.out.println("Username: " + username + " Name: " + name + " Bio:" + bio + " Email: " + email);
		instance.updateLogString("Creating a new account: " + name);
	}

	public void run()
	{
		try
		{
			setUpBrowser();
		} catch(Exception io)
		{
			io.printStackTrace();
		}
	}

	public void setUpBrowser()
	{
		try
		{
			instance.updateLogString("Launching Browser");
			launchBrowser();


			Thread.sleep(3000);

			//go to submit url login url
			navigateToLoginPage();


			Thread.sleep(1000);

			instance.updateLogString("Creating Account.... ");
			
			//###################
			signUp();

			//wait 5 seconds 
			Thread.sleep(8000);

			if(!successSubmit()) //if submit failed stop browser and ask for/get new ip
			{
				instance.updateLogString("Couldn't validate account after submit!!");
				killThread();
			}
			//########################	
			
			Thread.sleep(2000);
			instance.updateLogString("Updating Profile Info " + username);
			
			//##########################			
			submitProfileInfo();
			//#######################			
			
			Thread.sleep(15000);
			instance.updateLogString("Interacting with " + username);
			
			//#######################		
			quickFollows();
			//#######################
			
			
			System.out.println("All Done! Account Created! ");

			Thread.sleep(15000);

			instance.updateLogString("Success! Please change IP!");
			
			playCompleteSound();
			
			instance.forceSave(username + ":" + email);
			killThread();
		} catch(Exception io)
		{
			io.printStackTrace();
		}
	}
	
	public void launchBrowser()
	{		
		if(!proxy.isEmpty())
		{
			System.out.println("Connecting with proxy: " + proxy);
			
			org.openqa.selenium.Proxy p = new org.openqa.selenium.Proxy();
			p.setHttpProxy(proxy).setFtpProxy(proxy).setSslProxy(proxy);
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--user-agent=" + getRandomUserAgent());
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability(CapabilityType.PROXY, p);
			cap.setCapability(ChromeOptions.CAPABILITY , co);
			
			driver = new ChromeDriver(cap);
			driver.manage().window().setSize(new Dimension(750, 1000));

	        
		} else
		{
			driver = new ChromeDriver();
			driver.manage().window().setSize(new Dimension(750, 1000));
		}
	}

	
	

	
	public String getRandomUserAgent()
	{
		int randomPick = (new Random()).nextInt(userAgent.length);
		return userAgent[randomPick];
		
	}
	
	public void navigateToLoginPage()
	{
		//go to instagram url
		driver.get("https://www.instagram.com");

	}

	public void signUp()
	{
		try
		{
			//find and fillout email field
			WebElement elementEmail = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[2]/div[1]/div/form/div[2]/input"));

			Thread.sleep(1000);

			elementEmail.sendKeys(email);

			Thread.sleep(5000);

			//find and fillout name field
			WebElement elementName = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[2]/div[1]/div/form/div[3]/input"));

			elementName.sendKeys(name);

			Thread.sleep(5000);

			//find and fillout username field
			WebElement elementUsername = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[2]/div[1]/div/form/div[4]/input"));

			Thread.sleep(1000);

			elementUsername.sendKeys(Keys.CONTROL + "a");
			elementUsername.sendKeys(Keys.DELETE);

			Thread.sleep(1000);

			elementUsername.sendKeys(Keys.CONTROL + "a");
			elementUsername.sendKeys(Keys.DELETE);

			Thread.sleep(1000);

			elementUsername.sendKeys(username);

			System.out.println("USERNAME: " + username);

			Thread.sleep(5000);

			//find and fillout password field
			WebElement elementFirstName = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[2]/div[1]/div/form/div[5]/input"));

			elementFirstName.sendKeys("M0narch71!");

			Thread.sleep(5000);

			//find and fillout firstname field
			WebElement elementSubmit = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[2]/div[1]/div/form/div[6]/span/button"));

			elementSubmit.click();

		} catch(Exception io)
		{
			io.printStackTrace();
		}
	}

	public boolean successSubmit()
	{
		try
		{
			WebElement elementProfileButton = driver.findElement(By.xpath("//*[@id='react-root']/section/nav/div/div/div/div[3]/div/div[3]/a"));
			return true;
		} catch(Exception io)
		{
			return false;
		}
	}

	public void submitProfileInfo()
	{
		try
		{
			//go to profile account info
			driver.get("https://www.instagram.com/accounts/edit/");

			Thread.sleep(1000);

			WebElement bioElement = driver.findElement(By.xpath("//*[@id='pepBio']"));
			bioElement.sendKeys(bio);

			Thread.sleep(1000);

			Select genderDropdown = new Select(driver.findElement(By.xpath("//*[@id='pepGender']")));

			Random genRan = new Random();
			int fakeGen = genRan.nextInt(2);
			genderDropdown.selectByIndex(fakeGen);

			Thread.sleep(2000);

//### Scroll Down
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,250)", "");
//#########		
			
			//get submit button element
			WebElement submitElement = driver.findElement(By.xpath("//*[@id='react-root']/section/main/div/article/form/div[10]/div/div/span/button"));
			
			
			WebElement profileImageElement = driver.findElement(By.xpath("//*[@id='react-root']/section/main/div/article/div/div/button"));

			profileImageElement.click();

			Thread.sleep(1500);
			
			//dont forget i added code above
			// /html/body/div[2]/div/div[2]/div/ul/li[3]/button
		
				WebElement uploadProfileElement = driver.findElement(By.xpath("//button[contains(.,'Upload Photo')]"));
				//WebElement uploadProfileElementTwo = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/ul/li[3]/button"));
				
		
			
			//################################################ File Upload

			String workingDir = System.getProperty("user.dir");

			String autoitscriptpath = workingDir + "\\AutoIt\\" + "AutoIt_Instructions.au";

			//(new File(".")).getAbsolutePath() + "/chromedriver.exe"

			String filepath = workingDir + "\\profile_images\\" + (new Random()).nextInt(20) + ".jpg"; //instance.getImageCounter() + ".jpg";
			
			
			
			instance.setImageCounter(instance.getImageCounter() + 1);

			instance.updateNextImageNumber();

			// Added a wait to make you notice the difference.
			Thread.sleep(1000);

			//CLICK ON UPLOAD BUTTON
			uploadProfileElement.click();

			// Added sleep to make you see the difference.
			Thread.sleep(1000);

			Runtime.getRuntime().exec("cmd.exe /c Start " + workingDir + "\\AutoIt\\AutoIt3.exe " + autoitscriptpath + " \"" + filepath + "\"");

			Thread.sleep(3000);

			//#######################################################

			//submit save profile
			submitElement.click();

		} catch(Exception io)
		{
			io.printStackTrace();
		}

	}

	public void quickFollows()
	{
		String[] usernames =
		{ "disney", "disneyfrozen", "dccomics" };
		for(String u : usernames)
		{
			superFollow(u);
		}
	}

	public void superFollow(String username)
	{
		try
		{
			driver.get("https://www.instagram.com/" + username + "/");

			Thread.sleep(1000);

			WebElement followMainUser = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/header/div[2]/div[2]/span/span[1]/button"));
			followMainUser.click();

			Thread.sleep(1000);

			WebElement follow1st = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[1]/div[2]/div/div[1]/div/ul/li[1]/div/div/span/button"));
			follow1st.click();

			Thread.sleep(1000);

			WebElement follow2st = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[1]/div[2]/div/div[1]/div/ul/li[2]/div/div/span/button"));
			follow2st.click();

			Thread.sleep(1000);

			WebElement follow3st = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[1]/div[2]/div/div[1]/div/ul/li[3]/div/div/span/button"));
			follow3st.click();

			Thread.sleep(1000);

			WebElement follow4st = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[1]/div[2]/div/div[1]/div/ul/li[4]/div/div/span/button"));
			follow4st.click();

			Thread.sleep(1000);

			WebElement follow5st = driver.findElement(By.xpath("//*[@id='react-root']/section/main/article/div[1]/div[2]/div/div[1]/div/ul/li[5]/div/div/span/button"));
			follow5st.click();

			Thread.sleep(1000);

			
			
		} catch(Exception io)
		{
			io.printStackTrace();
		}
	}

	static String parseSpintax(Random rnd, String str)
	{
		String pat = "\\{[^{}]*\\}";
		Pattern ma;
		ma = Pattern.compile(pat);
		Matcher mat = ma.matcher(str);
		while (mat.find()) //when we find patter {||}
		{
			String segono = str.substring(mat.start() + 1, mat.end() - 1); //remove { }
			String[] choies = segono.split("\\|", -1); //split by |
			str = str.substring(0, mat.start()) + choies[rnd.nextInt(choies.length)].toString() + str.substring(mat.start() + mat.group().length()); //pick random
			mat = ma.matcher(str); //
		}
		return str;
	}

	public void playCompleteSound()
	{
	    AudioInputStream audioIn;
        try
        {
	        audioIn = AudioSystem.getAudioInputStream(IPListener.class.getResource("done.wav"));

		    Clip clip = null;
            try
            {
	            clip = AudioSystem.getClip();
            } catch(LineUnavailableException e)
            {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
            }
		    try
            {
	            clip.open(audioIn);
            } catch(LineUnavailableException e)
            {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
            }
		    clip.start();
        } catch(UnsupportedAudioFileException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        } catch(IOException e)
        {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
	
	public void killThread()
	{
		instance.getInstance().setRunning(false);
		
		driver.close();
		this.interrupt();
	}
}
