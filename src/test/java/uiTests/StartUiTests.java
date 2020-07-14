package uiTests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestBase;

public class StartUiTests extends TestBase {

    @BeforeMethod
    @Step("Инициализация драйвера")
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    @Step("Закрытие браузера")
    public void tearDown(ITestResult result) {
        if (result.getStatus() == 2) {
            takeScreenshot();
        }
        driver.quit();
    }

    @Test
    @Description("Проверка редиректа")
    public void сheckRedirect() {
        getDriver().get(properties.getProperty("urlFirst"));

        Assert.assertEquals(getDriver().getCurrentUrl(), properties.getProperty("urlSecond"));
        Assert.assertEquals(getDriver().getTitle(), properties.getProperty("title"), "Заголовок страницы отличен от ожидаемого");
    }
}
