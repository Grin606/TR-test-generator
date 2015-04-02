package tel_ran.tests.numfunctions;

import tel_ran.tests.tools.RandFunc;

public class IntLinear extends NFunct {
	
	int MULTY = 10;
	int STEP = 10;
	
	@Override
	public int function(int ar) {
		return MULTY*ar+STEP;
	}

	@Override
	public void setDifficultyLevel(int difficultyLevel) {
		int[] linearMultyRange = {3,5,10,10,20};
		int[] linearStepRange = {2,5,10,20,50};
		int[] linearArgRange = {20,20,20,30,50};
		
		MULTY = RandFunc.IntRandomInRange(1, linearMultyRange[difficultyLevel-1]);
		STEP = RandFunc.IntRandomInRange(1, linearStepRange[difficultyLevel-1]);
		
		range = linearStepRange[difficultyLevel-1];
		nMin = 1;
		nMax = RandFunc.IntRandomInRange(6, linearArgRange[difficultyLevel-1]);
	}

}
