package tel_ran.tests.numfunctions;

import tel_ran.tests.tools.RandFunc;

public class IntSquarePlusStep extends NFunct {

	int STEP;
	
	@Override
	public int function(int ar) {
		return ar*ar+STEP;
	}

	@Override
	public void setDifficultyLevel(int difficultyLevel) {
		
		int[] sqStepRange = {1,5,10,20,50};
		int[] sqStepArgRange = {15,15,15,20,20};
		
		STEP = RandFunc.IntRandomInRange(2, sqStepRange[difficultyLevel-1]);
		range = sqStepRange[difficultyLevel-1];
		nMin = 1;
		nMax = RandFunc.IntRandomInRange(6, sqStepArgRange[difficultyLevel-1]);
	}

}
