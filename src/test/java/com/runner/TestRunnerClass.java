package com.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.reports.Reporting;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(snippets=SnippetType.CAMELCASE,monochrome=true,dryRun = false,plugin= {"pretty", "json:target/report.json"}
,features= {"src\\test\\resources"}, glue= {"com.stepdefinition"})
public class TestRunnerClass {
	
	@AfterClass
	public static void after() {
		
		Reporting.generateJVMReport("C:\\Users\\Scandy\\eclipse-workspace\\OMRBranchAPIAutomation\\target\\report.json");
	}

}
