package com.experian.page;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import com.experian.factory.DriverFactory;
import com.experian.util.BasePage;
import com.experian.util.ConfigReader;
public class FraudAdminPage extends BasePage {
	  private DriverFactory driverFactory;
	  private WebDriver driver;
	  private ConfigReader configReader;
	  Properties prop;

	  public void getProperty() {
	      configReader = new ConfigReader();
	      prop = configReader.init_prop();
	  }
	 
	  public FraudAdminPage(WebDriver driver) {
	    super(driver);
	  }

	  public boolean navigateToHomePage(String fraudadminurl) {
	    return navigateto(fraudadminurl);
	  }

	  public String getUSerID() {
	    return prop.getProperty("userid");
	  }

	  public String getPassword() {
	    return prop.getProperty("password");
	  }

	  public  String getQuestion() {
	    return prop.getProperty("question");
	  }
	}

