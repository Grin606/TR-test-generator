package tel_ran.tests.numfunctions;

import tel_ran.tests.tools.RandFunc;

public class IntSquareMulty extends NFunct {

	int MULTY;
	
	@Override
	public int function(int ar) {
		return ar*ar*MULTY;
	}

	@Override
	public void setDifficultyLevel(int difficultyLevel) {
		int[] sqMultyRange = {1,1,3,5,10};
		int[] sqMultyArgRange = {15,15,15,20,30};
		
		MULTY = RandFunc.IntRandomInRange(1, sqMultyRange[difficultyLevel-1]);
		range = sqMultyRange[difficultyLevel-1];
		nMin = 1;
		nMax = RandFunc.IntRandomInRange(6, sqMultyArgRange[difficultyLevel-1]);

	}

}
