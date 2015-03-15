package tel_ran.tests.sequences;

import tel_ran.tests.tools.RandFunc;

public class ArithmeticWIncSequence extends NumSequence{
	
	int BASE_RANGE = 100;
	int STEP_RANGE = 20;
	int INCREMENT_RANGE = 5;
	
	public static String desc;
	
	public int[] make(int l) {
		
		int[] res = new int[l];
		
		int base = RandFunc.IntRandomInRangeExept(-BASE_RANGE, BASE_RANGE,0);
		int step0 = RandFunc.IntRandomInRangeExept(- STEP_RANGE, STEP_RANGE,0);
		int inc = RandFunc.IntRandomInRange(1, INCREMENT_RANGE);
		
		res[0] = base;
		int step = step0;
		for (int i=1; i < l; i++) {
			res[i] = res[i-1]+step;
			step += inc;
		}
		range = STEP_RANGE;
		
		desc = "Arithmetic sequence with base " + Integer.toString(base)+ 
			   ", initial step "+ Integer.toString(step0) + 
			   " and step increment "+ Integer.toString(inc);
		return res;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		
		int[] arithmeticWIncBaseRange = { 10, 30, 100, 100, 200 };
		int[] arithmeticWIncStepRange = { 5, 10, 20,  20,  30 };
		int[] arithmeticWIncIncRange = { 2, 3, 5,  10,  30 };
		
		BASE_RANGE = arithmeticWIncBaseRange[difficultyLevel - 1];
		STEP_RANGE = arithmeticWIncStepRange[difficultyLevel - 1];
		INCREMENT_RANGE = arithmeticWIncIncRange[difficultyLevel - 1];

	}
}