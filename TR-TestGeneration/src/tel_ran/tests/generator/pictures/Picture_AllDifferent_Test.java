package tel_ran.tests.generator.pictures;

import tel_ran.tests.dataset.ThreeDimensionalPictureArray;
import tel_ran.tests.pictures.Picture;
import tel_ran.tests.pictures.Table;
import tel_ran.tests.pictures.Table33;
import tel_ran.tests.pictures.Table33_AllDifferent;
import tel_ran.tests.tools.RandFunc;

public class Picture_AllDifferent_Test extends PictureTablesTest {

static final int NUMBER_OF_STEPS = 18;
	
	final int PROBLEM_LENGHT = 2;
	final int ANSWERS_NUMBER = 4;
	
	public Picture_AllDifferent_Test() {	
		super();			
		name = "Table 211E test";
	}
	
	
	@Override
	public void generate(int difficultyLevel) {
	
	Table33_AllDifferent ti = new Table33_AllDifferent();
	Table33 t = ti.t;

	Picture[][][] problem = new Picture[PROBLEM_LENGHT][t.getHeight()][t.getWidth()];
	Picture[][][] answers = new Picture[ANSWERS_NUMBER][t.getHeight()][t.getWidth()];
			
	makeProblem(t, problem, answers);
	
	p.thp = new ThreeDimensionalPictureArray(problem);
	Picture[][] cAnswer = answers[0]; 
	
	makeAnswers(answers);
	
	a.thp = new ThreeDimensionalPictureArray(answers);
	
	setCorrectAnswer(cAnswer, answers);
	
}

public void makeProblem(Table33 init, Picture[][][] pr, Picture[][][] a) {
	
	Table33 tt;
	
	pr[0] = init.getTable();
	
	int step;
    
    do {
    step = RandFunc.IntRandomInRange(0, NUMBER_OF_STEPS-1);	
    
    tt = tableStep(init,step);
    } while(init.equals(tt));
    
    pr[1] = tt.getTable();
    
    tt = tableStep (tt,step);
    a[0] = tt.getTable();
}

public void makeAnswers(Picture[][][] answers) {
	
	Table tt;
	
	Picture[][] a = answers[0];
	Table t = new Table(a);
	
	tt = t.deviateColor();
	answers[1] = tt.getTable();
	
	tt = t.deviateShape();
	answers[2] = tt.getTable();

	tt = t.deviateInside();
	answers[3] = tt.getTable();

	shuffleTables(answers);
}

	
public Table33 tableStep(Table33 t, int step) {
	
	Table33 tt = t.copyTable33();
	
		switch (step) {
	
			case 0: tt.swapAngles(); break;
			case 1: tt.swapColumns(0, 2); break;
			case 2: tt.swapRows(0, 2); break;
			case 3: tt.stepUp(); break;
			case 4: tt.stepDown(); break;
			case 5: tt.stepLeft(); break;
			case 6: tt.stepRight(); break;
			case 7: tt.downAndRight(); break;
			case 8: tt.downAndLeft(); break;
			case 9: tt.upAndRight(); break;
			case 10: tt.upAndLeft(); break;
			case 11: tt.snakeClockwise(); break;
			case 12: tt.snakeCounterClockwise(); break;
			case 13: tt.turn90(); break;
			case 14: tt.turn180(); break;
			case 15: tt.turn270(); break;
			case 16: tt.snakeZigZagHor(); break;
			case 17: tt.snakeZigZagVert(); break;

			default:
		}
	return tt;
}


}
