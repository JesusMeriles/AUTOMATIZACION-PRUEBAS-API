package com.todoly.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.todoly.stepdefinitions"},
        plugin = {
                "pretty",
                "html:build/reports/cucumber/cucumber-report.html",
                "json:build/reports/cucumber/cucumber.json",
                "junit:build/reports/cucumber/cucumber.xml"
        },
        monochrome = true
)
public class CucumberTestRunner {
}
