package com.duyj.selenium.ecspage;

import com.duyj.selenium.BrowserEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/10/23
 */
public class EcsPageTest {
    WebDriver driver;

    @Before
    public void setUp() throws IOException {
        BrowserEngine browserEngine = new BrowserEngine();
        browserEngine.initConfigData("http://192.168.2.172:8080/ecs-console/index.html#/login");
        driver = browserEngine.getBrowser();
    }

    //如果需要看清楚结果，把@AfterClass注销就好
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void login() {
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login("duyj", "999999");
    }
}