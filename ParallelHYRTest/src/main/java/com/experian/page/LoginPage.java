package com.experian.page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.experian.util.BasePage;
public class LoginPage extends BasePage{

private WebDriver driver;

// 2. Constructor of the page class:
public LoginPage(WebDriver driver) {
 super(driver);
}
private static final By by_submit = By.xpath("//input[@id='okta-signin-submit']");
 private static final By by_username = By.xpath("//input[@id='okta-signin-username']");
 private static final By by_password = By.xpath("//input[@id='okta-signin-password']");
 // private static final By by_Question = By.xpath("//input[@id='input11']");
 private static final By by_Question = By.xpath("//input[@id='input80']");
 private static final By by_verify = By.xpath("//input[@value='Verify']");
 // private static final By by_header = By.xpath("//*[@data-initials='AE']");
 private static final By by_header = By.xpath("//div[@data-qaid='topnav-avatar']");
 private static final By by_logout = By.xpath("//button[@data-qaid='profile-logout']");

 public boolean submitbutton() throws Exception {
   return ClickAction(by_submit);
 }

 public boolean verifybutton() throws Exception {
   return ClickAction(by_verify);
 }

 public boolean username(String arg1) throws Exception {
   return EnterTextAction(by_username, arg1);
 }

 public boolean password(String arg1) throws Exception {
   return EnterTextAction(by_password, arg1);
 }

 public boolean question(String arg1) throws Exception {
   Thread.sleep(1000);
   return EnterTextAction(by_Question, arg1);
 }

 public boolean headerbutton() throws Exception {
   return mouseover(by_header);
 }

 public boolean logout() throws Exception {
   Thread.sleep(10);
   return ClickAction(by_logout);
 }


}
