package tel_ran.tests.generator.pictures;

import tel_ran.tests.pictures.*;
import tel_ran.tests.tools.RandFunc;

public class Picture_211E_Test extends PictureTablesTest{
	
	final int PROBLEM_LENGHT = 2;
	
	public Picture_211E_Test() {
		
		super();			
		name = "Table 211E test";
		problemLength = PROBLEM_LENGHT;
}

	@Override
	public void generate(int difficultyLevel) {
	
		Table33_211E ti = new Table33_211E();
		Table33 t = ti.t;
		
		uploadProblemAndAnswers(t);				
		makeProblem(t, problem, answers);
		makeAnswers(ti, answers);	
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
	
	public void makeAnswers(Table33_211E ti, PictureN[][][] answers) {
		
		Table tt;
		
		PictureN[][] a = answers[0];
		Table t = new Table(a);
	
		tt = Table.birth(t);           // Wrong answers
		answers[1] = tt.getTable();
		
		tt=Table.death(t);
		answers[2] = tt.getTable();

	    tt = t.copyTable();
		switch (ti.kind) {
	    case 0: tt = t.deviateColor(ti.rc1, ti.rc2, t);break;
	    case 1: tt = t.deviateInside(ti.ri1,ti.ri2, t);break;
	    case 2: tt = t.deviateShape(ti.rs1, ti.rs2, t);break;
	    }
		answers[3] = tt.getTable();

	}
}