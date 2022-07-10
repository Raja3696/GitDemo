package testCases;

import static org.testng.Assert.assertThrows;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	@BeforeTest
	public void setReport() {
		htmlReporter = new ExtentHtmlReporter("./reports/extent.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("W2A Automation Reports");
		htmlReporter.config().setReportName("Automation Results");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("Automation Tester", "Raja N");
		extent.setSystemInfo("Organisation", "W2Automation");
		extent.setSystemInfo("Build Number", "100.545.2");
	}
	
	@AfterTest
	public void endReport() {
		extent.flush();
	}
	
	@Test
	public void doLogin() {
		test = extent.createTest("Login Test");
		System.out.println("Executing Login Test");
	}
	@Test
	public void DoUserReg() {
		test = extent.createTest("Registration Test");
		System.out.println("Executing Registration Test");
		Assert.fail("Execution Failed");
	}
	@Test
	public void IsSkip() {
		test = extent.createTest("Skip Test");
//		System.out.println("Executing Skip Test");
		throw new SkipException("Test case is skipped");
	}
	
	@AfterMethod()
	public void tearDown(ITestResult result) {
			if(result.getStatus() == ITestResult.FAILURE) {
//				String MethodName = result.getMethod().getMethodName();
//				String logFile = "<b>"+"TEST CASE: - "+MethodName.toUpperCase()+"</b>";
//				Markup m = MarkupHelper.createLabel(logFile, ExtentColor.RED);
//				test.fail(m);
				
				
			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			test.fail("<details>"+"<summary>"+"<b>"+"<font color = red>"+"Exception Occured:Click to see"+"</font>"
					+ "</b>"+"</summary>"+exceptionMessage.replaceAll(",", "<br>")+"</details>"+"\n");
				String failueLogg = "TEST CASE FAILED";
				Markup m = MarkupHelper.createLabel(failueLogg, ExtentColor.RED);
				test.log(Status.FAIL, m);
			}
				
			else if(result.getStatus() == ITestResult.SKIP) {
				String MethodName = result.getMethod().getMethodName();
				String logFile = "<b>"+"TEST CASE: - "+MethodName.toUpperCase()+"</b>";
				Markup m = MarkupHelper.createLabel(logFile, ExtentColor.BLUE);
				test.skip(m);
			}
			else if(result.getStatus() == ITestResult.SUCCESS) {
				String MethodName = result.getMethod().getMethodName();
				String logFile = "<b>"+"TEST CASE: - "+MethodName.toUpperCase()+"</b>";
				Markup m = MarkupHelper.createLabel(logFile, ExtentColor.GREEN);
				test.pass(m);
			}
				
	}
	
}
