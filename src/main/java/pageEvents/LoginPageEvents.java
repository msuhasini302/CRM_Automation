package pageEvents;

import org.testng.Assert;

import pageObject.LoginPageElements;
import utils.ElementFetch;

public class LoginPageEvents {
	ElementFetch ele = new ElementFetch();
public void verfiyIfLoginPageLoaded() {
	Assert.assertTrue(ele.getWebElements("XPATH", LoginPageElements.loginText ).size()>0,"Element not found");
}
public void verfiyIfLoginPageNotLoaded() {
	Assert.assertTrue(ele.getWebElements("XPATH", LoginPageElements.loginText ).size()==0,"Element not found");
}
public void enterCredentials() {
	ele.getWebElement("XPATH", LoginPageElements.emailAddress).sendKeys("Admin");
	ele.getWebElement("XPATH", LoginPageElements.passwordField).sendKeys("password");
}
}
