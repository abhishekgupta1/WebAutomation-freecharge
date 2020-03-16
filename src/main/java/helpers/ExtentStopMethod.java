package helpers;

import org.testng.annotations.Test;

public class ExtentStopMethod {

	@Test
	public void StopEmuWithService() {
		ExtentStartMethod.extent.flush();

	}
}