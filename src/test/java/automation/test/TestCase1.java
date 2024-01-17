package automation.test;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.runner;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import utils.ElementFetch;
import java.io.File;

public class TestCase1 extends runner {
	ElementFetch ele = new ElementFetch();
	HomePageEvents homePage = new HomePageEvents();
	LoginPageEvents dataProvider = new LoginPageEvents();

	@BeforeTest
	public void beforeTest() {
		sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + File.separator + "reports" + File.separator + "extentreport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.STANDARD);
		extent.setSystemInfo("HostName", "Admin");
		extent.setSystemInfo("UserName", "QA");
		sparkReporter.config().setDocumentTitle("QA Report");
		sparkReporter.config().setReportName("QA Automation Report");
	}

	@Test
	public void enteringCredentials(@Optional("chrome") String browser) throws IOException {

		Map<String, String>[][] testDataArray = dataProvider.getDataFromExcel();

		for (Map<String, String>[] testData : testDataArray) {
			new LoginPageEvents().enterCredentials(testData[0]);
			extentlogger.info("SingIn into LoginPage");
			homePage.singInButton();
			extentlogger.info("Verfing if Login text is present or not");
			dataProvider.verfiyIfLoginPageLoaded();
			extentlogger.info("Verfing if Enter Credentials");
			dataProvider.enterCredentials(null);
		}
	}

	@Test
	public void notEnteringCredentials(@Optional("chrome") String browser) throws IOException {

		Map<String, String>[][] testDataArray = dataProvider.getDataFromExcel();

		for (Map<String, String>[] testData : testDataArray) {
			new LoginPageEvents().enterCredentials(testData[0]);
			extentlogger.info("SingIn into LogonPage");
			homePage.singInButton();
			extentlogger.info("Verfing if Login text is present or not");
			dataProvider.verfiyIfLoginPageNotLoaded();
			extentlogger.info("Verfing if Enter Credentials");
			dataProvider.enterCredentials(null);
		}
	}
}


