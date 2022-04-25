package PagesSauceDemo;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotBase {
    WebDriver driver;
        //public WebDriver driver;
        public void takescreenshot(WebDriver driver, String path)throws IOException {

            File sourcefilename = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourcefilename, new File("path"));
        }
    }

