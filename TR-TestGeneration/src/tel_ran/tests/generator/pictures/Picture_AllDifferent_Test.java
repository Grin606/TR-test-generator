package tel_ran.tests.generator.pictures;

import tel_ran.tests.pictures.PictureN;

import tel_ran.tests.pictures.Table;
import tel_ran.tests.pictures.Table33;
import tel_ran.tests.pictures.Table33_AllDifferent;
import tel_ran.tests.tools.RandFunc;

public class Picture_AllDifferent_Test extends PictureTablesTest {

	final int PROBLEM_LENGHT = 2;
	
	public Picture_AllDifferent_Test() {	
		super();			
		name = "Table All Differrent test";
		problemLength = PROBLEM_LENGHT;
	}
	
	@Override
	public void generate(int difficultyLevel) {
	
	Table33_AllDifferent ti = new Table33_AllDifferent();
	Table33 t = ti.t;
	
	uploadProblemAndAnswers(t);
	makeProblem(t, problem, answers);
	makeAnswers(answers);
	setDataSet();
	
}

public void makeProblem(Table33 init, PictureN[][][] pr, PictureN[][][] a) {
	
	Table33 tt;
	
	pr[0] = init.getTable();
	
	int step;
    
    do {
    step = RandFunc.IntRandomInRange(0, Table33.NUMBER_OF_STEPS-1);	
    
    tt = Table33.tableStep(init,step);
    } while(init.equals(tt));
    
    pr[1] = tt.getTable();
    
    tt = Table33.tableStep (tt,step);
    a[0] = tt.getTable();
}

public void makeAnswers(PictureN[][][] answers) {
	
	Table tt;
	
	PictureN[][] a = answers[0];
	Table t = new Table(a);
	
	tt = t.deviateColor();	
	answers[1] = tt.getTable();
	
	tt = t.deviateShape();
	answers[2] = tt.getTable();

	tt = t.deviateInside();
	answers[3] = tt.getTable();
}

}
