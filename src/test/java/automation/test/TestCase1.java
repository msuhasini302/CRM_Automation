package automation.test;

import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import baseTest.runner;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import utils.ElementFetch;



public class TestCase1 extends runner{
	ElementFetch ele = new ElementFetch();
	HomePageEvents homePage = new HomePageEvents();
	LoginPageEvents loginPage = new LoginPageEvents();

	@Test
	public void enteringCredentials (@Optional("chrome") String browser) {
		logger.info("SingIn into LoginPage");
		homePage.singInButton();
		logger.info("Verfing if Login text is present or not");
		loginPage.verfiyIfLoginPageLoaded();
		logger.info("Verfing if Enter Credentials");
		loginPage.enterCredentials();
	}
	@Test
	public void notEnteringCredentials (@Optional("chrome") String browser) {
		logger.info("SingIn into LogonPage");
		homePage.singInButton();
		logger.info("Verfing if Login text is present or not");
		loginPage.verfiyIfLoginPageNotLoaded();
		logger.info("Verfing if Enter Credentials");
		loginPage.enterCredentials();
	}
	
}
