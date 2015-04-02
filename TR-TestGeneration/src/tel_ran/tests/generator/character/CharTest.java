package tel_ran.tests.generator.character;

import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.sequences.CesarSequence;
import tel_ran.tests.tools.*;

public abstract class CharTest extends Testing_Problem {

	public CharTest() {
		super();
	}

	@Override
	public abstract void generate(int difficultyLevel);
	
	void setCharAnswers(String charCorrAnswer) {
		
		String[] answers = new String[numOfAnswers];
			
		answers[1] = CesarSequence.cesarStep(1, charCorrAnswer);
		for (int i = 2; i<numOfAnswers; i++) {
			answers[i]= CesarSequence.cesarStep(i, charCorrAnswer);
					
		}  
		
		answers[0] = charCorrAnswer;
		answers = ArrayFun.shuffle(answers);
		
		setAnswers(answers);
		
		for (int i=0; i<numOfAnswers; i++)
			if (answers[i].equals(charCorrAnswer)) correctAnswerChar = answerCharSymbols[i];
	}
	
	public void setAnswers(String[] answers) {
		a.setODSA(answers);
	}
	
}
