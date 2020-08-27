package PagePackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TravelInsurancePage {
	
	WebDriver driver ;
	By otherIns = By.xpath("//a[@href='https://www.policybazaar.com/other-insurance/']");
	By travelIns = By.xpath("//a[@href='https://www.policybazaar.com/travel-insurance/']");
	
    public TravelInsurancePage(WebDriver driver){

        this.driver = driver;

    }
    
    public void selectTravelIns()
    {
    	Actions action = new Actions(driver);
    	WebElement element = driver.findElement(otherIns);
		action.moveToElement(element).perform();
		WebElement ele = driver.findElement(travelIns);
		ele.click();
    }

}
