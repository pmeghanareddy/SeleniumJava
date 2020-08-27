package BasePackage;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {

	WebDriver driver;
	
	public Screenshots(WebDriver driver)
	{
		this.driver=driver;
	}
	public void takeSnapShot(String path1)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File Source=ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(Source, new File(path1));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
