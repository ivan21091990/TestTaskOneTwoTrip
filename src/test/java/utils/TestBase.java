package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public abstract class TestBase {

    protected static Properties properties = TestProperties.getInstance().getProperties();
    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Attachment(type = "image/png", value = "Screenshot")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
