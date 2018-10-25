package com.duyj.selenium.ecspage;

import com.duyj.selenium.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * <description>
 *
 * @author 杜永军
 * @date 2018/10/23
 */
public class LoginPage extends BasePage {
    @FindBy(id = "userName")
    WebElement userInput;
    @FindBy(id = "password")
    WebElement passwordInput;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String name, String passwordInput) {
        type(userInput, name);
        type(this.passwordInput, passwordInput);
        click(loginButton);
    }
}
