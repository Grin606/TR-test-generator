package tel_ran.tests.sequences;

import tel_ran.tests.generator.*;
import tel_ran.tests.generator.numeric.NumRandomSequence;
import tel_ran.tests.tools.RandFunc;

public class ArithmeticSequence extends NumRandomSequence{
	
	int BASE_RANGE = 100;
	int STEP_RANGE = 20;
	
	public static String desc;
	
	public ArithmeticSequence() {
		super();
		name = "Arithmetic Sequence";
		
	}
	public void generate(int difficultyLevel) {
		
	setDifficultyLevel(difficultyLevel);
	
	int base = RandFunc.IntRandomInRangeExept(-BASE_RANGE, BASE_RANGE,0);
	int step = RandFunc.IntRandomInRangeExept(-STEP_RANGE, STEP_RANGE,0);

	sequence[0] = base;
	for (int i=1; i < lengthOfSequence; i++) {
		sequence[i] = sequence[i-1]+step;
	}
	
	make(sequence,STEP_RANGE);
	
	}

	public void setDifficultyLevel(int difficultyLevel) {

		int[] arithmeticBaseRange = { 10, 30, 100, 100, 200 };
		int[] arithmeticStepRange = { 5, 10, 20, 50, 100 };
		
		BASE_RANGE = arithmeticBaseRange[difficultyLevel - 1];
		STEP_RANGE = arithmeticStepRange[difficultyLevel - 1];

	}
}
