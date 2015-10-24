package tel_ran.tests.generator.pictures;

import tel_ran.tests.pictures.*;

public class Picture_311F_Test extends PictureTablesTest{
		
	final int PROBLEM_LENGHT = 4;
		
	public Picture_311F_Test() {
			
		super();			
		category2Name = "Table 311F test";
		problemLength = PROBLEM_LENGHT;
	}

	@Override
	public void generate(int difficultyLevel) {

		Table33_311F ti = new Table33_311F();
		Table33 t = ti.t;
		
		uploadProblemAndAnswers(t);
		makeProblem(ti, problem, answers);
		makeAnswers(ti, answers);
		setDataSet();
	}
	
	public void makeProblem(Table33_311F init, PictureN[][][] pr, PictureN[][][] a) {
				
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
	
	public void makeAnswers(Table33_311F ti, PictureN[][][] answers) {
		
		Table tt;
		
		PictureN[][] a = answers[0];
		Table t0 = new Table(a);
		
		switch (ti.kind) {
		
		case 0:
			
			tt = t0.copyTable();
			tt.swapColors(ti.rc1, ti.rc2);          
			answers[1] = tt.copyTable().getTable();
			
			tt = t0.copyTable();
			tt.swapColors(ti.rc1, ti.rc3);          
			answers[2] = tt.copyTable().getTable();

			tt = t0.copyTable();
			tt.swapColors(ti.rc2, ti.rc3);          
			answers[3] = tt.copyTable().getTable();
			
			break;
			
		case 1:
			
			tt = t0.copyTable();
			tt.swapShapes(ti.rs1, ti.rs2);          
			answers[1] = tt.copyTable().getTable();
			
			tt = t0.copyTable();
			tt.swapShapes(ti.rs1, ti.rs3);          
			answers[2] = tt.copyTable().getTable();

			tt = t0.copyTable();
			tt.swapShapes(ti.rs2, ti.rs3);          
			answers[3] = tt.copyTable().getTable();
			
			break;
			
		case 2:
			
			tt = t0.copyTable();
			tt.swapInsides(ti.ri1, ti.ri2);          
			answers[1] = tt.copyTable().getTable();
			
			tt = t0.copyTable();
			tt.swapInsides(ti.ri1, ti.ri3);          
			answers[2] = tt.copyTable().getTable();

			tt = t0.copyTable();
			tt.swapInsides(ti.ri2, ti.ri3);          
			answers[3] = tt.copyTable().getTable();
			
			break;
			
		}
	}
	
}
