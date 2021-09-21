package com.dior.runners;

import com.dior.config.Properties;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;

public class BaseRunner extends AbstractTestNGCucumberTests {


    @Parameters({"browser", "device"})
    @BeforeClass
    public static void beforeTest(@Optional String browser, @Optional String device){
        String browserA;

        browserA = java.util.Optional
                .ofNullable(browser)
                .orElse(Properties.Config.getBrowser());

        Properties.DriverManager.setDriver(browserA);
    }

    @AfterClass
    public static void tearDown(){
        Properties.DriverManager.getDriver().quit();
    }

}
