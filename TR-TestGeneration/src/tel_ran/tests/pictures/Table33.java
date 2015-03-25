package tel_ran.tests.pictures;

public class Table33 extends Table {
	
	public Table33() {
		super(3,3); 
	}
	
	public Table33(Picture[][] a) {
		super(3,3);
		table = a;
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
		table[1][1] = Picture.setShape(0);
	}
	
}
