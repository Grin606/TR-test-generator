package tel_ran.tests.generator.pictures;

import tel_ran.tests.dataset.ThreeDimensionalPictureArray;
import tel_ran.tests.pictures.*;

public class Picture_311F_Test extends PictureTablesTest{
		
	final int PROBLEM_LENGHT = 4;
	final int ANSWERS_NUMBER = 4;
		
	public Picture_311F_Test() {
			
		super();			
		weight = 1;
		numberOfDescripton = 5;
		category = "Picture reasoning";
		name = "Table 211E test";
		numOfAnswers = 4;
	}

	@Override
	public void generate(int difficultyLevel) {

		Table33_311F ti = new Table33_311F();
		Table33 t = ti.t;

		Picture[][][] problem = new Picture[PROBLEM_LENGHT][t.getHeight()][t.getWidth()];
		Picture[][][] answers = new Picture[ANSWERS_NUMBER][t.getHeight()][t.getWidth()];
		
		makeProblem(ti, problem, answers);
		
		p.thp = new ThreeDimensionalPictureArray(problem);
		Picture[][] cAnswer = answers[0]; 
		
		makeAnswers(answers);
		
		a.thp = new ThreeDimensionalPictureArray(answers);
		
		setCorrectAnswer(cAnswer, answers);
	}
	
	public void makeProblem(Table33_311F init, Picture[][][] pr, Picture[][][] a) {
				
		pr[0] = init.t.getTable();
		Table tt = init.t.copyTable();
		
		int[] seq = new int[3];
		switch (init.kind) {
		
			case 0: seq[0] = init.rc1; seq[1] = init.rc2; seq[2] = init.rc3; break;
			case 1: seq[0] = init.rs1; seq[1] = init.rs2; seq[2] = init.rs3; break;
			case 2: seq[0] = init.ri1; seq[1] = init.ri2; seq[2] = init.ri3; break;
		}
	    
	    for (int i=1; i<=PROBLEM_LENGHT; i++) {
	    	
	    	switch (init.kind) {
	    	
	    	case 0: tt.colorSequenceStep(seq); break;
	    	case 1: tt.shapeSequenceStep(seq); break;
	    	case 2: tt.insideSequenceStep(seq); break;
	    	
	    	}
	    	
	    	if (i == PROBLEM_LENGHT)a[0] = tt.copyTable().getTable(); 
	    	else pr[i] = tt.copyTable().getTable(); 	
	    }  
	}
	
	public void makeAnswers(Picture[][][] answers) {
		
		Table tt;
		
		Picture[][] a = answers[0];
		Table t0 = new Table(a);

		tt = t0.deviateColor();           // Wrong answers
		answers[1] = tt.copyTable().getTable();
		tt = t0.deviateShape();
		answers[2] = tt.copyTable().getTable();
		tt = t0.deviateInside();
		answers[3] = tt.copyTable().getTable();

		shuffleTables(answers);
}
}
