package tel_ran.tests.generator.pictures;

import tel_ran.tests.dataset.*;
import tel_ran.tests.pictures.*;
import tel_ran.tests.tools.RandFunc;

public class Picture_211E_Test extends PictureTablesTest{
	
	static final int NUMBER_OF_STEPS = 16;
	
	final int PROBLEM_LENGHT = 2;
	final int ANSWERS_NUMBER = 4;
	
	public Picture_211E_Test() {
		
		super();			
		weight = 1;
		numberOfDescripton = 5;
		category = "Picture reasoning";
		name = "Table 211E test";
		numOfAnswers = 4;
	}

	@Override
	public void generate(int difficultyLevel) {
	
		Table33_211E ti = new Table33_211E();
		Table33 t = ti.t;

		Picture[][][] problem = new Picture[PROBLEM_LENGHT][t.getHeight()][t.getWidth()];
		Picture[][][] answers = new Picture[ANSWERS_NUMBER][t.getHeight()][t.getWidth()];
		
				
		makeProblem(t, problem, answers);
		
		
		
		p.thp = new ThreeDimensionalPictureArray(problem);
		Picture[][] cAnswer = answers[0]; 
		
		makeAnswers(ti.kind, answers, ti.rc1,ti.rc2,ti.rs1,ti.rs2,ti.ri1,ti.ri2);
		
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
	
	public void makeAnswers(int kind, Picture[][][] answers,
									int rc1, int rc2, int rs1, int rs2, int ri1, int ri2) {
		
		Table33 tt;
		
		Picture[][] a = answers[0];
		Table33 t = new Table33(a);
		
		
		tt = birth(t);           // Wrong answers
		
		
		
		answers[1] = tt.getTable();
		
		tt=death(t);
		answers[2] = tt.getTable();
		

	    switch (kind) {
	    case 0: tt = (Table33) deviateColor(rc1, rc2, t);break;
	    case 1: tt = (Table33) deviateInside(ri1,ri2, t);break;
	    case 2: tt = (Table33) deviateShape(rs1, rs2, t);break;
	    }
	    
		
		answers[3] = tt.getTable();

		shuffleTables(answers);
	}
	
	public Table33 deviateShape(int is1, int is2, Table33 t) {
		
		Table33 tt = t.copyTable33();
		Picture p;
		
		if (t.isTableEmpty()) return tt;
			
		int rp = tt.getRandomCellNotEmpty();
		int ri = rp/tt.getHeight();
		int rj = rp%tt.getHeight();
		
		p = tt.getTable()[ri][rj];
		
		int is = tt.getTable()[ri][rj].getShape();
		int ic = tt.getTable()[ri][rj].getColorInt();
		int ii = tt.getTable()[ri][rj].getInside();
		
		if (is == is1) p = Picture.setShape(is1);
		if (ic == is2) p = Picture.setShape(is2);
		
		p.setColor(ic);
		p.setInside(ii);
		
		tt.getTableToChange()[ri][rj] = p;
		
		return tt;
	}
	
	public Table33 deviateInside(int ii1, int ii2, Table33 t) {
		
		Table33 tt = t.copyTable33();
		
		if (t.isTableEmpty()) return tt;
			
		int rp = tt.getRandomCellNotEmpty();
		int ri = rp/tt.getHeight();
		int rj = rp%tt.getHeight();
		
		int ii = tt.getTable()[ri][rj].getInside();
		
		if (ii == ii1) tt.getTableToChange()[ri][rj].setInside(ii2);
		if (ii == ii2) tt.getTableToChange()[ri][rj].setInside(ii1);
		
		return tt;
	}
	
	public Table33 deviateColor(int ic1, int ic2, Table33 t) {
		
		Table33 tt = t.copyTable33();
		
		if (t.isTableEmpty()) return tt;
			
		int rp = tt.getRandomCellNotEmpty();
		int ri = rp/tt.getHeight();
		int rj = rp%tt.getHeight();
		
		int ic = tt.getTable()[ri][rj].getColorInt();
		
		if (ic == ic1) tt.getTableToChange()[ri][rj].setColor(ic2);
		if (ic == ic2) tt.getTableToChange()[ri][rj].setColor(ic1);
		
		return tt;
	}
	
	public Table33 death(Table33 t) {
		
		Table33 tt = t.copyTable33();
		
		if (t.isTableEmpty()) return tt;
		
		int rp = tt.getRandomCellNotEmpty();
		int ri = rp/tt.getHeight();
		int rj = rp%tt.getHeight();
		
		Picture p = Picture.setShape(Picture.EMPTY);
		tt.getTableToChange()[ri][rj] = p;
		
		return tt;
	}
	
	public Table33 birth(Table33 t) {
		
		Table33 tt = t.copyTable33();
		
		if (t.isTableFull()) return tt;
		
		
		
		int rpe = tt.getRandomCellEmpty();
		
		
		
		int rie = rpe/tt.getHeight();
		int rje = rpe%tt.getHeight();
		
		
		int rpf = tt.getRandomCellNotEmpty();
		
		int rif = rpf/tt.getHeight();
		int rjf = rpf%tt.getHeight();

		
		Picture p = tt.getCell(rif, rjf);
		
		tt.getTableToChange()[rie][rje] = p;
		
		return tt;
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
				default:
			}
		return tt;
	}

}