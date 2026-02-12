package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import org.testng.annotations.DataProvider;

/**
 * Login senaryolarını test eder
 */
public class LoginTest extends BaseTest {

    @Test
    public void successfulLoginTest() {

        test = extent.createTest("Successful Login Test");

        // 1️⃣ Test edilecek sayfaya git
        getDriver().get(ConfigReader.get("baseUrl") + "/login");

        // 2️⃣ LoginPage nesnesi oluştur
        LoginPage loginPage = new LoginPage(getDriver());

        // 3️⃣ Doğru bilgilerle login ol
        loginPage.login("tomsmith", "SuperSecretPassword!");

        // 4️⃣ Sonucu doğrula (ASSERTION)
        String message = loginPage.getMessageText();

        // Başarılı login mesajını kontrol et
        Assert.assertTrue(
                message.contains("You logged into a secure area!"),
                "Başarılı login mesajı görünmedi!"
        );
    }

    @Test
    public void invalidPasswordLoginTest() {

        test = extent.createTest("Successful Login Test");
        test = extent.createTest("Invalid Password Login Test");

        // 1️⃣ Login sayfasına git
        getDriver().get("https://the-internet.herokuapp.com/login");

        // 2️⃣ LoginPage oluştur
        LoginPage loginPage = new LoginPage(getDriver());

        // 3️⃣ Yanlış şifre ile login dene
        loginPage.login("tomsmith", "WRONG_PASSWORD");

        // 4️⃣ Hata mesajını al
        String message = loginPage.getMessageText();

        // 5️⃣ Hata mesajını doğrula
        Assert.assertTrue(
                message.contains("Your password is invalid"),
                "Yanlış şifre uyarısı görünmedi!"
        );
    }
    @DataProvider(name = "loginData")
    public Object[][] loginData() {

        return new Object[][]{
                {"tomsmith", "SuperSecretPassword!", true},
                {"tomsmith", "WRONG_PASSWORD", false},
                {"wronguser", "SuperSecretPassword!", false},
                {"", "SuperSecretPassword!", false},
                {"tomsmith", "", false}
        };
    }

    @Test (dataProvider = "loginData")
    public void loginTest(String username, String password, boolean shouldSucceed) {
        getDriver().get(ConfigReader.get("baseUrl") + "/login");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        String message = loginPage.getMessageText();

        if(shouldSucceed) {
            Assert.assertTrue(message.contains("You logged into a secure area!"));
        } else {
            Assert.assertTrue(message.contains("invalid"));
        }
    }


}
