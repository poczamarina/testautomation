package hu.masterfield.layout;

import com.galenframework.api.Galen;
import com.galenframework.junit.GalenJUnitTestBase;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.TestReport;
import com.galenframework.reports.model.LayoutReport;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class BBQLayoutTest extends GalenJUnitTestBase {
    static WebDriver driver;
    static WebDriverWait wait;
    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        LayoutTest test = new LayoutTest();
//        test.createDriver();
    }
    @Test
    @DisplayName("TC1 - Tesco BBQ")
    public void TC2() throws IOException {
        driver.get("https://bevasarlas.tesco.hu/groceries/hu-HU/zone/bbq/");
        WebElement header = wait.until(driver -> driver.findElement(By.className("tile__image-container")));
        layoutReport = Galen.checkLayout(driver, "/specs/bbqLayout.gspec", Arrays.asList(new String[]{"desktop"}));
    }
    @AfterAll
    public static void cleanup() {
        reportUpdate();
        driver.quit();
    }

    @Override
    public WebDriver createDriver() {
        super.driver.set(driver);
        super.report.set(new TestReport());
        return driver;
    }
    static LayoutReport layoutReport;
    public static void reportUpdate() {
        try {
            List<GalenTestInfo> tests = new LinkedList<>();
            GalenTestInfo test = GalenTestInfo.fromString("Tesco BBQ Page check");
            test.getReport().layout(layoutReport, "Verify BBQ page layout");
            tests.add(test);
            new HtmlReportBuilder().build(tests, "target/galen-html-reports");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}