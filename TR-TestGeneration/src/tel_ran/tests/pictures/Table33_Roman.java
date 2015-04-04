package tel_ran.tests.pictures;

import tel_ran.tests.tools.RandFunc;


public class Table33_Roman extends Table33 {
	
	public Table33 t;
	
	int orientation;
	int corner;
	
	public Table33_Roman(){
		
		super();
		
		t = new Table33();
		
		t.setEmptyTable();
		
		orientation = RandFunc.IntRandomInRange(0, 1);
		corner = RandFunc.IntRandomInRange(0, 3);
		
		Picture p1 = Picture.setRandomPicture(true);
		Picture p2;
		do {
			p2 = Picture.setRandomPicture(true);
		}while (p1.equals(p2));
		
		t.table[0][0] = p1;
		if (orientation == 0) t.table[0][1] = p2;
		if (orientation == 1) t.table[1][0] = p2;
	}

	public Table33 getT() {
		return t;
	}

	public int getOrientation() {
		return orientation;
	}

	public int getCorner() {
		return corner;
	}
	
	
	
}
