package com.reports;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting {
	public static void generateJVMReport(String jsonfile) {
		
	File file = new File("C:\\Users\\Scandy\\eclipse-workspace\\OMRBranchAPIAutomation\\target");
	
	Configuration config = new Configuration(file, "Adactin hotel");
	
	config.addClassifications("Browser", "Chrome");
	config.addClassifications("Version", "103");
	config.addClassifications("OS", "Windows10");
	
	List<String> jsonfiles =new ArrayList<String>();
	jsonfiles.add(jsonfile);
	
	ReportBuilder report = new ReportBuilder(jsonfiles, config);
	report.generateReports();
	

	}
}
