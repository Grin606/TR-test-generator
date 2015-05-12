package tel_ran.tests.generator.pictures;

import tel_ran.tests.dataset.ThreeDimensionalPictureArray;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.pictures.Picture;
import tel_ran.tests.pictures.PictureN;
import tel_ran.tests.pictures.Table;
import tel_ran.tests.tools.RandFunc;

public abstract class PictureTablesTest extends Testing_Problem {

	String correctAnswer;
	PictureN[][][] problem;
	PictureN[][][] answers;
	
	int problemLength = 1;
	
	public PictureTablesTest() {
		super();
//		weight = 1;
		numberOfDescripton = 5;
		category = "Picture reasoning";
		numOfAnswers = 4;
		getAnswerFrames().setFrMedium(2);
		getProblemFrames().setFrMedium(2);
		correctAnswer = "";
	}
	
	@Override
	public abstract void generate(int difficultyLevel);
	
	public Picture[][][] transferPictureNToPicture(PictureN[][][] pn){
		
		Picture[][][] res = new Picture[pn.length][pn[0].length][pn[0][0].length];
		
		PictureN pOrig;
		
		for (int i = 0; i < pn.length; i++) {
			for (int j=0; j < pn[0].length; j++) {
				for (int k=0; k < pn[0][0].length; k++) {
					pOrig = pn[i][j][k];
					res[i][j][k] = Picture.setPicture(pOrig.getShape(), pOrig.getColor(),pOrig.getInside());
				}
			}
		}
		
		return res;
	}

	
	public void shuffleTables(PictureN[][][] ppp) {
		
		int i1;
		int i2;
		PictureN[][] pp;
		
		int deep = ppp.length;
		for (int i=0; i<deep; i++) {
			i1 = RandFunc.IntRandomInRange(0, deep-1);
			i2 = RandFunc.IntRandomInRangeExept(0, deep-1, i1);
			pp = ppp[i2];
			ppp[i2] = ppp[i1];
			ppp[i1] = pp;
		}
	}
	
	public void setCorrectAnswer(PictureN[][] cAnswer, PictureN[][][] answers) {
		
		int cA = -1;
		for (int i= 0; i<answers.length; i++) {
			if (answers[i] == cAnswer) {cA = i; break;}
		}
		
		if (cA != -1) correctAnswerChar = answerCharSymbols[cA];  
	}
	
	public void setDataSet() {
		
		PictureN[][] cAnswer = answers[0]; 
		shuffleTables(answers);
		setCorrectAnswer(cAnswer, answers);	
		
		p.thp = new ThreeDimensionalPictureArray(transferPictureNToPicture(problem));
		a.thp = new ThreeDimensionalPictureArray(transferPictureNToPicture(answers));
	}
	
	public void uploadProblemAndAnswers(Table t) {
		
		problem = new PictureN[problemLength][t.getHeight()][t.getWidth()];
		answers = new PictureN[numOfAnswers][t.getHeight()][t.getWidth()];
		
	}

}
