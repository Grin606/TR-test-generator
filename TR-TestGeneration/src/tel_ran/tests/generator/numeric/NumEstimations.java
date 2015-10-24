package tel_ran.tests.generator.numeric;

import tel_ran.tests.tools.*;

public class NumEstimations extends  NumTest {
	
	public static final int NUMBER_OF_TYPES = 3;
	
	public static int SUMM_RANGE = 100;    
	public static int PRODUCT_RANGE = 30;  
	public static int TAIL = 2;
	public static int DIGITS_AFTER_POINT_RANGE = 2;
	
	public NumEstimations() {
		
		super();
//		weight = NUMBER_OF_TYPES;
		numberOfDescripton = 2;
		category = "Computations";
		category2Name = "NumEstimations";
	}
	
	@Override
	public void generate(int difficultyLevel) {

		if (difficultyLevel < 1 || difficultyLevel > 5) difficultyLevel = 5;
		
		difLevel = difficultyLevel;
		
		String[] problem = new String[1];
		int correctAnswer=0;
	
		int type = RandFunc.IntRandomInRange(0, NUMBER_OF_TYPES-1);
		
		NumEstimations.setDifficultyLevel(difficultyLevel);
		
		double last = getRandomDouble(2, SUMM_RANGE);
		
		switch (type) {
		
			case 0:
				double s1 = getRandomDouble(2, SUMM_RANGE);
				double s2 = getRandomDouble(2, SUMM_RANGE);
				
				correctAnswer = (int)(s1+s2+last);
				problem[0] = Double.toString(s1)+" + " + Double.toString(s2) + " + " 
				                 + Double.toString(last) + " = ?";
				break;
			case 1:
				double fProduct = getRandomDouble(2, PRODUCT_RANGE);
				double sProduct = getRandomDouble(2, PRODUCT_RANGE);
				
				correctAnswer = (int)(fProduct*sProduct+last);
				problem[0] = Double.toString(fProduct) + "*" + Double.toString(sProduct) + " + " 
				                 + Double.toString(last) + " = ?";
				break;
			case 2:
				double toDivide = getRandomDouble(2, SUMM_RANGE);
				int divider = RandFunc.IntRandomInRange(2, PRODUCT_RANGE);
				
				correctAnswer = (int)(toDivide/divider+last);
				problem[0] = Double.toString(toDivide) + "/" + Integer.toString(divider) + " + "
				                 + Double.toString(last) + " = ?";
				break;
				
		}
		p.setODSA(problem);
		setIntAnswers(correctAnswer, (int)last);
	}
	
	double getRandomDouble(int nMin, int nMax) {
		
		int tenPower = RandFunc.IntRandomInRange(1, DIGITS_AFTER_POINT_RANGE);
		int tenP = 10;
		for (int i=1; i<tenPower; i++)tenP *= 10;
		
		
		double res = (double)(RandFunc.IntRandomInRange(nMin+1, nMax));
		int tail = RandFunc.IntRandomInRangeExept(-TAIL*tenPower, TAIL*tenPower,0);
		
		return res+(double)tail/tenP;
	}
	
	
	public static void setDifficultyLevel(int difficultyLevel) {

		int[] estimationSummRange = { 10, 30, 100, 300, 1000 };
		int[] estimationProductRange = { 5, 10, 20, 50, 50 };
		int[] estimationTailRange = { 1, 2, 2, 3, 5 };
		int[] estimationDigitsAfterPoint = { 1, 2, 2, 3, 3 };
		
		SUMM_RANGE = estimationSummRange[difficultyLevel - 1];
		PRODUCT_RANGE = estimationProductRange[difficultyLevel - 1];
		TAIL = estimationTailRange[difficultyLevel - 1];
		DIGITS_AFTER_POINT_RANGE = estimationDigitsAfterPoint[difficultyLevel - 1];
	}	
}
