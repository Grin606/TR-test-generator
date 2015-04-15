package tel_ran.tests.pictures;

public class Table33 extends Table {
	
	public static final int NUMBER_OF_STEPS = 14;
	
	public Table33() {
		super(3,3); 
	}
	
	public Table33(PictureN[][] a) {
		super(3,3);
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				table[i][j] = a[i][j].copyPicture();
			}
		}
	}

	public Table33 copyTable33() {
		
		Table33 res = new Table33 ();
			
		for (int i=0; i< height; i++) {
			for (int j= 0; j<width; j++) {
				res.table[i][j] = table[i][j].copyPicture();
			}
		}
		
		return res;
	}
	// movements 1 level
	
		//snakes
	
	public void snakeClockwise() {
		
		PictureN tt = table[0][0];
		
		table[0][0] = table[1][0];
		table[1][0] = table[2][0];
		table[2][0] = table[2][1];
		table[2][1] = table[2][2];
		table[2][2] = table[1][2];
		table[1][2] = table[0][2];
		table[0][2] = table[0][1];
		table[0][1] = tt;	
	}
	public void snakeCounterClockwise() {
		
		PictureN tt = table[0][0];
		
		table[0][0] = table[0][1];
		table[0][1] = table[0][2];
		table[0][2] = table[1][2];
		table[1][2] = table[2][2];
		table[2][2] = table[2][1];
		table[2][1] = table[2][0];
		table[2][0] = table[1][0];
		table[1][0] = tt;	
	}
	
	public void snakeZigZagVert() {
		
		PictureN tt = table[2][2];
		
		table[2][2] = table[1][2];
		table[1][2] = table[0][2];
		table[0][2] = table[0][1];
		table[0][1] = table[1][1];
		table[1][1] = table[2][1];
		table[2][1] = table[2][0];
		table[2][0] = table[1][0];
		table[1][0] = table[0][0];
		table[0][0] = tt;
	}
	
	public void snakeZigZagHor() {
		
		PictureN tt = table[2][2];
		
		table[2][2] = table[2][1];
		table[2][1] = table[2][0];
		table[2][0] = table[1][0];
		table[1][0] = table[1][1];
		table[1][1] = table[1][2];
		table[1][2] = table[0][2];
		table[0][2] = table[0][1];
		table[0][1] = table[0][0];
		table[0][0] = tt;
	}
	
	// turns
	
	public void turn90() {
		
		PictureN tt1 = table[0][0];
		PictureN tt2 = table[0][1];
		
		table[0][0] = table[0][2];
		table[0][1] = table[1][2];
		table[0][2] = table[2][2];
		table[1][2] = table[2][1];
		table[2][2] = table[2][0];
		table[2][1] = table[1][0];
		table[2][0] = tt1;
		table[1][0] = tt2;
	}
	
	public void turn180() {
		turn90();
		turn90();
	}
	
	public void turn270() {
		turn90();
		turn90();
		turn90();
	}
	
		// symmetries
	
	public void vertSymmetry() {
		swapColumns(0,2);
	}
	public void horSymmetry() {
		swapRows(0,2);
	}
	
		// transformations 1 level
	
	public void setEmptyToCenter() {
		PictureN p = table[1][1];
		p.setEmptyPicture();
	}
	
	public static Table33 tableStep(Table33 t, int step) {
		
		Table33 tt = t.copyTable33();
		
			switch (step) {
		
				case 0: tt.swapAngles(); break;
				case 1: tt.swapColumns(0, 2); break;
				case 2: tt.swapRows(0, 2); break;
				case 3: tt.stepUp(); break;
				case 4: tt.stepDown(); break;
				case 5: tt.stepLeft(); break;
				case 6: tt.stepRight(); break;
//				case 7: tt.downAndRight(); break;
//				case 8: tt.downAndLeft(); break;
//				case 9: tt.upAndRight(); break;
//				case 10: tt.upAndLeft(); break;
				case 7: tt.snakeClockwise(); break;
				case 8: tt.snakeCounterClockwise(); break;
				case 9: tt.turn90(); break;
				case 10: tt.turn180(); break;
				case 11: tt.turn270(); break;
				case 12: tt.snakeZigZagHor(); break;
				case 13: tt.snakeZigZagVert(); break;

				default:
			}
		return tt;
	}
}
