package tel_ran.tests.generator.numeric;

import tel_ran.tests.numfunctions.FunctionsBox;
import tel_ran.tests.numfunctions.NFunct;
import tel_ran.tests.tools.RandFunc;

public class NumTableTest extends NumTest {
	
	int numOfFunctions;
	
	private int numOfMembers = 5;
	
	FunctionsBox fB;
	NFunct nFun;
	int questionPosition;
	int[] upper;
	int[] lower;
	String[] s_upper;
	String[] s_lower;
	String[][] problem;
	
	int r;
	
	public NumTableTest () {
		
		fB = new FunctionsBox();
		numOfFunctions = FunctionsBox.NUMBER_OF_FUNCTIONS;
//		weight = numOfFunctions;
		numberOfDescripton = 3;
		category = "Numerical Reasoning";
		name = "NumTableTest";
		
		upper = new int[numOfMembers];
		lower = new int[numOfMembers];
		s_upper = new String[numOfMembers];
		s_lower = new String[numOfMembers];
		
		problem = new String[2][];
	}
	
	@Override
	public void generate(int difficultyLevel) {
		
		if (difficultyLevel < 1 || difficultyLevel > 5) difficultyLevel = 5;
		difLevel = difficultyLevel;
		
		int nn = RandFunc.IntRandomInRange(0, numOfFunctions-1);
		nFun = fB.fBox[nn];
		nFun.setDifficultyLevel(difficultyLevel);
		
		int[] upper = NFunct.makeRandomIntArray(numOfMembers);		
		for (int i=0; i<numOfMembers; i++) {
			lower[i] = nFun.function(upper[i]);
		}
		r = nFun.range;
		
		questionPosition = RandFunc.IntRandomInRange(0, numOfMembers-1);
		int correctAnswer = lower[questionPosition];
		
		for (int i=0; i<numOfMembers; i++) {
			s_upper[i] = Integer.toString(upper[i]);
			if (i == questionPosition) s_lower[i] = "?";
			else s_lower[i] = Integer.toString(lower[i]);
		}
		
		problem[0] = s_upper;
		problem[1] = s_lower;
		
		p.setTDSA(problem);
		setIntAnswers(correctAnswer, r);
	}
}
