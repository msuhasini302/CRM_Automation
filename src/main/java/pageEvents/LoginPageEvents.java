package pageEvents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import pageObject.LoginPageElements;
import utils.ElementFetch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPageEvents {
	ElementFetch ele = new ElementFetch();
	private static final Logger logger = LoggerFactory.getLogger(LoginPageEvents.class);

	public void verfiyIfLoginPageLoaded() {
		Assert.assertTrue(ele.getWebElements("XPATH", LoginPageElements.loginText).size() > 0, "Element not found");
		logger.info("Login page loaded successfully.");
	}

	public void verfiyIfLoginPageNotLoaded() {
		Assert.assertTrue(ele.getWebElements("XPATH", LoginPageElements.loginText).size() == 0, "Element not found");
		logger.info("Login page not loaded successfully.");
	}

	public Map<String, String>[][] getDataFromExcel() throws IOException {
		String excelFilePath = "C:\\Users\\SuhasiniManuneethi\\OneDrive - Kadel Labs Private Limited\\Desktop\\qa_automation\\testData\\CRM_TestData.xlsx";
		FileInputStream inputStream = new FileInputStream(excelFilePath);

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheet("Sheet1");

		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

		List<Map<String, String>> data = new ArrayList<>();

		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			Map<String, String> testData = new HashMap<>();

			for (int j = 0; j < colCount; j++) {
				String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
				String cellValue = row.getCell(j).getStringCellValue();
				testData.put(columnName, cellValue);
			}

			data.add(testData);
		}

		workbook.close();
		inputStream.close();

		Map<String, String>[][] dataArray = new Map[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			dataArray[i][0] = data.get(i);
		}

		return dataArray;
	}

	public void enterCredentials(Map<String, String> testData) throws IOException {
		String username = testData.get("username");
		String password = testData.get("password");

		ele.getWebElement("XPATH", LoginPageElements.emailAddress).sendKeys(username);
		ele.getWebElement("XPATH", LoginPageElements.passwordField).sendKeys(password);

		System.out.println("Entering credentials - Username: " + username + ", Password: " + password);
		logger.info("Entering credentials - Username: {}, Password: {}", username, password);
	}
}

