package tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Failure anında screenshot alan yardımcı sınıf
public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {

        // Zaman damgası (dosya çakışmasın diye)
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String path = "target/screenshots/" + testName + "_" + timestamp + ".png";

        try {
            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File destination = new File(path);
            destination.getParentFile().mkdirs();

            Files.copy(source.toPath(), destination.toPath());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
