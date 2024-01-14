package pageEvents;

import pageObject.HomePageElements;
import utils.ElementFetch;

public class HomePageEvents {
ElementFetch ele = new ElementFetch();
	
	public void singInButton() {
		
		ele.getWebElement("XPATH", HomePageElements.singInButtonText).click();
		
	}
}
