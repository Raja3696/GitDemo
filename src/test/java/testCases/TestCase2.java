package testCases;

import java.util.Date;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestCase2 {

	@Test
	public void doLogin() {
		Date date = new Date();
		date.toString();
		System.out.println("Executing Login Test  "+date);
	}

	@Test
	public void DoUserReg() {
		System.out.println("Executing Registration Test");
		Assert.fail("Execution Failed");
		System.out.println("Assertion Failed");    // Newly added for git learning
	}

	@Test
	public void IsSkip() {
		throw new SkipException("Test case is skipped");
	}
}
