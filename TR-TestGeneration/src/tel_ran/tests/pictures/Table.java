package tel_ran.tests.pictures;
import java.awt.Color;

import tel_ran.tests.tools.RandFunc;

public class Table {
	
	int height;
	int width;
	
	Picture[][] table;
	
	// constructor

	public Table(int h,int w) {
		table = new Picture[h][w];
		height = h;
		width = w;
	}
	
	public Table (Picture[][] pp) {
		
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
				if (!this.table[i][j].equals(obj.table[i][j])) return false;
			}
		}	
		return true;
	}
	
	public Table copyTable() {
		
		Table res = new Table (height, width);
		
		res.height = height;
		res.width = width;
		
		for (int i=0; i< height; i++) {
			for (int j= 0; j<width; j++) {
				res.table[i][j] = table[i][j].copyPicture();
			}
		}
		
		return res;
	}
	
	public boolean contains(Picture p) {
		
		if (p==null) return false;
		
		Picture tt;
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				tt = table[i][j];
				if (tt != null && tt.equals(p)) return true;
			}
		}
		return false;
	}
	
	public void setCell(int i, int j, Picture p) {
		table[i][j] = p;
	}
	
	// general Table setters
	
	public void setRandomTable() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				table[i][j] = Picture.setRandomPicture(true);
			}
		}	
	}
	
	public void setRandomAllDifferentCellsTable() {
		
		Picture p;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				while(true) {
					p = Picture.setRandomPicture(true);
					if (!contains(p)) break;
				}
				table[i][j] = p;
			}
		}	
	}
	
	public void setEmptyTable() {
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				table[i][j] = Picture.setShape(Picture.EMPTY);
			}
		}	
	}
	
	// 1-x-x setters
	
	public void setOneShapeTable(int is) {
		
		Picture p;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				p = Picture.setShape(is);
				if (is != Picture.EMPTY) {
					p.setRandomColor();
					p.setRandomInside();
				}
				table[i][j] = p;
			}
		}
	}	
	
	public void setOneColorTable(Color color) {
		
		Picture p;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				p = Picture.setRandomShape(true);
				p.setColor(color);
				p.setRandomInside();
				table[i][j] = p;
			}
		}
	}
	public void setOneColorTable(int ic) {
		setOneColorTable(Picture.colorByInt(ic));
	}

	public void setOneInsideTable(int ii) {
		
		Picture p;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				p = Picture.setRandomShape(true);
				p.setRandomColor();
				p.setInside(ii);
				table[i][j] = p;
			}
		}
	}
	
	//2-1-1 table setters
	
	public void setTwoColorsOneShapeOneInside(Color c1, Color c2, int is, int ii){

		Picture p;
		int rc;

		if (is == Picture.EMPTY) {
			setEmptyTable();
			return;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				p = Picture.setShape(is);
				rc = RandFunc.getRandomOfTwo(0, 1);
				switch (rc) {
				case 0: 
					p.setColor(c1);
					break;
				case 1:
					p.setColor(c2);
					break;
				}
				p.setInside(ii);
				table[i][j] = p;
			}
		}	
	}
	public void setTwoColorsOneShapeOneInside(int c1, int c2, int is, int ii) {
		setTwoColorsOneShapeOneInside(Picture.colorByInt(c1), Picture.colorByInt(c2), is, ii);
	}
	public void setTwoColorsOneShapeOneInsidePlusEmpty(Color c1, Color c2, int is, int ii){

		Picture p;
		int rc;

		if (is == Picture.EMPTY) {
			setEmptyTable();
			return;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (Math.random() < 0.33333) p = Picture.setShape(Picture.EMPTY);
				else {
					p = Picture.setShape(is);
					rc = RandFunc.getRandomOfTwo(0, 1);
					switch (rc) {
					case 0: 
						p.setColor(c1);
						break;
					case 1:
						p.setColor(c2);
						break;
					}
				p.setInside(ii);
				}
				table[i][j] = p;
			}
		}	
	}
	public void setTwoColorsOneShapeOneInsidePlusEmpty(int c1, int c2, int is, int ii) {
		setTwoColorsOneShapeOneInsidePlusEmpty(Picture.colorByInt(c1), Picture.colorByInt(c2), is, ii);
	}	
	public void setTwoInsidesOneShapeOneColor(int ii1, int ii2, int is, Color color){

		Picture p;
		int ri;
		
		if (is == Picture.EMPTY) {
			setEmptyTable();
			return;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				p = Picture.setShape(is);
				ri = RandFunc.getRandomOfTwo(0, 1);
				switch (ri) {
				case 0: 
					p.setInside(ii1);
					break;
				case 1:
					p.setInside(ii2);
					break;
				}
				p.setColor(color);
				table[i][j] = p;
			}
		}	
	}
	
	public void setTwoInsidesOneShapeOneColor(int ii1, int ii2, int is, int ic){
		setTwoInsidesOneShapeOneColor(ii1, ii2, is, Picture.colorByInt(ic));
	}
	public void setTwoInsidesOneShapeOneColorPlusEmpty(int ii1, int ii2, int is, Color color){

		Picture p;
		int rc;

		if (is == Picture.EMPTY) {
			setEmptyTable();
			return;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (Math.random() < 0.33333) p = Picture.setShape(Picture.EMPTY);
				else {
					p = Picture.setShape(is);
					rc = RandFunc.getRandomOfTwo(0, 1);
					switch (rc) {
						case 0: 
							p.setInside(ii1);
							break;
						case 1:
							p.setInside(ii2);
							break;
					}
					p.setColor(color);
				}
				table[i][j] = p;
			}
		}	
	}

	public void setTwoInsidesOneShapeOneColorPlusEmpty(int ii1, int ii2, int is, int ic) {
		setTwoInsidesOneShapeOneColorPlusEmpty(ii1, ii2, is, Picture.colorByInt(ic));
	}
	
	public void setTwoShapesOneInsideOneColor(int is1, int is2, int ii, Color color){

		Picture p;
		int rs;
		int is=is1;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				rs = RandFunc.getRandomOfTwo(0, 1);
				switch (rs) {
				case 0: 
					is = is1;
					break;
				case 1:
					is = is2;
					break;
				}
				p = Picture.setShape(is);
				if (is != Picture.EMPTY) {
					p.setInside(ii);
					p.setColor(color);
				}
				table[i][j] = p;
			}
		}	
	}
	
	public void setTwoShapesOneInsideOneColor(int ii1, int ii2, int is, int ic){
		setTwoShapesOneInsideOneColor(ii1, ii2, is, Picture.colorByInt(ic));
	}
	
	public void setTwoShapesOneInsideOneColorPlusEmpty(int is1, int is2, int ii, Color color){

		if (is1 == Picture.EMPTY || is2 == Picture.EMPTY ) {
			setEmptyTable();
			return;
		}
		
		Picture p;
		int rs;
		int is=is1;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				if (Math.random() < 0.33333) p = Picture.setShape(Picture.EMPTY);
				else {
					rs = RandFunc.getRandomOfTwo(0, 1);
					switch (rs) {
						case 0: 
							is = is1;
							break;
						case 1:
							is = is2;
							break;
					}
				p = Picture.setShape(is);
				p.setInside(ii);
				p.setColor(color);
				}
				table[i][j] = p;
			}
		}	
	}
	
	public void setTwoShapesOneInsideOneColorPlusEmpty(int is1, int is2, int ii, int ic){
		setTwoShapesOneInsideOneColorPlusEmpty(is1, is2, ii, Picture.colorByInt(ic));
	}

// 3-1-1 table setters

	public void setThreeColorsOneShapeOneInside(Color c1, Color c2, Color c3, int is, int ii){

		Picture p;
		int rc;
		
		if (is == Picture.EMPTY) {
			setEmptyTable();
			return;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				p = Picture.setShape(is);
				rc = RandFunc.getRandomOfThree(0, 1, 2);
				switch (rc) {
				case 0: 
					p.setColor(c1);
					break;
				case 1:
					p.setColor(c2);
					break;
				case 2:
					p.setColor(c3);
					break;
				}
				p.setInside(ii);
				table[i][j] = p;
			}
		}	
	}
	public void setThreeColorsOneShapeOneInside(int c1, int c2, int c3, int is, int ii) {
		setThreeColorsOneShapeOneInside(Picture.colorByInt(c1), Picture.colorByInt(c2), Picture.colorByInt(c3), is, ii);
	}
	
		
	public void setThreeInsidesOneShapeOneColor(int ii1, int ii2, int ii3, int is, Color color){

		Picture p;
		int ri;
	
		if (is == Picture.EMPTY) {
			setEmptyTable();
			return;
		}
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				p = Picture.setShape(is);
				ri = RandFunc.getRandomOfThree(0, 1,2);
				switch (ri) {
				case 0: 
					p.setInside(ii1);
					break;
				case 1:
					p.setInside(ii2);
					break;
				case 2:
					p.setInside(ii3);
					break;
				}
				p.setColor(color);
				table[i][j] = p;
			}
		}	
	}
	
	public void setThreeInsidesOneShapeOneColor(int ii1, int ii2, int ii3, int is, int ic){
		setThreeInsidesOneShapeOneColor(ii1, ii2, ii3, is, Picture.colorByInt(ic));
	}
	
	public void setThreeShapesOneInsideOneColor(int is1, int is2, int is3, int ii, Color color){

		Picture p;
		int rs;
		int is=is1;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				rs = RandFunc.getRandomOfThree(0, 1, 2);
				switch (rs) {
				case 0: 
					is = is1;
					break;
				case 1:
					is = is2;
					break;
				case 2:
					is = is3;
					break;
				}
				p = Picture.setShape(is);
				if (is != Picture.EMPTY) {
					p.setInside(ii);
					p.setColor(color);
				}
				table[i][j] = p;
			}
		}	
	}
	
	public void setThreeShapesOneInsideOneColor(int ii1, int ii2, int ii3, int is, int ic){
		setThreeShapesOneInsideOneColor(ii1, ii2, ii3, is, Picture.colorByInt(ic));
	}
	
	// 2-2-1 table setters
	
	public void setTwoShapesTwoColorsOneInside(int is1, int is2, Color c1, Color c2, int ii){
		
		Picture p;
		int rs;
		int rc;
		int is=is1;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				rs = RandFunc.getRandomOfTwo(0, 1);
				switch (rs) {
				case 0: 
					is = is1;
					break;
				case 1:
					is = is2;
					break;
				}
				p = Picture.setShape(is);
				
				if (is != Picture.EMPTY) {
					
					rc = RandFunc.getRandomOfTwo(0, 1);
					switch (rc) {
					case 0: p.setColor(c1);break;
					case 1: p.setColor(c2);break;
					}
					
					p.setInside(ii);
				}
				table[i][j] = p;
			}
		}	
	}
	
	public void setTwoShapesTwoColorsOneInside(int is1, int is2, int ic1, int ic2, int ii){
		setTwoShapesTwoColorsOneInside(is1, is2, Picture.colorByInt(ic1), Picture.colorByInt(ic2), ii);
	}
	
	public void setTwoShapesTwoInsidesOneColor(int is1, int is2, Color c, int ii1, int ii2) {
		
		Picture p;
		int rs;
		int ri;
		int is=is1;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				rs = RandFunc.getRandomOfTwo(0, 1);
				switch (rs) {
				case 0: 
					is = is1;
					break;
				case 1:
					is = is2;
					break;
				}
				p = Picture.setShape(is);
				
				if (is != Picture.EMPTY) {
					
					ri = RandFunc.getRandomOfTwo(0, 1);
					switch (ri) {
					case 0: p.setInside(ii1);break;
					case 1: p.setInside(ii2);break;
					}
					
					p.setColor(c);
				}
				table[i][j] = p;
			}
		}	
	}
	
	public void setTwoShapesTwoInsidesOneColor(int is1, int is2, int ic, int ii1, int ii2) {
		setTwoShapesTwoInsidesOneColor(is1, is2, Picture.colorByInt(ic), ii1, ii2);
	}
	
	public void setTwoColorsTwoInsidesOneShape(int is, Color c1, Color c2, int ii1, int ii2) {
		
		Picture p;
		int rc;
		int ri;
		
		if (is == Picture.EMPTY) {
			setEmptyTable();
			return;
		}
		
		p = Picture.setShape(is);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				
				rc = RandFunc.getRandomOfTwo(0, 1);
				switch (rc) {
				case 0: p.setColor(c1);break;
				case 1: p.setColor(c2);break;
				}
					
				ri = RandFunc.getRandomOfTwo(0, 1);
				switch (ri) {
				case 0: p.setInside(ii1);break;
				case 1: p.setInside(ii2);break;
				}
				
				table[i][j] = p.copyPicture();
			}
		}	
	}
	
	public void setTwoColorsTwoInsidesOneShape(int is, int ic1, int ic2, int ii1, int ii2) {
		setTwoColorsTwoInsidesOneShape(is, Picture.colorByInt(ic1), Picture.colorByInt(ic2), ii1, ii2);
	}
	
	// movements 1 level
	
		// swaps
	
	public void swap(int i1,int j1,int i2,int j2) {
		
		Picture p = table[i1][j1];
		table[i1][j1] = table[i2][j2];
		table[i2][j2] = p;
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
		
		Picture[] tt;
		
		int h1 = height-1;
		tt = table[0];
		for (int i = 0; i < h1; i++) table[i] = table[i+1];
		table[h1] = tt;
	}
	public void stepDown() {
		
		Picture[] tt;
		
		int h1 = height-1;
		tt = table[h1];
		for (int i = h1; i > 0; i--) table[i] = table[i-1];
		table[0] = tt;
	}
	
	public void stepLeft() {
		
		Picture tt;
		
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
		
		Picture tt;
		
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
	
	public void changeColor(int i, int j, Color color) {
		table[i][j].setColor(color);
	}
	
	public void changeColor(int i, int j, int ic) {
		changeColor (i,j,Picture.colorByInt(ic));
	}
	
	public void swapColors (Color c1, Color c2){
				
		for (int i=0; i<height; i++) {
			for (int j = 0; j<width; j++) {
				if (table[i][j].getColor() == c1) 
					table[i][j].setColor(c2);
				else if (table[i][j].getColor() == c2) 
					table[i][j].setColor(c1);
			}
		}	
	}
	
	public void swapColors (int c1, int c2){
		swapColors(Picture.colorByInt(c1), Picture.colorByInt(c2));
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
	
	public void swapCrosses() {
		swapInsides(Picture.INSIDE_ERECT_CROSS, Picture.INSIDE_OBLIQUE_CROSS);
	}
	public void swapEmptyAndFull() {
		swapInsides(Picture.INSIDE_EMPTY, Picture.INSIDE_FULL);
	}
	public void swapErectLines() {
		swapInsides(Picture.INSIDE_HORLINE, Picture.INSIDE_VERTLINE);
	}
	public void swapObliqueLines() {
		swapInsides(Picture.INSIDE_NE_SW_LINE,Picture.INSIDE_NW_SE_LINE);
	}
	
	 // shapes
	
	public void changeShape(int i, int j, int is) {
		
		Picture t = table[i][j];
		
		if (t.getShape() == is) return;
		
		Picture p;
		
		p = Picture.setShape(is);
		
		if (is != Picture.EMPTY) {
			p.setColor(t.getColor());
			p.setInside(t.getInside());
		}
		table[i][j] = p;
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
		
		public void colorSequenceStep(Color[] colorSeq) {
		
		Table tt = copyTable();
		
		int csL1 = colorSeq.length-1;
		for (int ic = 0; ic < csL1; ic++) {
			for (int i = 0; i<height; i++) {
				for (int j=0; j<width; j++) {
					if (table[i][j].getColor() == colorSeq[ic]) {
						tt.changeColor(i, j, colorSeq[ic]);
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
	
	public void colorSequenceStep(int[] colorSeq) {
		Color[] cs = new Color[colorSeq.length];
		for (int i = 0; i < colorSeq.length; i++) {
			cs[i] = Picture.colorByInt(colorSeq[i]);
		}
		colorSequenceStep(cs);
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
	
	public Picture[][] getTable(){
		
		return this.copyTable().table;
	}
	
	public Picture[][] getTableToChange(){
		return this.table;
	}
	
	public Picture getCell(int i, int j) {
		
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
		
		int rp = getRandomCellNotEmpty();
		int ri = rp/tt.height;
		int rj = rp%tt.height;
		
		int ic = this.table[ri][rj].getColorInt();
		
		tt.table[ri][rj].setColor(RandFunc.IntRandomInRangeExept(0, Picture.NUMBER_OF_COLORS-1,ic));
		
		return tt;
	}
	
	public Table deviateInside() {
		
		if (isTableEmpty()) return copyTable();
		
		Table tt = this.copyTable();
		
		int rp = getRandomCellNotEmpty();
		int ri = rp/tt.height;
		int rj = rp%tt.height;
		
		int ii = this.table[ri][rj].getInside();
		
		tt.table[ri][rj].setInside(RandFunc.IntRandomInRangeExept(1, Picture.NUMBER_OF_INSIDES-1,ii));
		
		return tt;
	}
	
	public Table deviateShape() {
		
		if (isTableEmpty()) return copyTable();
		
		Table tt = this.copyTable();
		
		int rp = getRandomCellNotEmpty();
		int ri = rp/tt.height;
		int rj = rp%tt.height;
		
		int is = this.table[ri][rj].getShape();
		int ic = this.table[ri][rj].getColorInt();
		int ii = this.table[ri][rj].getInside();
		
		Picture p = Picture.setShape(RandFunc.IntRandomInRangeExept(1, Picture.NUMBER_OF_SHAPES-1,is));
		p.setColor(ic);
		p.setInside(ii);
		
		tt.table[ri][rj] = p;
		
		return tt;
	}
	
	public int getRandomCellNotEmpty() {
		
		while(true) {
			int rp = RandFunc.IntRandomInRange(0,height*(width-1));
			int ri = rp/height;
			int rj = rp%height;
			if (table[ri][rj].getShape() != Picture.EMPTY) return rp;
		}
	}
	
	public int getRandomCellEmpty() {
		
		while(true) {
			int rp = RandFunc.IntRandomInRange(0,height*(width-1));
			int ri = rp/height;
			int rj = rp%height;
			if (table[ri][rj].getShape() == Picture.EMPTY) return rp;
		}
	}
	
	public int countColors() {
		
		int[] c = new int[Picture.NUMBER_OF_COLORS];
		
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				if (table[i][j].getShape() != Picture.EMPTY) c[table[i][j].getColorInt()]++;
			}
		}
		
		int res = 0;
		for (int i=0; i<c.length; i++) {
			if (c[i] != 0)res++;		
		}
		
		return res;
	}
	
	public int countInsides() {
		
		int[] c = new int[Picture.NUMBER_OF_INSIDES];
		
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				if (table[i][j].getShape() != Picture.EMPTY) c[table[i][j].getInside()]++;
			}
		}
		
		int res = 0;
		for (int i=0; i<c.length; i++) {
			if (c[i] != 0)res++;		
		}
		
		return res;
	}
	
	public int countShapes() {
		
		int[] c = new int[Picture.NUMBER_OF_SHAPES];
		
		for (int i=0; i<height; i++) {
			for (int j=0; j<width; j++) {
				c[table[i][j].getShape()]++;
			}
		}
		
		int res = 0;
		for (int i=1; i<c.length; i++) {
			if (c[i] != 0)res++;		
		}
		
		return res;
	}
	
	public boolean isTableEmpty() {
		
		for (int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				if (table[i][j].getShape() != Picture.EMPTY) return false;
			}
		}
		return true;
	}
	
	public boolean isTableFull() {
		
		for (int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				if (table[i][j].getShape() == Picture.EMPTY) return false;
			}
		}
		return true;
	}
	
	public void display () {
		
		System.out.println("=====================================================================================================================");
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width-1; j++) {
				table[i][j].draw(null,0,0, 100);
				System.out.print("  ||   ");
			}
			table[i][width-1].draw(null, 0,0, 100);
			System.out.println("\n=====================================================================================================================");
		}
	}
}
