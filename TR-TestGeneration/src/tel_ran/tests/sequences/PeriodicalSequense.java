package tel_ran.tests.sequences;
import tel_ran.tests.generator.numeric.NumRandomSequence;
import tel_ran.tests.tools.RandFunc;

public class PeriodicalSequense extends NumRandomSequence{
	
	int BASE_RANGE = 50;
	int RANGE = 2;
	
	public PeriodicalSequense() {
		super();
		name = "Arithmetic Sequence";
		
	}
	public void generate(int difficultyLevel) {
		
	setDifficultyLevel(difficultyLevel);
	
	int[] sequence= new int[lengthOfSequence];
	sequence[0] = RandFunc.IntRandomInRangeExept(-BASE_RANGE, BASE_RANGE,0);
	sequence[1] = RandFunc.IntRandomInRangeExept(-BASE_RANGE, BASE_RANGE,0);
	sequence[2] = RandFunc.IntRandomInRangeExept(-BASE_RANGE, BASE_RANGE,0);
	for (int i=3; i < lengthOfSequence; i++) {
		sequence[i] = sequence[i-3];
	}
	
	make(sequence,RANGE);
	
	}
	
	public void setDifficultyLevel(int difficultyLevel) {
		
		int[] periodicalBaseRange = { 5, 10, 50, 200, 1000};
		
		BASE_RANGE = periodicalBaseRange[difficultyLevel-1];
	}

}
