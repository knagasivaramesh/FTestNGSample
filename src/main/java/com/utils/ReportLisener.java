package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReportLisener implements IReporter{

    private ExtentHtmlReporter htmlReporter;
    private ExtentReports extent;

    public void generateReport( List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        String date = new SimpleDateFormat("dd-MM-yy").format(new Date());
        htmlReporter = new ExtentHtmlReporter(outputDirectory + File.separator + suites.get(0).getName() +"_"+ date + ".html");

        htmlReporter.config().setChartVisibilityOnOpen(true);

        // Report configurations
        htmlReporter.config()
                .setJS("$(document).ready(function() { var link = document.createElement('link');link.type = 'image/x-icon';link.rel = 'shortcut icon';link.href = '';document.getElementsByTagName('head')[0].appendChild(link);  });"
                        + "$(document).ready(function() { var a = document.getElementsByClassName('brand-logo')[0]; a.innerHTML='Report'; a.href = '';});");
        htmlReporter.config().setDocumentTitle("QA Automation Report");
        htmlReporter.config().setReportName("QA Automation Report");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Put Failures on top of the report
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getFailedTests(), Status.FAIL);
            }
        }

        // Skipped
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
            }
        }

        // Passed
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), Status.PASS);
            }
        }

        for (String s : Reporter.getOutput()) {
            extent.setTestRunnerOutput(s);
        }

        extent.flush();
    }

    private void buildTestNodes( IResultMap tests, Status status) {
        ExtentTest test;

        String screenshotPath = System.getProperty("user.dir") + "//src//test//resources//screenshots//";

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                String testName = result.getMethod().getMethodName();
                System.out.println(result.getMethod().getMethodName());
                test = extent.createTest(testName);

                // test.getTest().setStartedTime(getTime(result.getStartMillis()));
                // test.getTest().setEndedTime(getTime(result.getEndMillis()));

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                String message = "Test " + status.toString().toLowerCase() + "ed";

                if (result.getThrowable() != null) {
                    // test.log(status,
                    // ExceptionUtils.getStackTrace(result.getThrowable()));
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                    try {
                        test.addScreenCaptureFromPath(screenshotPath + result.getName() + ".jpeg");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");

                    try {
                        test.addScreenCaptureFromPath(screenshotPath + result.getName() + ".jpeg");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
