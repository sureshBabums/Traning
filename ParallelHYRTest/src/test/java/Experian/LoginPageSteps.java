package Experian;

import org.junit.Assert;

import com.experian.factory.DriverFactory;
import com.experian.page.FraudAdminPage;
import com.experian.page.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class LoginPageSteps {

private LoginPage LoginPage = new LoginPage(DriverFactory.getDriver());
private FraudAdminPage FraudAdminPage = new FraudAdminPage(DriverFactory.getDriver());

    /*
     * @Given("user is on login page") public void user_is_on_login_page() {
     *
     * DriverFactory.getDriver()
     * .get("http://automationpractice.com/index.php?controller=authentication&back=my-account"); }
     */
    @Given("^User navigates to FraudUI page$")
    public void typeMyeMailAndPassFor() {
      Assert.assertTrue("Failed to Launch : " + " URL",
          FraudAdminPage.navigateToHomePage("https://www.google.com/"));
    }

    @Then("^Enter user name$")
    public void Enterusername() throws Exception {
      //Assert.assertTrue("Failed to Enter : " + " user name", LoginPage.username(FraudAdminPage.getUSerID()));
    }

    @Then("^Enter user password$")
    public void Enteruserpassword() throws Exception {
      Assert.assertTrue("Failed to Enter : " + " password", LoginPage.password(
          FraudAdminPage.getPassword()));
    }

    @Then("^Enter user question")
    public void Enteruserquestion() throws Exception {
      Assert.assertTrue("Failed to Enter : " + " question answer", LoginPage.question(
          FraudAdminPage.getQuestion()));
    }

    @Then("^Click on submit button$")
    public void clickonsumbit() throws Exception {
      Assert.assertTrue("Failed to Click : " + " sumbit button", LoginPage.submitbutton());
    }

    @Then("^Click on verify button$")
    public void clickonverify() throws Exception {
      Assert.assertTrue("Failed to Click : " + " verify button", LoginPage.verifybutton());
    }

    @Then("^Click on profile button$")
    public void mouseover() throws Exception {
      Assert.assertTrue("Failed to mouseover : " + " profile", LoginPage.headerbutton());
    }

    @Then("^Select Subcode (.*)$")
    public void SelectSubcode(String subcode) throws Exception {
     // Assert.assertTrue("Failed to Select Subcode : " + subcode, Modifypage.Selectsubcode(subcode));
    }


}
