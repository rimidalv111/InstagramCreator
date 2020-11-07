package com.rimidalv111.igcreator;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.swing.JTextField;

public class InstagramIndexer extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton startButton;
	private JButton stopButton;

	private BrowserHandler browserHandler;

	private JLabel accountsCount;
	private JLabel urlsCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					InstagramIndexer frame = new InstagramIndexer();
					frame.setVisible(true);
				} catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InstagramIndexer()
	{
		String workingDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", workingDir + "\\chromedriver.exe");

		//CHANGE WHEN EXPORTING JAR: (new File(".")).getAbsolutePath() + "/chromedriver.exe"
		//CHANGE WHEN CODING: "src/com/rimidalv111/browser/chromedriver.exe"

		setTitle("Instagram Account Creator");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		URL iconURL = getClass().getResource("favicon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 257);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!running)
				{
					System.out.println("Starting Instagram Account Creator");
					browserHandler.start();

				} else
				{
					System.out.println("Currently the IGCreator thread is running...");
				}
			}
		});
		startButton.setForeground(new Color(192, 192, 192));
		startButton.setBackground(new Color(0, 128, 0));
		startButton.setBounds(10, 148, 89, 23);
		contentPane.add(startButton);

		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(running)
				{
					System.out.println("Stopping IGCreator thread");
					browserHandler.stop();
				} else
				{
					System.out.println("IGCreator Thread not Running...");
				}
			}
		});
		stopButton.setForeground(new Color(192, 192, 192));
		stopButton.setBackground(new Color(128, 0, 0));
		stopButton.setBounds(240, 148, 89, 23);
		contentPane.add(stopButton);

		//count labels
		accountsCount = new JLabel("");
		accountsCount.setBounds(10, 316, 89, 23);
		contentPane.add(accountsCount);

		urlsCount = new JLabel("");
		urlsCount.setBounds(10, 330, 89, 23);
		contentPane.add(urlsCount);

		logUpdate = new JLabel("Waiting...");
		logUpdate.setBounds(10, 198, 319, 23);
		contentPane.add(logUpdate);

		lblUploadImage = new JLabel("Uploading Next Image : 1.jpg");
		lblUploadImage.setBounds(10, 11, 319, 14);
		contentPane.add(lblUploadImage);

		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		btnPause.setForeground(Color.LIGHT_GRAY);
		btnPause.setBackground(new Color(0, 128, 128));
		btnPause.setBounds(130, 148, 89, 23);
		contentPane.add(btnPause);
		
		proxyField = new JTextField();
		proxyField.setBounds(125, 101, 204, 20);
		contentPane.add(proxyField);
		proxyField.setColumns(10);
		
		useProxyLabel = new JLabel("Use Proxy:");
		useProxyLabel.setBounds(10, 104, 80, 14);
		contentPane.add(useProxyLabel);

		browserHandler = new BrowserHandler(this);

		updateMainBar("Testing main bar loading");
		updateCurrentBar("Testing current bar loading");
	}

	public JTextField getProxyField()
    {
    	return proxyField;
    }

	public void setProxyField(JTextField proxyField)
    {
    	this.proxyField = proxyField;
    }

	public void updateMainBar(String s)
	{
	}

	public void updateCurrentBar(String s)
	{
	}

	public boolean running = false;
	private JLabel logUpdate;
	private JLabel lblUploadImage;
	private JTextField proxyField;
	private JLabel useProxyLabel;

	public JLabel getLblUploadImage()
	{
		return lblUploadImage;
	}

	public void setLblUploadImage(JLabel lblUploadImage)
	{
		this.lblUploadImage = lblUploadImage;
	}

	public boolean isRunning()
	{
		return running;
	}

	public void setRunning(boolean running)
	{
		this.running = running;
	}

	public JLabel getAccountsCount()
	{
		return accountsCount;
	}

	public JLabel getUrlsCount()
	{
		return urlsCount;
	}

	public void setAccountsCount(JLabel accountsCount)
	{
		this.accountsCount = accountsCount;
	}

	public void setUrlsCount(JLabel urlsCount)
	{
		this.urlsCount = urlsCount;
	}

	public JLabel getLogUpdate()
	{
		return logUpdate;
	}

	public void setLogUpdate(JLabel logUpdate)
	{
		this.logUpdate = logUpdate;
	}
}
