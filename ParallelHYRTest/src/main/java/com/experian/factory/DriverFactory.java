package com.experian.factory;


import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
public class DriverFactory {

	public RemoteWebDriver driver;

	public static ThreadLocal<RemoteWebDriver> tlDriver = new ThreadLocal<>();

	/**
	* This method is used to initialize the thradlocal driver on the basis of given
	* browser
	*
	* @param browser
	* @return this will return tldriver.
	*/
	public RemoteWebDriver init_driver(String browser) {

	System.out.println("browser value is: " + browser);

	if (browser.equals("chrome")) {
	WebDriverManager.chromedriver().setup();
	ChromeOptions options = new ChromeOptions();
	HashMap<String, Object> prefs = new HashMap<>();
	prefs.put("plugins.always_open_pdf_externally", true);
	prefs.put("download.default_directory", "./src/test/java");
	options.setExperimentalOption("prefs", prefs);
	//WebDriver driver = new ChromeDriver(options);
	tlDriver.set(new ChromeDriver(options));
	} else if (browser.equals("firefox")) {
	WebDriverManager.firefoxdriver().setup();
	FirefoxOptions options = new FirefoxOptions();
    FirefoxProfile profile = new FirefoxProfile();		
	profile.setPreference("pdfjs.disabled", true);
	profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/doc,application/ms-doc,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
	profile.setPreference("browser.download.dir","C:\\Users\\yhanuman.ORADEV\\Music\\TestDownloads");
	profile.setPreference("browser.download.folderList", 2);
	options.setProfile(profile);		

	tlDriver.set(new FirefoxDriver(options));
	} else if (browser.equals("safari")) {
	tlDriver.set(new SafariDriver());
	} else if (browser.equals("Edge")) {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		HashMap<String, Object> prefs = new HashMap<>();
		prefs.put("plugins.always_open_pdf_externally", true);
		prefs.put("download.default_directory", "C:\\Users\\yhanuman.ORADEV\\Music\\TestDownloads");
		options.setExperimentalOption("prefs", prefs);
		tlDriver.set(new EdgeDriver(options));
	      }else {
	System.out.println("Please pass the correct browser value: " + browser);
	}

	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	return getDriver();

	}

	/**
	* this is used to get the driver with ThreadLocal
	*
	* @return
	*/
	public static synchronized RemoteWebDriver getDriver() {
	return tlDriver.get();
	}
	}
