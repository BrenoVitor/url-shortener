package br.com.backendbrasil.urlshortener.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", tags = "@UrlTest", plugin = {"pretty", "json:target/cucumber-report.json"}, strict = true, monochrome = true, dryRun = false, glue = "br.com.backendbrasil.urlshortener.cucumber")
public class UrlTest {

	

}
