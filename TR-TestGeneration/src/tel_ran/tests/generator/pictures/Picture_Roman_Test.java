package tel_ran.tests.generator.pictures;

import tel_ran.tests.pictures.PictureN;

import tel_ran.tests.pictures.Table;
import tel_ran.tests.pictures.Table33;
import tel_ran.tests.pictures.Table33_Roman;
import tel_ran.tests.tools.RandFunc;
	
	public class Picture_Roman_Test extends PictureTablesTest{
		
		final int PROBLEM_LENGHT = 4;
		
		public Picture_Roman_Test() {
			
			super();			
			name = "Table Roman test";
			problemLength = PROBLEM_LENGHT;
		}

		@Override
		public void generate(int difficultyLevel) {
		
			Table33_Roman ti = new Table33_Roman();
			Table33 t = ti.t;
			
			uploadProblemAndAnswers(t);
			makeProblem(t, problem, answers, ti.getOrientation(), ti.getCorner());
			makeAnswers(answers, ti.getCorner());
			setDataSet();
		}
		
	 public void makeProblem(Table33 init, PictureN[][][] pr, PictureN[][][] a, int orientation, int corner) {
			
			Table33 tt = new Table33(init.getTable());
			
			pr[0] = getTableRoman(tt,corner);
			
			int kind = RandFunc.getRandomOfTwo(0, 1);
			
		    for (int i=1; i < PROBLEM_LENGHT; i++) {	
		    
		    tt = tableStep(tt,kind,orientation);
		    
		    pr[i] = getTableRoman(tt,corner);
		    }
		    
		    tt = tableStep (tt,kind,orientation);
		    a[0] = tt.getTable();
		    
		}
		
		public void makeAnswers(PictureN[][][] answers, int corner) {
			
			Table tt;
			
			PictureN[][] a = answers[0];
			Table t = new Table(a);
			answers[0] = getTableRoman(t,corner);
			
			tt = Table.birth(t);           // Wrong answers
			answers[1] = getTableRoman(tt, corner);
			
			tt = Table.death(t);
			answers[2] = getTableRoman(tt,corner);

		    tt = t.copyTable();
			tt.swap(0, 0, 1, 0);
			answers[3] = getTableRoman(tt,corner);
		}
		
		public PictureN[][] getTableRoman(Table t, int corner) {
			
			Table33 tt = new Table33(t.getTable());	
			for (int i= 0; i < corner; i++) tt.turn90();	
			return tt.getTable();
		}
		
		public Table33 tableStep(Table33 t, int kind, int orientation) {
			
			PictureN[][] pp; 
			
			Table33 tt = t.copyTable33();
			if (orientation == 1) {
				if (kind == 0) tt.snakeCounterClockwise();
				else tt.snakeZigZagVert();
				pp = tt.getTableToChange();
				pp[0][0] = pp[2][0].copyPicture();
			}
			else {
				if (kind == 0) tt.snakeClockwise();
				else tt.snakeZigZagHor();
				pp = tt.getTableToChange();
				pp[0][0] = pp[0][2].copyPicture();
			}
			return tt;
		}
		
		



}
