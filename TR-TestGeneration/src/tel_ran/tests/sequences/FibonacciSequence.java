package tel_ran.tests.sequences;

import tel_ran.tests.generator.numeric.NumRandomSequence;
import tel_ran.tests.tools.RandFunc;

public class FibonacciSequence extends NumRandomSequence{
	
	static int BASE_RANGE1 = - 10;
	static int BASE_RANGE2 = 10;
	
	public FibonacciSequence() {
		super();
		name = "Arithmetic Sequence";
		
	}
	public void generate(int difficultyLevel) {
		
	setDifficultyLevel(difficultyLevel);
	
	int[] sequence = new int[lengthOfSequence];
	
	int base1 = RandFunc.IntRandomInRangeExept(BASE_RANGE1, BASE_RANGE2,0);
	int base2 = RandFunc.IntRandomInRangeExept(BASE_RANGE1, BASE_RANGE2,0);
	
	sequence[0] = base1;
	sequence[1] = base2;
	for (int i=2; i < lengthOfSequence; i++) {
		sequence[i] = sequence[i-1]+sequence[i-2];
	}
	
	make(sequence,Math.max(Math.abs(BASE_RANGE1), Math.abs(BASE_RANGE2)));
	
	}
		
	public void setDifficultyLevel(int difficultyLevel) {
		
		int[] fibonacciBaseRange1 = { 0, 0, -10, -20, -30 };
		int[] fibonacciBaseRange2 = { 5, 10, 10,  20,  30 };
		
		BASE_RANGE1 = fibonacciBaseRange1[difficultyLevel - 1];
		BASE_RANGE2 = fibonacciBaseRange2[difficultyLevel - 1];
		
		
		
	}
	
}
