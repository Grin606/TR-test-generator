package tel_ran.tests.sequences;

import tel_ran.tests.generator.numeric.NumRandomSequence;
import tel_ran.tests.tools.RandFunc;

public class GeometricSequence extends NumRandomSequence{
	
	int BASE_RANGE = 10;
	int RATIO_RANGE = 6;
	
	public GeometricSequence() {
		super();
		name = "Geometric Sequence";
		
	}
	public void generate(int difficultyLevel) {
		
	setDifficultyLevel(difficultyLevel);
	
	int base = RandFunc.SignRandom()*RandFunc.IntRandomInRange(2, BASE_RANGE);
	int ratio = RandFunc.SignRandom()*RandFunc.IntRandomInRange(2, RATIO_RANGE);

	sequence[0] = base;
	for (int i=1; i < lengthOfSequence; i++) {
		sequence[i] = sequence[i-1]*ratio;
	}
	
	make(sequence,BASE_RANGE);
	
	}
	
	public void setDifficultyLevel(int difficultyLevel) {

		int[] geometricalBaseRange = { 5, 10, 10, 10, 20 };
		int[] geometricalRatioRange = { 3, 3,  5,  8, 10 };
		
		BASE_RANGE = geometricalBaseRange[difficultyLevel - 1];
		RATIO_RANGE = geometricalRatioRange[difficultyLevel - 1];
	}
}