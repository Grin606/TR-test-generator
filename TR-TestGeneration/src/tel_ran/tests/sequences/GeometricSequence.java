package tel_ran.tests.sequences;

import tel_ran.tests.tools.RandFunc;

public class GeometricSequence extends NumSequence{
	
	int BASE_RANGE = 10;
	int RATIO_RANGE = 6;
	
	public static String desc;
	
	public int[] make(int l) {
	
	int[] res = new int[l];
		
	int base = RandFunc.SignRandom()*RandFunc.IntRandomInRange(2, BASE_RANGE);
	int ratio = RandFunc.SignRandom()*RandFunc.IntRandomInRange(2, RATIO_RANGE);
	
	res[0] = base;
	for (int i=1; i < l; i++) {
		res[i] = res[i-1]*ratio;
	}
	range = BASE_RANGE;
	desc = "Geometric sequence with base " + Integer.toString(base)+ 
			  " and ratio "+ Integer.toString(ratio);
	return res;
	}

	public void setDifficultyLevel(int difficultyLevel) {

		int[] geometricalBaseRange = { 5, 10, 10, 10, 20 };
		int[] geometricalRatioRange = { 3, 3,  5,  8, 10 };
		
		BASE_RANGE = geometricalBaseRange[difficultyLevel - 1];
		RATIO_RANGE = geometricalRatioRange[difficultyLevel - 1];
	}
}