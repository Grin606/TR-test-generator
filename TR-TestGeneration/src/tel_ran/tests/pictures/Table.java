package tel_ran.tests.pictures;
import tel_ran.tests.tools.RandFunc;

public class Table {
	
	private static final double EMPTY_PROBABILITY = 0.333333;
	int height;
	int width;
	
	private final int empty = Picture.EMPTY; 
	private final int nS = PictureN.NUMBER_OF_SHAPES-1;
	private final int nC = PictureN.NUMBER_OF_COLORS-1;
	private final int nI = PictureN.NUMBER_OF_INSIDES-1;
	
	PictureN[][] table;
	
	// constructor

	public Table(int h,int w) {
		table = new PictureN[h][w];
		height = h;
		width = w;
	}
	
	public Table(PictureN[][] pp) {
		
		height = pp.length;
		width = pp[0].length;
		
		table = pp;
	}
	
	// copy, equals, contains and setCell
	
	public boolean equals(Table obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		Table other = (Table) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (!this.table[i][j].equals(other.table[i][j])) return false;
			}
		}	
		return true;
	}
	
	public Table copyTable() {
		
		Table res = new Table (height, width);
				
		for (int i=0; i< height; i++) {
			for (int j= 0; j<width; j++) {
				res.setCell(i,j,table[i][j]);
			}
		}
		return res;
	}
	
	public boolean contains(PictureN pic) {
		
		if (pic==null) return false;
		
		PictureN p;
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				p = table[i][j];
				if (p != null && p.equals(pic)) return true;
			}
		}
		return false;
	}
	
	public void setCell(int i, int j, PictureN p) {
		table[i][j] = p.copyPicture();
	}
	
	public void setCell (int i, int j, int iShape, int iColor, int iInside) {
		
		PictureN p = new PictureN();
		
		p.setShape(iShape);
		if (iShape != empty) {
			p.setColor(iColor);
			p.setInside(iInside);
		}
		
		setCell (i,j,p);
	}
	
	// general Table setters
	
	public void setRandomTable() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				PictureN p = new PictureN();
				p.setRandomPicture();
				setCell(i,j,p);
			}
		}	
	}
	
	public void setRandomAllDifferentCellsTable() {
		
		PictureN p = new PictureN();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				do {					
					p.setRandomPicture();
				}while(contains(p));
				setCell(i,j,p);
			}
		}	
	}
	
	public void setEmptyTable() {
		
		PictureN p = new PictureN();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				p.setEmptyPicture();
				setCell(i,j, p);
			}
		}	
	}
	
	public void seedEmpty() {
		
		PictureN p = new PictureN();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (Math.random() < EMPTY_PROBABILITY) {
					p.setEmptyPicture();
					setCell(i,j, p);
				}	
			}
		}
	}
	
	// 1-x-x setters
	
	public void setOneShapeTable(int is) {
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i,j,is,RandFunc.IntRandomInRange(0, nC-1),RandFunc.IntRandomInRange(0, nI));
			}
		}
	}	
	
	public void setOneColorTable(int ic) {
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i,j,RandFunc.IntRandomInRange(1, nS), ic, RandFunc.IntRandomInRange(0, nI));
			}
		}
	}

	public void setOneInsideTable(int ii) {
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i,j,RandFunc.IntRandomInRange(1, nS),RandFunc.IntRandomInRange(0, nC), ii);
			}
		}
	}
	
	//2-1-1 table setters
	
	public void setTwoColorsOneShapeOneInside(int ic1, int ic2, int is, int ii){
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i,j,is,RandFunc.getRandomOfTwo(ic1, ic2), ii);
			}
		}	
	}
	public void setTwoColorsOneShapeOneInsidePlusEmpty(int ic1, int ic2, int is, int ii){
		setTwoColorsOneShapeOneInside(ic1, ic2, is, ii);
		seedEmpty();
	}
	
	public void setTwoInsidesOneShapeOneColor(int ii1, int ii2, int is, int ic){
	
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i,j,is,ic,RandFunc.getRandomOfTwo(ii1, ii2));
			}
		}	
	}
	
	public void setTwoInsidesOneShapeOneColorPlusEmpty(int ii1, int ii2, int is, int ic){		
		setTwoInsidesOneShapeOneColor(ii1, ii2, is, ic);
		seedEmpty();
	}
	
	public void setTwoShapesOneInsideOneColor(int is1, int is2, int ii, int ic){

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i,j,RandFunc.getRandomOfTwo(is1, is2), ic,ii);
			}
		}	
	}
	
	public void setTwoShapesOneInsideOneColorPlusEmpty(int is1, int is2, int ii, int ic){	
		setTwoShapesOneInsideOneColor(is1, is2, ii, ic);
		seedEmpty();
	}
	
// 3-1-1 table setters

	public void setThreeColorsOneShapeOneInside(int ic1, int ic2, int ic3, int is, int ii){

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i, j, is, RandFunc.getRandomOfThree(ic1, ic2, ic3), ii);
			}
		}	
	}	
		
	public void setThreeInsidesOneShapeOneColor(int ii1, int ii2, int ii3, int is, int ic){
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell (i,j,is,ic,RandFunc.getRandomOfThree(ii1, ii2, ii3));
			}
		}	
	}
	
	public void setThreeShapesOneInsideOneColor(int is1, int is2, int is3, int ii, int ic){

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					setCell (i,j,RandFunc.getRandomOfThree(is1, is2, is3), ic, ii);
			}
		}	
	}
		
	// 2-2-1 table setters
	
	public void setTwoShapesTwoColorsOneInside(int is1, int is2, int ic1, int ic2, int ii){
				
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i,j,RandFunc.getRandomOfTwo(is1, is2), RandFunc.getRandomOfTwo(ic1, ic2), ii);
			}
		}	
	}
	
	public void setTwoShapesTwoInsidesOneColor(int is1, int is2, int ic, int ii1, int ii2) {
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i,j,RandFunc.getRandomOfTwo(is1, is2), ic, RandFunc.getRandomOfTwo(ii1, ii2));
			}
		}	
	}
	
	public void setTwoColorsTwoInsidesOneShape(int is, int ic1, int ic2, int ii1, int ii2) {
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				setCell(i,j,is,RandFunc.getRandomOfTwo(ic1, ic2),RandFunc.getRandomOfTwo(ii1, ii2));
			}
		}	
	}
		
	// movements 1 level
	
		// swaps
	
	public void swap(int i1,int j1,int i2,int j2) {
		
		PictureN p = table[i1][j1];
		setCell(i1, j1, table[i2][j2]);
		setCell(i2,j2,p);
	}
	public void swapNW_SE() {
		swap(0,0,height-1,width-1);
	}
	public void swapNE_SW() {
		swap(0, width-1, height-1,0);
	}
	public void swapAngles() {
		swapNW_SE();
		swapNE_SW();
	}
	public void swapColumns(int j1, int j2) {
		for (int i=0; i<height; i++) {
			swap(i,j1,i,j2);
		}
	}
	public void swapRows(int i1, int i2) {
		for (int j=0; j<width; j++) {
			swap (i1,j,i2,j);
		}
	}
	
	   // steps
	
	public void stepUp() {
		
		PictureN[] tt;
		
		int h1 = height-1;
		tt = table[0];
		for (int i = 0; i < h1; i++) table[i] = table[i+1];
		table[h1] = tt;
	}
	public void stepDown() {
		
		PictureN[] tt;
		
		int h1 = height-1;
		tt = table[h1];
		for (int i = h1; i > 0; i--) table[i] = table[i-1];
		table[0] = tt;
	}
	
	public void stepLeft() {
		
		PictureN tt;
		
		for (int i=0; i<height; i++) {
			
			tt = table[i][0];
			int w1 = width-1;
			for (int j = 0; j<w1; j++) {
				table[i][j] = table[i][j+1];
			}
			table[i][w1] = tt;
		}
	}
	
	public void stepRight() {
		
		PictureN tt;
		
		for (int i=0; i<height; i++) {
			
			int w1 = width-1;
			tt = table[i][w1];
			for (int j = w1; j>0; j--) {
				table[i][j] = table[i][j-1];
			}
			table[i][0] = tt;
		}	
	}
		
	//  transformations 1 level
	
		// colors
	
	public void changeColor(int i, int j, int color) {
		table[i][j].setColor(color);
	}
		
	public void swapColors (int ic1, int ic2){
				
		for (int i=0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				if (table[i][j].getColor() == ic1) 
					table[i][j].setColor(ic2);
				else if (table[i][j].getColor() == ic2) 
					table[i][j].setColor(ic1);
			}
		}	
	}
	
		// insides
	
	public void changeInside(int i, int j, int ii) {
		table[i][j].setInside(ii);
	}
	
	public void swapInsides(int i1, int i2) {
		
		for (int i=0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				if (table[i][j].getInside() == i1) table[i][j].setInside(i2);
				else if (table[i][j].getInside() == i2) table[i][j].setInside(i1);
			}
		}
	}
		
	 // shapes
	
	public void changeShape(int i, int j, int is) {
		
		PictureN p = table[i][j];
		p.setShape(is);
		
	}

	public void changeShapes(int is1, int is2) {
		
		for (int i=0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				if (table[i][j].getShape() == is1) changeShape(i,j,is2);
			}
		}
	}
	
	public void swapShapes(int is1, int is2) {
		
		for (int i=0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				if (table[i][j].getShape() == is1) changeShape(i,j,is2);
				else if (table[i][j].getShape() == is2) changeShape(i,j,is1);
			}
		}
	}

	// movements 2 level
	
			//double steps
		
		public void downAndRight() {
			stepDown();
			stepRight();	
		}
		public void downAndLeft() {
			stepDown();
			stepLeft();
		}
		public void upAndRight() {
			stepUp();
			stepRight();
		}
		public void upAndLeft() {
			stepUp();
			stepLeft();
		}

	// transformations 2 level
	
		// sequences
		
		public void colorSequenceStep(int[] colorSeq) {
		
		Table tt = copyTable();
		
		int csL1 = colorSeq.length-1;
		for (int ic = 0; ic < csL1; ic++) {
			for (int i = 0; i<height; i++) {
				for (int j=0; j<width; j++) {
					if (table[i][j].getColor() == colorSeq[ic]) {
						tt.changeColor(i, j, colorSeq[ic+1]);
					}
				}
			}
		}
		
		for (int i = 0; i<height; i++) {
			for (int j=0; j<width; j++) {
				if (table[i][j].getColor() == colorSeq[csL1]) {
					tt.changeColor(i,j,colorSeq[0]);
				}
			}
		}
		table = tt.table;
	}
		
	public void insideSequenceStep(int[] insideSeq) {
		
	Table tt = copyTable();
		
		int isL1 = insideSeq.length-1;
		for (int ii = 0; ii < isL1; ii++) {
			for (int i = 0; i<height; i++) {
				for (int j=0; j<width; j++) {
					if (table[i][j].getInside() == insideSeq[ii]) {
						tt.table[i][j].setInside(insideSeq[ii+1]);;
					}
				}
			}
		}
		
		for (int i = 0; i<height; i++) {
			for (int j=0; j<width; j++) {
				if (table[i][j].getInside() == insideSeq[isL1]) {
					tt.table[i][j].setInside(insideSeq[0]);;
				}
			}
		}
		table = tt.table;
	}	
			
	public void shapeSequenceStep(int[] shapeSeq) {
		
		Table tt = copyTable();
		
		int ssL1 = shapeSeq.length-1;
		for (int is = 0; is < ssL1; is++) {
			for (int i = 0; i<height; i++) {
				for (int j=0; j<width; j++) {
					if (table[i][j].getShape() == shapeSeq[is]) {
						tt.changeShape(i,j,shapeSeq[is+1]);
					}
				}
			}
		}
		
		for (int i = 0; i<height; i++) {
			for (int j=0; j<width; j++) {
				if (table[i][j].getShape() == shapeSeq[ssL1]) {
					tt.changeShape(i,j,shapeSeq[0]);
				}
			}
		}
		table = tt.table;
	}
	
	// getters
	
	public PictureN[][] getTable(){
		
		return (copyTable()).table;
	}
	
	public PictureN[][] getTableToChange(){
		return table;
	}
	
	public PictureN getCell(int i, int j) {
		
		return table[i][j].copyPicture();
	}
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	// deviations

	public Table deviateColor() {
		
		if (isTableEmpty()) return copyTable();
		
		Table tt = this.copyTable();
		PictureN p = tt.getRandomCellNotEmpty();	
		
		p.setColor(RandFunc.IntRandomInRangeExept(0, nC,p.getColor()));
		
		return tt;
	}
	
	public Table deviateColor(int ic1, int ic2, Table t) {
		
		Table tt = t.copyTable();
		
		if (t.isTableEmpty()) return tt;
			
		PictureN p = tt.getRandomCellNotEmpty();
		int ic = p.getColor();
		
		if (ic == ic1) p.setColor(ic2);
		if (ic == ic2) p.setColor(ic1);
		
		return tt;
	}
	
	public Table deviateInside() {
		
		if (isTableEmpty()) return copyTable();
		
		Table tt = this.copyTable();
		PictureN p = tt.getRandomCellNotEmpty();	
		
		p.setInside(RandFunc.IntRandomInRangeExept(0, nI,p.getInside()));
		
		return tt;
	}
	
	public Table deviateInside(int ii1, int ii2, Table t) {
		
		Table tt = t.copyTable();
		
		if (t.isTableEmpty()) return tt;
			
		PictureN p = tt.getRandomCellNotEmpty();
		int ii = p.getInside();
		
		if (ii == ii1) p.setInside(ii2);
		if (ii == ii2) p.setInside(ii1);
		
		return tt;
	}
	
	public Table deviateShape() {
		
		if (isTableEmpty()) return copyTable();
		
		Table tt = this.copyTable();	
		PictureN p = tt.getRandomCellNotEmpty();
		
		p.setShape(RandFunc.IntRandomInRangeExept(0, nS,p.getShape()));
			
		return tt;
	}
	
	public Table deviateShape(int is1, int is2, Table t) {
		
		Table tt = t.copyTable();

		if (t.isTableEmpty()) return tt;
			
		PictureN p = tt.getRandomCellNotEmpty();
		
		int is = p.getShape();
			
		if (is == is1) p.setShape(is2);
		if (is == is2) p.setShape(is1);
	
		return tt;
	}
	
	// birth and death
	
	public static Table death(Table t) {
		
		Table tt = t.copyTable();
		
		if (t.isTableEmpty()) return tt;
		
		PictureN p = tt.getRandomCellNotEmpty();
		p.setEmptyPicture();
		
		return tt;
	}
	
	public static Table birth(Table t) {
		
		Table tt = t.copyTable();
		
		if (t.isTableFull()) return tt;
		
		PictureN pe = tt.getRandomCellEmpty();
	
		if (t.isTableEmpty()) {
			pe.setRandomPicture();
		}
		else {
			PictureN pf = tt.getRandomCellNotEmpty();
			pe.setShape(pf.getShape());
			pe.setColor(pf.getColor());
			pe.setInside(pf.getInside());
		}
		
		return tt;
	}
		
	public PictureN getRandomCellEmpty() {
		
		PictureN p;
		do {
			p = getRandomCell();
		}while (cellNotEmpty(p));
		
		return p;
	}
	
	public PictureN getRandomCellNotEmpty() {
		
		PictureN p;
		do {
			p = getRandomCell();
		}while (!cellNotEmpty(p));
		
		return p;
	}
	
	public boolean cellNotEmpty(int i, int j) {
		return (table[i][j].getShape() != empty);
	}
	
	public boolean cellNotEmpty(PictureN p) {
		return (p.getShape() != empty);
	}
	
	public int countColors() {
		
		int[] c = new int[PictureN.NUMBER_OF_COLORS];
		
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				if (cellNotEmpty(i, j)) c[table[i][j].getColor()]++;
			}
		}
		
		return countNonZero(c);
	}
	
	public int countInsides() {
		
		int[] c = new int[PictureN.NUMBER_OF_INSIDES];
		
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				if (cellNotEmpty(i, j)) c[table[i][j].getInside()]++;
			}
		}
		
		return countNonZero(c);
	}
	
	public int countShapes() {
		
		int[] c = new int[PictureN.NUMBER_OF_SHAPES];
		
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				if (cellNotEmpty(i, j)) c[table[i][j].getShape()]++;
			}
		}
				
		return countNonZero(c);
	}
	
	public int countCellsNotEmpty(){
		
		int counter = 0;
		
		for (int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				if (table[i][j].getShape() != empty) counter++;
			}
		}

		return counter;
	}

	public boolean isTableEmpty() {
		return (countCellsNotEmpty() == 0);
	}
	
	public boolean isTableFull() {
		return (countCellsNotEmpty() == height*width);
	}
	
	private int countNonZero(int[] c){	
		
		int res = 0;
		for (int i : c) if (i != 0) res++;
		return res;
	}
	
	public PictureN getRandomCell() {
		
		int rp = RandFunc.IntRandomInRange(0,height*width-1);
		int ri = rp/height;
		int rj = rp%height;
		
		return table[ri][rj];
	}
	
//	public void display () {
//		
//		System.out.println("=====================================================================================================================");
//		for (int i = 0; i < height; i++) {
//			for (int j = 0; j < width-1; j++) {
//				table[i][j].draw(null,0,0, 100);
//				System.out.print("  ||   ");
//			}
//			table[i][width-1].draw(null, 0,0, 100);
//			System.out.println("\n=====================================================================================================================");
//		}
//	}
}
