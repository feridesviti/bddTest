package com;

import com.helper.WebDriverLogger;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    public EventFiringWebDriver driver;
    private WebDriverLogger webDriverLogger;
    private int implicitlyWait = 30;

    public EventFiringWebDriver setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        webDriverLogger = new WebDriverLogger();
        driver.register(webDriverLogger);
        return driver;
    }

    public void after() {
        driver.unregister(webDriverLogger);
        driver.quit();
    }
}
