package tel_ran.tests.sequences;

import tel_ran.tests.tools.RandFunc;

public class FibonacciSequence extends NumSequence{
	
	static int BASE_RANGE1 = - 10;
	static int BASE_RANGE2 = 10;
	
	public static String desc;
	
	public int[] make(int l) {
	
	int[] res = new int[l];
		
	int base1 = RandFunc.IntRandomInRangeExept(BASE_RANGE1, BASE_RANGE2,0);
	int base2 = RandFunc.IntRandomInRangeExept(BASE_RANGE1, BASE_RANGE2,0);
	
	res[0] = base1;
	res[1] = base2;
	for (int i=2; i < l; i++) {
		res[i] = res[i-1]+res[i-2];
	}
	range = Math.max(Math.abs(BASE_RANGE1), Math.abs(BASE_RANGE2));
	
	desc = "Fibonacci sequence with bases " + Integer.toString(base1)+ 
						  " and "+ Integer.toString(base2);
	return res;
	}
	
	public void setDifficultyLevel(int difficultyLevel) {
		
		int[] fibonacciBaseRange1 = { 0, 0, -10, -20, -30 };
		int[] fibonacciBaseRange2 = { 5, 10, 10,  20,  30 };
		
		BASE_RANGE1 = fibonacciBaseRange1[difficultyLevel - 1];
		BASE_RANGE2 = fibonacciBaseRange2[difficultyLevel - 1];
		
		
		
	}
	
}
