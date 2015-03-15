package tel_ran.tests.sequences;

import tel_ran.tests.tools.RandFunc;

public class ArithmeticSequence extends NumSequence{
	
	int BASE_RANGE = 100;
	int STEP_RANGE = 20;
	
	public static String desc;
	
	public int[] make(int l){
		
		int[] res = new int[l];
	
		int base = RandFunc.IntRandomInRangeExept(-BASE_RANGE, BASE_RANGE,0);
		int step = RandFunc.IntRandomInRangeExept(-STEP_RANGE, STEP_RANGE,0);
	
		res[0] = base;
		for (int i=1; i < l; i++) {
			res[i] = res[i-1]+step;
		}
	
		range = STEP_RANGE;
		desc = "Arithmetic sequence with base " + Integer.toString(base)+ 
						  " and step "+ Integer.toString(step);
	
		return res;
	}

	public void setDifficultyLevel(int difficultyLevel) {

		int[] arithmeticBaseRange = { 10, 30, 100, 100, 200 };
		int[] arithmeticStepRange = { 5, 10, 20, 50, 100 };
		
		BASE_RANGE = arithmeticBaseRange[difficultyLevel - 1];
		STEP_RANGE = arithmeticStepRange[difficultyLevel - 1];

	}
}
