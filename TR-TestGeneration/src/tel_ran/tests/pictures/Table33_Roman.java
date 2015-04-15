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
		
		PictureN p1 = new PictureN();
		p1.setRandomPicture();
		PictureN p2 = new PictureN();
		do {
			p2.setRandomPicture();
		}while (p1.equals(p2));
		
		t.table[0][0] = p1;
		if (orientation == 0) t.table[0][1] = p2;
		if (orientation == 1) t.table[1][0] = p2;
	}

	public int getOrientation() {
		return orientation;
	}

	public int getCorner() {
		return corner;
	}
	
	
	
}
