package com.experian.util;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import dev.failsafe.internal.util.Assert;
public class BasePage {

	  //private static WebDriver driver;
	  private WebDriver driver;

	  public BasePage(WebDriver driver) {
	    this.driver = driver;
	  }

	  /*
	   * protected WebDriver getDriver() { return driver; }
	   */

	  /*
	   * protected WebDriverWait driverWait(long timeoutSeconds) { return new WebDriverWait(driver,
	   * timeoutSeconds); }
	   */

	  public boolean navigateto(String arg1) {

	    boolean navigatehome = false;
	    try {
	      driver.navigate().to(arg1);
	      navigatehome = true;
	    } catch (Exception e) {
	     //Assert.assertFalse(false);;
	    }
	    return navigatehome;
	  }

	  public String captureScreenShot(WebDriver driver) throws IOException {
	    TakesScreenshot screen = (TakesScreenshot) driver;
	    File src = screen.getScreenshotAs(OutputType.FILE);
	    String dest = "./src/test/resources/screenshots/" + getcurrentdateandtime() + ".png";
	    File target = new File(dest);
	    FileUtils.copyFile(src, target);
	    return dest;
	  }

	  private String getcurrentdateandtime() {
	    String str = null;
	    try {
	      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
	      Date date = new Date();
	      str = dateFormat.format(date);
	      str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
	    } catch (Exception e) {
	    }
	    return str;
	  }

	  /*
	   * private static void generateReport(String status, String message) throws IOException { if
	   * (prop.getProperty("loggerreport").contains("true")) { switch (status.toLowerCase()) { case
	   * "info": StepDefinitions.test.log(Status.INFO, MarkupHelper.createLabel(message,
	   * ExtentColor.YELLOW)); case "passed": StepDefinitions.test.log(Status.PASS,
	   * MarkupHelper.createLabel(message, ExtentColor.GREEN)); break; case "failed":
	   * StepDefinitions.test.log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
	   * StepDefinitions.test.addScreenCaptureFromPath( System.getProperty("user.dir") + "/" +
	   * captureScreenShot(driver)); break; default: StepDefinitions.test.log(Status.SKIP,
	   * MarkupHelper.createLabel(message, ExtentColor.BLUE)); break; } } }
	   */
	  // --------------------------------------------------------------------------------------------------------------------------------
	  // Dropdown list
	  // --------------------------------------------------------------------------------------------------------------------------------

	  public boolean DropDownAction(By locator, String valuetoselect) throws Exception {
	    boolean result = false;
	    try {
	      if (isElementPresent(locator)) {
	        Select sel = new Select(driver.findElement(locator));
	        sel.selectByVisibleText(valuetoselect);
	        // Thread.sleep(200);
	        if (driver.findElement((By) locator).getText().contains(valuetoselect)) {
	          /*
	           * generateReport("passed", "DropDownAction Passed :  Select Element '" +
	           * gAttributevalue(locator) + "' was set with - '" + driver.findElement((By)
	           * locator).getText() + "'");
	           */
	          result = true;
	        } else {
	          /*
	           * generateReport("failed", "DropDownAction Failed :  Select Element '" +
	           * gAttributevalue(locator) + "' was set with - '" + driver.findElement((By)
	           * locator).getText() + "'");
	           */
	        }
	      }
	    } catch (Error e) {
	     // Assert.assertFalse("DropDownAction Failed Error " + e, false);
	    }
	    return result;
	  }

	  public boolean SelectList(By locator, String valuetoselect) throws Exception {
	    boolean result = false;
	    try {
	      if (isElementPresent(locator)) {
	        Select sel = new Select(driver.findElement(locator));
	        sel.selectByIndex(2);
	        // Thread.sleep(200);
	        result = true;
	      }
	    } catch (Error e) {
	     //Assert.assertFalse("DropDownAction Failed Error " + e, false);
	    }
	    return result;
	  }

	  public boolean isPresent(By locator) throws Exception {
	    boolean result = false;
	    List<WebElement> w = null;
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	      w = driver.findElements((By) locator);
	      if (!w.isEmpty()) {
	        /*
	         * generateReport("passed", "element present " +
	         * driver.findElement(locator).getAttribute("data-qaid") + "was present - Passed ");
	         */
	        result = true;
	      }
	      return result;
	    }
	  /*
	   * ''''''''''''''''''''''''''''''''''''''''''
	   *
	   * is present in the UI
	   *
	   * ''''''''''''''''''''''''''''''''''''''''''''
	   */
	  public boolean isElementPresent(By locator) throws Exception {
	    boolean result = false;
	    List<WebElement> w = null;
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    try {
	      w = driver.findElements((By) locator);
	      if (w.isEmpty()) {
	        /*
	         * StepDefinitions.test.addScreenCaptureFromPath( System.getProperty("user.dir") + "/" +
	         * captureScreenShot(driver));
	         */
	        result = false;
	      } else {
	       // if (prop.getProperty("highlight").contains("true")) {
	          highLightElement(driver, driver.findElement((By) locator));
	       // }
	        result = true;
	      }
	    } catch (NoSuchElementException e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, "No Such Element Exception" + e);
	       * Assert.assertFalse("No Such Element Exception" + e, false);
	       */
	      throw new NoSuchElementException("No Such Element Exception");
	    } catch (NullPointerException e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, "Null point Exception" + e);
	       * Assert.assertFalse("Null point Exception" + e, false);
	       */
	      throw new NullPointerException("Null point Exception");
	    } catch (Exception e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, "Exception" + e); Assert.assertFalse("Exception" + e,
	       * false);
	       */
	      throw new Exception();
	    }
	    return result;
	  }

	  /*
	   * public void pagetoload(int arg) throws InterruptedException { new WebDriverWait(driver,
	   * arg).until(webDriver -> ((JavascriptExecutor) webDriver)
	   * .executeScript("return document.readyState").equals("complete")); }
	   */

	  public boolean clickbuttontexts(By arg, String buttonname) throws Exception {
	    boolean bclick = false;
	    int buttonsizes =
	        driver.findElements(By.xpath("//button[start-with(@id,'accordian_id_')")).size();
	    for (int i = 2; i >= buttonsizes; i++) {
	      if (driver.findElement(By.xpath("//button[start-with(@id,'accordian_id_')")).getText()
	          .contains(buttonname)) {
	        ClickAction(By.xpath("//button[start-with(@id,'accordian_id_'" + i + ")"));
	        bclick = true;
	        break;
	      }
	    }
	    return bclick;
	  }

	  public String getspantext(By arg) throws Exception {

	    String HeaderTxt =
	        driver.findElement(By.xpath("//span[@class='eds-alert_#message']")).getText();
	    return HeaderTxt;
	  }

	  public void tapKey() {
	    ((WebElement) driver).sendKeys(Keys.TAB);
	  }

	  public String gettext(By arg) throws Exception {
	    String HeaderTxt = null;
	    try {
	      if (isElementPresent(arg)) {
	        HeaderTxt = driver.findElement(arg).getText();

	        /*
	         * if (HeaderTxt != "") { generateReport("info", "get text :  " + HeaderTxt); } else {
	         * generateReport("failed", "get text :  " + HeaderTxt); }
	         */
	      }
	    } catch (Error e) {
	     // Assert.assertFalse("gettext Failed " + e, false);
	    }
	    return HeaderTxt;
	  }

	  public String getInputValue(By locator) {
	    return driver.findElement(locator).getAttribute("value");
	  }

	  public boolean getInputChecked(By locator) {
	    String value = driver.findElement(locator).getAttribute("checked");
	    return "checked".equals(value);
	  }

	  public String gAttributevalue(By locator) {
	    String valuess = null;
	    if (driver.findElement((By) locator).getAttribute("class") != null
	        && driver.findElement((By) locator).getAttribute("class") != "") {
	      valuess = driver.findElement((By) locator).getAttribute("class");
	    } else if (driver.findElement((By) locator).getAttribute("id") != null
	        && driver.findElement((By) locator).getAttribute("id") != "") {
	      valuess = driver.findElement((By) locator).getAttribute("id");
	    } else if (driver.findElement((By) locator).getAttribute("innertext") != null
	        && driver.findElement((By) locator).getAttribute("innertext") != "") {
	      valuess = driver.findElement((By) locator).getAttribute("innertext");
	    } else if (driver.findElement((By) locator).getAttribute("value") != null
	        && driver.findElement((By) locator).getAttribute("value") != "") {
	      valuess = driver.findElement((By) locator).getAttribute("value");
	    } else if (driver.findElement((By) locator).getAttribute("name") != null
	        && driver.findElement((By) locator).getAttribute("name") != "") {
	      valuess = driver.findElement((By) locator).getAttribute("name");
	    }
	    if (valuess == null || valuess == "") {
	      valuess = "Element";
	    }
	    return valuess;

	  }

	  // --------------------------------------------------------------------------------------------------------------------------------
	  // CheckBox list
	  // --------------------------------------------------------------------------------------------------------------------------------

	  public boolean CheckBoxAction(By locator) throws Exception {
	    boolean result = false;
	    try {
	      if (isElementPresent(locator)) {
	        if (!driver.findElement(locator).isSelected()) {
	          driver.findElement(locator).click();
	          /*
	           * generateReport("passed", "Checkbox " +
	           * driver.findElement(locator).getAttribute("data-qaid") + "was selected - Passed ");
	           */
	          waitForPageToLoad();
	          result = true;
	        }
	        if (driver.findElement(locator).isSelected()) {
	          result = true;
	        }
	      }
	    } catch (NoSuchElementException e) {
	     // Assert.assertFalse("No Such Element Exception" + e, false);
	      throw new NoSuchElementException("No Such Element Exception");
	    } catch (NullPointerException e) {
	     // Assert.assertFalse("Null point Exception" + e, false);
	      throw new NullPointerException("Null point Exception");
	    } catch (Exception e) {
	     // Assert.assertFalse("Exception" + e, false);
	      throw new Exception();
	    }
	    return result;
	  }
	//--------------------------------------------------------------------------------------------------
	 
	  public boolean CheckBoxAction(By locator,By slocator) throws Exception {
	    boolean result = false;
	    try {
	      if (isElementPresent(locator)) {
	        if (!driver.findElement(locator).isSelected()) {
	          driver.findElement(slocator).click();
	          /*
	           * generateReport("passed", "Checkbox " +
	           * driver.findElement(locator).getAttribute("data-qaid") + "was selected - Passed ");
	           */
	          waitForPageToLoad();
	          result = true;
	        }
	        if (driver.findElement(locator).isSelected()) {
	          result = true;
	        }
	      }
	    } catch (NoSuchElementException e) {
	     // Assert.assertFalse("No Such Element Exception" + e, false);
	      throw new NoSuchElementException("No Such Element Exception");
	    } catch (NullPointerException e) {
	     // Assert.assertFalse("Null point Exception" + e, false);
	      throw new NullPointerException("Null point Exception");
	    } catch (Exception e) {
	     // Assert.assertFalse("Exception" + e, false);
	      throw new Exception();
	    }
	    return result;
	  }
	  // ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	  /***
	   * This method will highLight Element
	   * //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	   *
	   * @param file name
	   * @return nil
	   * @throws IOException
	   */
	  public void highLightElement(WebDriver driver, WebElement element) {
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript(
	        "arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');",
	        element);

	    try {
	      Thread.sleep(500);
	    } catch (InterruptedException e) {
	      System.out.println(e.getMessage());
	    }
	    // js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
	  }

	  public boolean EnterTextAndClear(By locater, String sFIELDNAME) throws Exception {
	    boolean Etest = false;
	    try {
	      if (isElementPresent(locater)) {
	        driver.findElement((By) locater).clear();
	        waitForPageToLoad();
	        driver.findElement((By) locater).sendKeys(sFIELDNAME);
	        driver.findElement((By) locater).sendKeys(Keys.BACK_SPACE);
	        Etest = true;
	      } else {
	        /*
	         * generateReport("failed", "Enter Text Box Action  Error :  Element with tagname - " +
	         * locater + " not was 'present' ");
	         */
	      }
	    } catch (Exception e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, "Eeception : " + e.getMessage());
	       * Assert.assertFalse("Eeception : " + e.getMessage(), false);
	       */
	      throw new Exception("Exception :" + e.getMessage());
	    }
	    return Etest;
	  }

	  // ------------------------------------------------------------------------------------------------------------------------------
	  // Text field
	  // ------------------------------------------------------------------------------------------------------------------------------------
	  public boolean EnterTextAction(By locater, String sFIELDNAME) throws Exception {
	    boolean Etest = false;
	    try {
	      if (isElementPresent(locater)) {
	        String gettagname = driver.findElement((By) locater).getAttribute("name");
	        driver.findElement((By) locater).clear();
	        driver.findElement((By) locater).sendKeys(sFIELDNAME);
	        waitForPageToLoad();
	        driver.findElement((By) locater).sendKeys(Keys.TAB);
	        /*
	         * generateReport("passed", "Enter Text Box Action - Passed : Element with tagname '" +
	         * gettagname + "' was set with '" + sFIELDNAME + "'");
	         */
	        Etest = true;
	      } else {
	        /*
	         * generateReport("failed", "Enter Text Box Action  Error :  Element with tagname - " +
	         * locater + " not was 'present' ");
	         */
	      }
	    } catch (Exception e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, "Eeception : " + e.getMessage());
	       * Assert.assertFalse("Eeception : " + e.getMessage(), false);
	       */
	      throw new Exception("Exception :" + e.getMessage());
	    }
	    return Etest;
	  }

	  // ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	  // Button and click action
	  // ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	  public boolean ClickAction(By locater) throws Exception {
	    boolean cAction = false;
	    try {
	      if (isElementPresent(locater)) {
	        String getvalue = driver.findElement((By) locater).getAttribute("data-qaid");
	        driver.findElement((By) locater).click();
	        /*
	         * generateReport("passed", "Click Action Passed :  Element -'" + getvalue +
	         * "' was 'clicked' ");
	         */
	        cAction = true;
	      } else {
	        /*
	         * generateReport("failed", "Error :  Element with tagname - " + locater +
	         * " was not found ");
	         */
	      }
	    } catch (Exception e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, " Error : " + e.getMessage());
	       * StepDefinitions.test.addScreenCaptureFromPath( System.getProperty("user.dir") + "/" +
	       * captureScreenShot(driver));
	       */
	      throw new Exception(" Exception - " + e.getMessage());
	    }
	    return cAction;
	  }

	  public boolean mouseover(By locter) throws Exception {
	    boolean cAction = false;
	    try {
	      Actions action = new Actions(driver);
	      WebElement we = driver.findElement(locter);
	      action.moveToElement(we).build().perform();
	     // generateReport("passed", "Mouse over Action Passed : ' ");
	      cAction = true;
	    } catch (Exception e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, " Error : " + e.getMessage());
	       * StepDefinitions.test.addScreenCaptureFromPath( System.getProperty("user.dir") + "/" +
	       * captureScreenShot(driver));
	       */
	      throw new Exception(" Exception - " + e.getMessage());
	    }
	    return cAction;
	  }

	  public void waitForPageToLoad() {
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  }

	  public boolean validateDropdownSelection(By locater, String val) throws Exception {
	    boolean cAction = false;
	    String selectedText = "";
	    try {
	      if (isElementPresent(locater)) {
	        String getvalue = driver.findElement(locater).getAttribute("id");

	        Select select = new Select(driver.findElement(locater));
	        if (select.getFirstSelectedOption().isSelected()) {
	          selectedText = select.getFirstSelectedOption().getText();
	        }

	        cAction = selectedText.equals(val);
	        /*
	         * generateReport("passed", "Click Action Passed :  Element -'" + getvalue +
	         * "' was 'clicked' ");
	         */
	      } else {
	       // generateReport("failed", "Error :  Element with tagname - " + locater + " was not found ");
	      }
	    } catch (Exception e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, " Error : " + e.getMessage());
	       * StepDefinitions.test.addScreenCaptureFromPath( System.getProperty("user.dir") + "/" +
	       * captureScreenShot(driver));
	       */
	      throw new Exception(" Exception - " + e.getMessage());
	    }
	    return cAction;
	  }

	  public boolean verifyEnabledData(String locaters, String object) throws Exception {
	    boolean cAction = false;
	    try {
	      By by_EnabledData = By.xpath(locaters + "[(text()='" + object.trim() + "')]");
	      if (isElementPresent(by_EnabledData)) {
	        /*
	         * generateReport("passed", "isElementPresent Passed :  Element -'" + object +
	         * "' was 'exists' ");
	         */
	        cAction = true;
	      } else {
	        //generateReport("failed", "Error :  Element with tagname - " + object + " was not found ");
	      }
	    } catch (Exception e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, " Error : " + e.getMessage());
	       * StepDefinitions.test.addScreenCaptureFromPath( System.getProperty("user.dir") + "/" +
	       * captureScreenShot(driver));
	       */
	      throw new Exception(" Exception - " + e.getMessage());
	    }
	    return cAction;
	  }

	  public boolean ScoreCutoffHighdataverify(String arg1, String arg2) throws Exception {
	    boolean cAction = false;
	    try {
	      By by_EnabledData = By.xpath("//input[@data-qaid='cutoff-high_" + arg1 + "']");
	      if (isElementPresent(by_EnabledData)) {
	        String cutoffvalue = getInputValue(by_EnabledData);
	        cAction = cutoffvalue.toLowerCase().trim().contains(arg2.toLowerCase().trim());
	        //generateReport("passed", "verify cutt off :  value -'" + cutoffvalue + "' was 'exists' ");
	        cAction = true;
	      } else {
	       // generateReport("failed", "Error :  value - " + arg2 + " was not found ");
	      }
	    } catch (Exception e) {
	      /*
	       * StepDefinitions.test.log(Status.FAIL, " Error : " + e.getMessage());
	       * StepDefinitions.test.addScreenCaptureFromPath( System.getProperty("user.dir") + "/" +
	       * captureScreenShot(driver));
	       */
	      throw new Exception(" Exception - " + e.getMessage());
	    }
	    return cAction;

	  }

	  public boolean verifydata(By arg, String data) throws Exception {
	    boolean vboolean = false;
	    String svalue = gettext(arg);
	    if (svalue.toLowerCase().trim().contains(data.toLowerCase().trim())) {
	     // generateReport("passed", "Except :  " + data + ", Actual : " + svalue);
	      vboolean = true;
	    } else {
	      //generateReport("failed", "Except :  " + data + ", Actual : " + svalue);
	    }
	    return vboolean;
	  }

	  public boolean CheckboxSetState(By locator, boolean state) throws Exception {
	    try {
	      if (state && !driver.findElement(locator).isSelected()) {
	        driver.findElement(locator).click();
	      }
	      if (!state && driver.findElement(locator).isSelected()) {
	        driver.findElement(locator).click();
	      }
	      return true;
	    } catch (Exception e) {
	    //  Assert.assertFalse("Exception" + e, false);
	      throw e;
	    }
	  }

	  public boolean CheckBoxunselect(By locator) throws Exception {
	    boolean result = false;
	    try {
	      if (isElementPresent(locator)) {
	        if (driver.findElement(locator).isSelected()) {
	          driver.findElement(locator).click();
	          /*
	           * generateReport("passed", "Checkbox with tagname - " +
	           * driver.findElement(locator).getAttribute("data-qaid") + "was not selected - Passed ");
	           */
	        }
	        if (!driver.findElement(locator).isSelected()) {
	          result = true;
	        }
	      }
	    } catch (NoSuchElementException e) {
	     // Assert.assertFalse("No Such Element Exception" + e, false);
	      throw new NoSuchElementException("No Such Element Exception");
	    } catch (NullPointerException e) {
	     //Assert.assertFalse("Null point Exception" + e, false);
	      throw new NullPointerException("Null point Exception");
	    } catch (Exception e) {
	     // Assert.assertFalse("Exception" + e, false);
	      throw new Exception();
	    }
	    return result;
	  }

	  public boolean CheckBoxunselect(By locator,By slocator) throws Exception {
	    boolean result = false;
	    try {
	      if (isElementPresent(locator)) {
	        if (driver.findElement(locator).isSelected()) {
	          driver.findElement(slocator).click();
	          /*
	           * generateReport("passed", "Checkbox with tagname - " +
	           * driver.findElement(locator).getAttribute("data-qaid") + "was not selected - Passed ");
	           */
	        }
	        if (!driver.findElement(locator).isSelected()) {
	          result = true;
	        }
	      }
	    } catch (NoSuchElementException e) {
	     // Assert.assertFalse("No Such Element Exception" + e, false);
	      throw new NoSuchElementException("No Such Element Exception");
	    } catch (NullPointerException e) {
	      //Assert.assertFalse("Null point Exception" + e, false);
	      throw new NullPointerException("Null point Exception");
	    } catch (Exception e) {
	     // Assert.assertFalse("Exception" + e, false);
	      throw new Exception();
	    }
	    return result;
	  }
	 
	  public boolean VerifyCheckBoxisSeleted(By locator) throws Exception {
	    boolean result = false;
	    try {
	      if (isElementPresent(locator)) {
	        if (driver.findElement(locator).isSelected()) {
	          /*
	           * generateReport("passed", "Checkbox " +
	           * driver.findElement(locator).getAttribute("data-qaid") + " was selected - Passed ");
	           */
	          result = true;
	        }
	      }
	    } catch (NoSuchElementException e) {
	     // Assert.assertFalse("No Such Element Exception" + e, false);
	      throw new NoSuchElementException("No Such Element Exception");
	    } catch (NullPointerException e) {
	     // Assert.assertFalse("Null point Exception" + e, false);
	      throw new NullPointerException("Null point Exception");
	    } catch (Exception e) {
	     // Assert.assertFalse("Exception" + e, false);
	      throw new Exception();
	    }
	    return result;
	  }

	  public boolean VerifyCheckBoxisNotSeleted(By locator) throws Exception {
	    boolean result = false;
	    try {
	      if (isElementPresent(locator)) {
	        if (!driver.findElement(locator).isSelected()) {
	          /*
	           * generateReport("passed", "Checkbox " +
	           * driver.findElement(locator).getAttribute("data-qaid") + " was not selected - Passed ");
	           */
	          result = true;
	        }
	      }
	    } catch (NoSuchElementException e) {
	     // Assert.assertFalse("No Such Element Exception" + e, false);
	      throw new NoSuchElementException("No Such Element Exception");
	    } catch (NullPointerException e) {
	     // Assert.assertFalse("Null point Exception" + e, false);
	      throw new NullPointerException("Null point Exception");
	    } catch (Exception e) {
	     // Assert.assertFalse("Exception" + e, false);
	      throw new Exception();
	    }
	    return result;
	  }

	  public boolean clicktable(String loc) throws Exception {
	    WebElement TogetRows = driver
	        .findElement(By.xpath(loc + "/tbody/tr[1]/td[8]/div[@data-qaid='report-view-button']"));
	    TogetRows.click();
	    Thread.sleep(1000);
	    return true;
	  }
	  
	  public static void drawBroder(WebElement element, WebDriver driver){
		  JavascriptExecutor jse = ((JavascriptExecutor)driver);
		  jse.executeScript("arguments[0].style.border='3px solid red'",element);
		  }



		  public static void clickelement(WebElement element, WebDriver driver){
		  JavascriptExecutor jse = ((JavascriptExecutor)driver);
		  jse.executeScript("arguments[0].click();",element);
		  }

		  public static void refreshBrowserByjs(WebDriver driver){
		  JavascriptExecutor jse = ((JavascriptExecutor)driver);
		  jse.executeScript("history.go(0)");
		  }

		  public static String grttitleByjs(WebDriver driver){
		  JavascriptExecutor jse = ((JavascriptExecutor)driver);
		  return (String) jse.executeScript("return document.title;");
		  }

		  public static String getpageInnerTextByjs(WebDriver driver){
		  JavascriptExecutor jse = ((JavascriptExecutor)driver);
		  return (String) jse.executeScript("return document.documentElement.innerText;");
		  }

		  public static void scrollPageDownByjs(WebDriver driver){
		  JavascriptExecutor jse = ((JavascriptExecutor)driver);
		  jse.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		  }

		  public static void scrollintoviewByjs(WebElement element, WebDriver driver){
		  JavascriptExecutor jse = ((JavascriptExecutor)driver);
		  jse.executeScript("arguments[0].scrollIntoview(true);",element);
		  }


		  /** @param element to scroll into view */
		    public void scrollIntoView(WebElement element) {
		      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		    }

		   /**
		     * @param element to set the value from
		     * @param value we want to set for the element
		     */
		    public void jsSetValue(WebElement element, String value) {
		      JavascriptExecutor js = (JavascriptExecutor) driver;
		      js.executeScript(String.format("arguments[0].value='%s';", value), element);
		    }


		  /** @param element that we want to clear the value from */
		    public void jsClear(WebElement element) {
		      JavascriptExecutor js = (JavascriptExecutor) driver;
		      js.executeScript("arguments[0].value = '';", element);
		    }

		    /**
		     * @param element attempts to click on element normally but if it fails we click on it with
		     *     JavascriptExecutor
		     */
		    public void click(WebElement element) {
		      JavascriptExecutor jse = (JavascriptExecutor) driver;
		      try {
		        element.click();
		      } catch (Exception e) {
		        jse.executeScript("arguments[0].click();", element);
		      }
		    }

		    /**
		     * @param elems list of elements to click on
		     * @param index index to start clicking from
		     * @param amount how many times to click on elements Sequentially
		     */
		    public void clickSequentially(List<WebElement> elems, Integer index, Integer amount) {
		      int clicked = 0;

		      while (clicked < amount) {
		        elems.get(index).click();
		        clicked++;
		        index++;
		      }
		    }

		    /**
		     * @param element element that we want to click on
		     * @param amount how many times to click on element
		     */
		    public void clickMultiple(WebElement element, Integer amount) {
		      int clicked = 0;

		      while (clicked < amount) {
		        element.click();
		        clicked++;
		      }
		    }
	}

