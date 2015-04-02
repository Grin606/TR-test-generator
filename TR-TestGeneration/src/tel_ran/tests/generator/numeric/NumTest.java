package tel_ran.tests.generator.numeric;

import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.tools.*;


public abstract class NumTest extends Testing_Problem {
	
	public NumTest() {
		super();
	}
	
	@Override
	public abstract void generate(int difficultyLevel);
	
	void setIntAnswers(int intCorrAnswer, int range) {
		
		if (range == 0) range = 1;
		
		String[] answers = new String[numOfAnswers];
			
		answers[1] = Integer.toString(intCorrAnswer+RandFunc.IntRandomInRangeExept(-range, range-1, 0));
		for (int i = 2; i<numOfAnswers; i++) {
			answers[i]= Integer.toString(
					              intCorrAnswer+RandFunc.SignRandom()*RandFunc.IntRandomInRange(range*(i-1), range*i-1));
		}  
		String correctAnswer = Integer.toString(intCorrAnswer);
		
		answers[0] = correctAnswer;
		answers = ArrayFun.shuffle(answers);
		
		setAnswers(answers);
		
		for (int i=0; i<numOfAnswers; i++)
			if (answers[i].equals(correctAnswer)) correctAnswerChar = answerCharSymbols[i];
	}
	
	public void setAnswers(String[] answers) {
		a.setODSA(answers);
	}
	
}
