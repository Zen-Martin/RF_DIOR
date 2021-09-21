package com.dior.pageObjects;

import com.dior.config.Configuration;
import com.dior.config.Properties;
import com.dior.utils.ExcelManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Page {

    @FindBy(className = "ot-sdk-container")
    private WebElement cookiesFrame;

    @FindBy(id = "onetrust-reject-all-handler")
    private WebElement cookiesSelectOption;

    @FindBy(className = "popin ignore-use-click-outside")
    private WebElement accessFrame;

    @FindBy(className = "popin__wrapper__close")
    private WebElement accessSelectOption;

    /***
     *
     */
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions action;
    /***
     * waiter
     */
    protected WebDriverWait wait;
    protected WebDriverWait shortWait;
    protected WebDriverWait middleWait;
    protected WebDriverWait longWait;

    protected Configuration config = Properties.Config;

    private ExcelManager excel;

    Page(){
        // Init
        driver = Properties.DriverManager.getDriver();
        PageFactory.initElements(driver, this);

        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
        excel = new ExcelManager();

        //Waiter
        wait        = new WebDriverWait(driver, Duration.ofSeconds(4));
        shortWait   = new WebDriverWait(driver, Duration.ofSeconds(6));
        middleWait  = new WebDriverWait(driver, Duration.ofSeconds(12));
        longWait    = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    protected <V>boolean waitUntil(Function<? super WebDriver, V> isTrue){
        try{
            wait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    protected <V>boolean shortUntil(Function<? super WebDriver, V> isTrue){
        try{
            shortWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /***
     *
     * @param isTrue
     * @param <V>
     * @return
     */
    protected <V>boolean middleUntil(Function<? super WebDriver, V> isTrue){
        try{
            middleWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /***
     *
     * @param isTrue
     * @param <V>
     * @return
     */
    protected <V>boolean longUntil(Function<? super WebDriver, V> isTrue){
        try{
            longWait.until(isTrue);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /***
     *
     */
    protected void waitForLoadingPage( ){
        if(!longUntil(driver->js.executeScript("return document.readyState").equals("complete") || js.executeScript("return document.readyState").equals("interactive") )){
            throw new RuntimeException(driver.getCurrentUrl()+" not loaded");
        }
    }
    /***
     *
     * @param url
     */
    protected void get(String url){
        driver.get(url);
        waitForLoadingPage();
    }

    protected void clickOn(WebElement element){

        if( !shortUntil(visibilityOf(element)) ){
            // Logger
            throw new RuntimeException("Element not visible after click");
        }

        if( !shortUntil(elementToBeClickable(element))){
            // Logger
            throw new RuntimeException("Element not clickable");
        }
        element.click();
    }

    public void handleCookie(){
        if(shortUntil(visibilityOf(cookiesFrame))) clickOn(cookiesSelectOption);
    }

    public void handleFrame(){
        if(shortUntil(visibilityOf(accessSelectOption))) clickOn(accessSelectOption);
    }

    public void manageAccess(){
        handleCookie();
        handleFrame();
        scroll(10);
        }


    public void refresh_page(){
        driver.navigate().refresh();
        waitForLoadingPage();
        manageAccess();

    }

    public void goToLinkpage(WebElement link){
        driver.navigate().to(link.getAttribute("href"));
        waitForLoadingPage();
    }

    public void goTopage(String link){
        driver.navigate().to(link);
        manageAccess();
    }

    protected void scroll(int height){
        js.executeScript("window.scrollBy(0,"+height+")");
    }


    public String getScrollPosition( ){
        return (js.executeScript("return window.pageYOffset;").toString());
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static void saveScreenShotPNG(){
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) Properties.DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES)));
    }
}
