package hu.masterfield.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = {"hu.masterfield"},
        tags = "@TC_SearchWithResults",
        plugin = { "pretty", "summary", "json:target/cucumber-reports.json",
                "junit:target/cucumber-reports.xml", "html:target/cucumber-reports.html",
                "rerun:target/cucumber-rerun.txt"},
        publish = true)
public class TestRunner {
}
