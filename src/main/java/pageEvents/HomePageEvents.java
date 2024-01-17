package pageEvents;

import pageObject.HomePageElements;
import utils.ElementFetch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePageEvents {
	private static final Logger logger = LoggerFactory.getLogger(HomePageEvents.class);
	ElementFetch ele = new ElementFetch();

	public void singInButton() {

		ele.getWebElement("XPATH", HomePageElements.singInButtonText).click();
		logger.info("Clicked on Sign In button.");
	}
}
