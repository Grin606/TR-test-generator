package tel_ran.tests.generator.code_task.java;

public class TestData {
	
	public final int TIME_OUT = 10000;
	
	public final int MIN_BASE = 3;
	public final int MAX_BASE = 36;
		
	public final String RANDOM_BASE = "&";
	public final String RANDOM_METHOD = "*";
	
	public String[] methodsList = {
		"Add",         // 0
		"Subtract",    // 1
		"Multiply",    // 2
		"Divide",      // 3
		"Square",      // 4
		"Cube",        // 5
		"Factorial",   // 6
		"IsPrime",     // 7
		"GCD",         // 8
 		"CoPrime"      // 9
	};
	
	public String[] testProgram = {
		
		// base (& - random), list of methods (* - random 0-3)
		
		 "10,*",                        // difficulty level 1
		 "10,0,1,2,3",					// difficulty level 2
		 "10,0,1,2,3,4,5,6,7,8,9",		// difficulty level 3
		 "&,0,1,2,3",					// difficulty level 4
		 "&,0,1,2,3,4,5,6,7,8,9"		// difficulty level 5
	};
}