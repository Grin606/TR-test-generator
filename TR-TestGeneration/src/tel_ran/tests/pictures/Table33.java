package tel_ran.tests.pictures;

public class Table33 extends Table {
	
	public Table33() {
		super(3,3); 
	}
	
	public Table33(Picture[][] a) {
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
		
		Picture tt = table[0][0];
		
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
		
		Picture tt = table[0][0];
		
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
		
		Picture tt = table[2][2];
		
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
		
		Picture tt = table[2][2];
		
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
		
		Picture tt1 = table[0][0];
		Picture tt2 = table[0][1];
		
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
		table[1][1] = Picture.getPictureShape(0);
	}
	
public static Table33 death(Table33 t) {
		
		Table33 tt = t.copyTable33();
		
		if (t.isTableEmpty()) return tt;
		
		int rp = tt.getRandomCellNotEmpty();
		int ri = rp/tt.getHeight();
		int rj = rp%tt.getHeight();
		
		Picture p = Picture.getPictureShape(Picture.EMPTY);
		tt.getTableToChange()[ri][rj] = p;
		
		return tt;
	}
	
	public static Table33 birth(Table33 t) {
		
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

	
}
