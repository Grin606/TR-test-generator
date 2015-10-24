package tel_ran.tests.generator.numeric;

import tel_ran.tests.tools.*;
	
	public class NumComputations extends NumTest{
		
		public static final int NUMBER_OF_TYPES = 3;
		
		public static int SUMM_RANGE = 100; 
		public static int PRODUCT_RANGE = 30;
		
		
		public NumComputations () {
			
			super();
//			weight = NUMBER_OF_TYPES;
			numberOfDescripton = 1;
//			category = "Computations";
			category2Name = "NumComputations";
		}
		
		@Override
		public void generate(int difficultyLevel) {
			
			if (difficultyLevel < 1 || difficultyLevel > 5) difficultyLevel = 5;
			
			difLevel = difficultyLevel;
			
			String[] problem = new String[1];
			int correctAnswer=0;
			
			int type = RandFunc.IntRandomInRange(0, NUMBER_OF_TYPES-1);
			
			NumComputations.setDifficultyLevel(difficultyLevel);
			int last = RandFunc.IntRandomInRange(1, SUMM_RANGE);
			
			switch (type) {
			
				case 0:
					int s1 = RandFunc.IntRandomInRange(1, SUMM_RANGE);
					int s2 = RandFunc.IntRandomInRange(1, SUMM_RANGE);
					
					correctAnswer = s1 + s2 + last;
					problem[0] = Integer.toString(s1)+" + " + Integer.toString(s2) + " + " 
					                 + Integer.toString(last) + " = ?";
					break;
				case 1:
					int fProduct = RandFunc.IntRandomInRange(2, PRODUCT_RANGE);
					int sProduct = RandFunc.IntRandomInRange(2, PRODUCT_RANGE);
					
					correctAnswer = fProduct*sProduct + last;
					problem[0] = Integer.toString(fProduct) + "*" + Integer.toString(sProduct) + " + "
						       + Integer.toString(last) + " = ?";
					break;
				case 2:
					int sInt = RandFunc.IntRandomInRange(2, PRODUCT_RANGE);
					int toDivide = RandFunc.IntRandomInRange(2, PRODUCT_RANGE)*sInt;
					
					correctAnswer = toDivide/sInt+last;
					problem[0] = Integer.toString(toDivide)+ "/" + Integer.toString(sInt) + " + " 
					                 + Integer.toString(last) + " = ?";
					break;	
			}
			p.setODSA(problem);
			setIntAnswers(correctAnswer, last);
		}
		
		public static void setDifficultyLevel(int difficultyLevel) {
			
			int[] computationSummRange = { 30, 50, 100, 200, 200 };
			int[] computationsProductRange = { 10, 20, 30, 30, 50 };
			
			SUMM_RANGE = computationSummRange[difficultyLevel - 1];
			PRODUCT_RANGE = computationsProductRange[difficultyLevel - 1];
		}
	}		




