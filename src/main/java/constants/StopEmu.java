package constants;

import org.testng.annotations.Test;
public class StopEmu {
	

	@Test
	public void StopEmuWithService() {
		StartEmu.extent.flush();
	
	}
}