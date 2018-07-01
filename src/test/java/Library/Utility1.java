package Library;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility1 {

		public static void getScreenshot(WebDriver driver, String ScreenshotName ) throws IOException
		{

			//Creating Object for Date
				Date date = new Date();
				//Creating object for time stamp
				Timestamp timestamp= new Timestamp(date.getTime());
				//converting date format to String
				String time=timestamp.toString();
				//replacing all spaces with '-'
				time = time.replace(' ', '-');
				//replacing all colons with '-'
				time = time.replace(':', '-'); 
				
				//Capturing screenshot as File output type 
				File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				//copying screenshot to local machine directory
				FileUtils.copyFile(screen, new File(System.getProperty("user.dir")+"\\Screenshots\\"+"_"+ScreenshotName+"_"+time+"_"+"_"+".jpg"));
		}
}