package tests;

import org.testng.annotations.Test;

// BaseTest'i extend ediyoruz
public class FirstTest extends BaseTest {

    @Test
    public void openGoogleTest() {

        driver.get("https://www.google.com");
    }
}
