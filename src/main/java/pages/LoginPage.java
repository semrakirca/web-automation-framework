package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


/**
 * Bu class login sayfasını temsil eder.
 * Testler SAYFA DETAYI BİLMEZ, sadece buradaki methodları kullanır.
 */
public class LoginPage {

    // Bu sayfada kullanılacak driver
    private WebDriver driver;

    // Constructor: testten driver alır
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ===== LOCATOR'LAR =====

    // Username input alanı
    private By usernameInput = By.id("username");

    // Password input alanı
    private By passwordInput = By.id("password");

    // Login butonu
    private By loginButton = By.cssSelector("button[type='submit']");

    // Başarılı / başarısız mesaj alanı
    private By message = By.id("flash");

    // ===== ACTION METHODLARI =====

    // Username girer
    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    // Password girer
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    // Login butonuna tıklar
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Login işlemini tek adımda yapan method
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Ekrandaki mesajı döner
    public String getMessageText() {

        // Mesaj görünür olana kadar max 5 saniye bekler
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(message));

        return driver.findElement(message).getText();
    }

}
