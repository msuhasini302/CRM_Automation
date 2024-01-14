package utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import baseTest.runner;

public class ElementFetch {
	public WebElement getWebElement(String indentifierType, String identifierValue) 
	{
		switch(indentifierType) {
		case "XPATH":
		return runner.driver.findElement(By.xpath(identifierValue));
		case "ID":
		return runner.driver.findElement(By.id(identifierValue));
		case "Name":
			return runner.driver.findElement(By.name(identifierValue));
		
		default:
			return null;
		}
	}
	public List<WebElement> getWebElements(String indentifierType, String identifierValue) 
	{
		switch(indentifierType) {
		case "XPATH":
		return runner.driver.findElements(By.xpath(identifierValue));
		case "ID":
		return runner.driver.findElements(By.id(identifierValue));
		case "Name":
			return runner.driver.findElements(By.name(identifierValue));
		
		default:
			return null;
		}
	}

}
