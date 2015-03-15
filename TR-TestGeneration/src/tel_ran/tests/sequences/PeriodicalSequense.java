package tel_ran.tests.sequences;
import tel_ran.tests.tools.RandFunc;

public class PeriodicalSequense extends NumSequence{
	
	int BASE_RANGE = 50;
	int RANGE = 2;
	
	public static String desc;
	
	public int[] make(int l){
		
	int[] res = new int[l];
	res[0] = RandFunc.IntRandomInRangeExept(-BASE_RANGE, BASE_RANGE,0);
	res[1] = RandFunc.IntRandomInRangeExept(-BASE_RANGE, BASE_RANGE,0);
	res[2] = RandFunc.IntRandomInRangeExept(-BASE_RANGE, BASE_RANGE,0);
	for (int i=3; i < l; i++) {
		res[i] = res[i-3];
	}
	
	range = RANGE;
	desc = "Periodical sequence";
	
	return res;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		
		int[] periodicalBaseRange = { 5, 10, 50, 200, 1000};
		
		BASE_RANGE = periodicalBaseRange[difficultyLevel-1];
	}

}
