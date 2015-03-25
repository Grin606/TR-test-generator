package tel_ran.tests.generator;

import tel_ran.tests.pictures.Picture;
import tel_ran.tests.tools.RandFunc;


public abstract class PictureTablesTest extends Testing_Problem {

	String correctAnswer;
	
	public PictureTablesTest() {
		super();
		correctAnswer = "";
	}
	
	@Override
	public abstract void generate(int difficultyLevel);
	
	public void shuffleTables(Picture[][][] ppp) {
		
		int i1;
		int i2;
		Picture[][] pp;
		
		int deep = ppp.length;
		for (int i=0; i<deep; i++) {
			i1 = RandFunc.IntRandomInRange(0, deep-1);
			i2 = RandFunc.IntRandomInRangeExept(0, deep-1, i1);
			pp = ppp[i2];
			ppp[i2] = ppp[i1];
			ppp[i1] = pp;
		}
	}
	
	public void setCorrectAnswer(Picture[][] cAnswer, Picture[][][] answers) {
		
		int cA = -1;
		for (int i= 0; i<answers.length; i++) {
			if (answers[i] == cAnswer) {cA = i; break;}
		}
		
		if (cA != -1) correctAnswerChar = answerCharSymbols[cA];  
	}

}
