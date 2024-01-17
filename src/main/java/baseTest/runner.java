package baseTest;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
//import java.util.logging.LogManager;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.Parameter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;
import utils.WebDriverFactory;

public class runner {
	public static WebDriver driver;
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(runner.class);
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest extentlogger;

	@BeforeTest
	public void beforeTestMethod() {
		sparkReporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + File.separator + "reports" + File.separator + "extentreport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.STANDARD);
		extent.setSystemInfo("HostName", "Admin");
		extent.setSystemInfo("UserName", "QA");
		sparkReporter.config().setDocumentTitle("QA Report");
		sparkReporter.config().setReportName("QA Automation Report");
		logger.info("WebDriver setup and navigation to URL");
	}

	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser, Method testMethod) {
		extentlogger = extent.createTest(testMethod.getName());
		setupDriver(browser);
		driver.manage().window().maximize();
		driver.get(Constants.url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentlogger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			extentlogger.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			logger.error("Test Case Failed: {}", result.getName(), result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentlogger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped ", ExtentColor.ORANGE));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentlogger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " - Test Case PASS", ExtentColor.GREEN));
		}
		logger.info("Test Case Passed: {}", result.getName());

		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		extent.flush();
		logger.info("Extent report flushed.");

	}

	public void setupDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		logger.info("WebDriver setup for {} browser.", browser);
	}

}
