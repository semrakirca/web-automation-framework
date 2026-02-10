package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Raporu tek yerden yöneten sınıf
public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {

            // Raporun nereye yazılacağını belirler
            ExtentSparkReporter reporter =
                    new ExtentSparkReporter("target/extent-report.html");

            reporter.config().setReportName("Web Automation Report");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}
