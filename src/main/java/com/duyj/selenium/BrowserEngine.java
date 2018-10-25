package com.duyj.selenium;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/10/22
 */

import com.duyj.log.LogType;
import com.duyj.log.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrowserEngine {

    public String serverURL;
    public WebDriver driver;

    public void initConfigData(String serverURL) throws IOException {
        this.serverURL = serverURL;
        Logger.Output(LogType.LogTypeName.INFO, "Start to select browser name from properties file");
        Logger.Output(LogType.LogTypeName.INFO, "The test server URL is: " + serverURL);
    }

    public WebDriver getBrowser() {
        System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
        driver = new ChromeDriver();
        Logger.Output(LogType.LogTypeName.INFO, "Launching Chrome ...");
        driver.get(serverURL);
        Logger.Output(LogType.LogTypeName.INFO, "Open URL: " + serverURL);
        driver.manage().window().maximize();
        Logger.Output(LogType.LogTypeName.INFO, "Maximize browser...");
        callWait(3);
        return driver;
    }

    /*
     * 关闭浏览器并退出方法
     */

    public void tearDown() throws InterruptedException {

        Logger.Output(LogType.LogTypeName.INFO, "Closing browser...");
        driver.quit();
        Thread.sleep(3000);
    }

    /*
     * 隐式时间等待方法
     */
    public void callWait(int time) {

        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        Logger.Output(LogType.LogTypeName.INFO, "Wait for " + time + " seconds.");
    }
}

