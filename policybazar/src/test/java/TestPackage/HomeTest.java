
package TestPackage;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import BasePackage.Screenshots;
import BasePackage.TestBase;
import PagePackage.GetPricesDetails;
import PagePackage.TravelInsurancePage;

public class HomeTest extends TestBase{//inheritance
	
	//pages objects
	
	GetPricesDetails gpd;
	TravelInsurancePage tip;
	
	//public HomeTest(){
		//super();
		
	//}

	@Parameters("browser")
	@BeforeClass
	public void setUp(@Optional("name")String browser) throws InterruptedException {
	
		initialization();
	
	}
	
	@Test(priority=1)
	public void Test1() throws InterruptedException{
		test=report.startTest("Test1");
		test.log(LogStatus.INFO, "Test Started"+test.getStartedTime());
		tip = new TravelInsurancePage(driver);
		tip.selectTravelIns();
		gpd = new GetPricesDetails(driver);
		gpd.enterTravelDetails();
		gpd.enterPersonDetails();
		gpd.getLowestPrices();
		
		
		
	}
	
	@AfterClass
	public void close() throws InterruptedException
	{
		Screenshots util = new Screenshots(driver);
		util.takeSnapShot("E:\\Selenium\\Ebaysearchproduct\\src\\screenshot\\ebay.png");
		
		driver.close();
	}
}
