package PagePackage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import BasePackage.ExcelUtility;

public class GetPricesDetails {
	
	WebDriver driver ;
	By studentButton = By.xpath("//a[.='Student']");
	By dest = By.xpath("//input[@id='destination-autocomplete']");
	By memage1 = By.xpath("//input[@id='memage1']");
	By memage2 = By.xpath("//input[@id='memage2']");
	By startBox = By.xpath("//input[@class='date_at']");
	By endBox = By.xpath("//input[@class='date_to']");
	By yearSelect = By.xpath("//div[@class='drp-calendar left single']//select[@class='yearselect']");
	By MonthSelect = By.xpath("//div[@class='calendar-table']//select[@class='monthselect']");
	By calendarstartRows = By.xpath("//*[@id=\"navigatorType\"]/body/div[6]/div[2]/div[1]/table/tbody/tr");
	By calendarEndRows = By.xpath("//*[@id=\"navigatorType\"]/body/div[7]/div[2]/div[1]/table/tbody/tr");
	By proceedButton = By.xpath("//a[@class='proceedButton']");
	By gender = By.id("travelgender");
	By travelName = By.id("travelname");
	By travelmobile = By.id("travelmobile");
	By travelemail = By.id("travelemail");
	By getQuotes = By.xpath("//a[@class='proceedButton travelproceed']");
	By lowtohigh = By.xpath("//*[@name='SI']");
	Map<Integer,String> map = new HashMap<Integer,String>();
	ExcelUtility eu = new ExcelUtility();
	
	
	
	
    public GetPricesDetails(WebDriver driver){ //constructor with single parameter

        this.driver = driver;

    }
	
    public void enterTravelDetails() throws InterruptedException
    {
    	driver.findElement(studentButton).click();
		
		WebElement e = driver.findElement(dest);
		e.sendKeys("germany");
		Thread.sleep(2000);
		e.sendKeys(Keys.ENTER);;
		
		driver.findElement(memage1).sendKeys("21");
		driver.findElement(memage2).sendKeys("22");
		driver.findElement(startBox).click();
		Select sel1 = new Select(driver.findElement(yearSelect));
		sel1.selectByVisibleText("2020");
		Select sel = new Select(driver.findElement(MonthSelect));
		sel.selectByVisibleText("Sep");

		
		List<WebElement> lis = driver.findElements(calendarstartRows);
		int rows = lis.size();
		
		for(int i=1;i<=rows;i++)
		{
			List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"navigatorType\"]/body/div[6]/div[2]/div[1]/table/tbody/tr["+i+"]/td"));
			int cols = list.size();
			for(int j=1;j<=cols;j++)
			{
				WebElement text = driver.findElement(By.xpath("//*[@id=\"navigatorType\"]/body/div[6]/div[2]/div[1]/table/tbody/tr["+i+"]/td["+j+"]"));
				if(text.getText().equals("8")) {
					text.click();
					break;
				}
			}
		}
//		driver.findElement(By.xpath("//input[@class='date_to']")).click();
		Select sel3 = new Select(driver.findElement(yearSelect));
		sel3.selectByVisibleText("2020");
		Select sel2 = new Select(driver.findElement(MonthSelect));
		sel2.selectByVisibleText("Sep");

		
		List<WebElement> lis1 = driver.findElements(calendarEndRows);
		int rows1 = lis1.size();
		
		for(int i=1;i<=rows1;i++)
		{
			List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"navigatorType\"]/body/div[7]/div[2]/div[1]/table/tbody/tr["+i+"]/td"));
			int cols = list.size();
			for(int j=1;j<=cols;j++)
			{
				WebElement text = driver.findElement(By.xpath("//*[@id=\"navigatorType\"]/body/div[7]/div[2]/div[1]/table/tbody/tr["+i+"]/td["+j+"]"));
				if(text.getText().equals("15")) {
					text.click();
					break;
				}
			}
		}
		
		driver.findElement(proceedButton).click();
		Thread.sleep(4000);
		
    }
    
    public void enterPersonDetails() throws InterruptedException
    {
		Select s = new Select(driver.findElement(gender));
		s.selectByVisibleText("Mr.");
		
		driver.findElement(travelName).sendKeys("test");
		driver.findElement(travelmobile).sendKeys("9123491234");
		driver.findElement(travelemail).sendKeys("dummymail@gmail.com");
		driver.findElement(getQuotes).click(); 	
		Thread.sleep(10000);
    }
    
    public void getLowestPrices() throws InterruptedException
    {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(lowtohigh));
		Select s1 = new Select(driver.findElement(lowtohigh));
		s1.selectByVisibleText("Low to High");
		Thread.sleep(1000);
		
		for(int i=1;i<=3;i++)
		{
			String t = driver.findElement(By.xpath("//*[@class='show_quote']/div["+i+"]//span[@class='ng-binding ng-scope']")).getText();
			System.out.println(t);
			map.put(i, t);
		}
		map.put(4, "Result success");
		eu.writeToExcel(map);
    }

}
