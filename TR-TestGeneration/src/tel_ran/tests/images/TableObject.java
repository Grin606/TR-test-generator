package tel_ran.tests.images;

import java.awt.Color;
import java.awt.Graphics2D;

import tel_ran.tests.pictures.Picture;

public class TableObject extends ImageObject {
	
	Fields[][] fields;
	int[] wc;
	int[] hr;
	
	
	
	
	/* ------------------------------------------
	 * Constructor
	 * ------------------------------------------ */	

	public TableObject(Resizing rs, int padding) {
		super(rs, padding);		
		// TODO Auto-generated constructor stub
	}
	
	public TableObject(Fields[][] fields, Resizing rs, int padding) {
		super(rs, padding);
		this.fields = fields;	
		setWcHr();
		
	}
	
	public TableObject(String[][] data, int numFont, Resizing rs, int padding) {
		super(rs, padding);
		objectFill(data, numFont);		
		
	}
	
	public TableObject(Picture[][] data, Resizing rs, int padding) {
		super(rs, padding);
		objectFill(data);
		
	}
	
	public TableObject(Picture[][][] data, Resizing rs, int padding) {
		super(rs, padding);
		objectFill(data);
		
	}
	
	
	
	/* ------------------------------------------
	 * Start setters 
	 * ------------------------------------------ */
	
	
	protected void setWcHr() {
		int col = 0;
		int row = 0;		
		row = fields.length;
		
		for (Fields[] f : fields) {
			if (f.length > col)
				col = f.length;
		}
		
		if (header!=null) {
			row ++;
			if (header.length > col)
				col = header.length;
		}
		
		if (notes != null) {
			row ++;
			if (notes.length > col)
				col = notes.length;
		}
		
		this.wc = new int[col];
		this.hr = new int[row];
	}

	public void objectFill(String[][] strings, int numFont) {
		int size = strings.length;
		this.fields = new Fields[size][];
		for (int i = 0; i < size; i++) {
			int s2 = strings[i].length;
			fields[i] = new Fields[s2];
			for (int j = 0; j < s2; j++) {
				fields[i][j] = new FieldsString(strings[i][j], numFont, rs, fontColor);				
			}			
		}	
		setWcHr();
	}
	
	public void objectFill(Picture[][] pictures) {
		int size = pictures.length;
		this.fields = new Fields[size][];
		for (int i = 0; i < size; i++) {
			int s2 = pictures[i].length;
			fields[i] = new Fields[s2];
			for (int j = 0; j < s2; j++) {
				fields[i][j] = new FieldsPicture(pictures[i][j], rs);				
			}			
		}	
		setWcHr();
	}
	
	public void objectFill(Picture[][][] pictures) {
		int size = pictures.length;
		this.fields = new Fields[1][size];
		for (int i = 0; i < size; i++) {			
			ImageObject io = new TableObject(pictures[i], rs, paddingVer);		
			fields[0][i] = new FieldsObject(io);	
		}		
		setWcHr();
	}
	
	
	public void setHeader(String[] header, int numFont) {
		int size = header.length;
		this.header = new FieldsString[size];
		
		for (int i = 0; i < size; i++) {
			this.header[i] = new FieldsString(header[i], numFont, rs, fontColor); 
		}
		this.headerWH = new int[2];
		setWcHr();
	}
	
	public void setNotes(String[] notes, int numFont) {
		int size = notes.length;
		this.notes = new FieldsString[size];
		
		for (int i = 0; i < size; i++) {
			this.notes[i] = new FieldsString(notes[i], numFont, rs, fontColor); 
		}
		this.notesWH = new int[2];
		setWcHr();
	}
	
	
	@Override
	protected void setInnerBorders(int border, Color borderColor2) {
		if (fields[0][0].getClass().getName() == "tel_ran.tests.images.FieldsObject")
			for (Fields[] fs : fields)
				for (Fields f : fs)
					((FieldsObject) f).setBorders(border, borderColor2);
		
	}

	
	
	/* ------------------------------------------
	 * Calculation
	 * ------------------------------------------ */	
	
	@Override
	protected void calculateObject() throws Exception {
		
	int ihr = 0;
	
		if (header!=null)
			getFlexibleTableSize(ihr++, header);
	
		for (Fields[] f : this.fields) {
			getFlexibleTableSize(ihr++, f);
		}
		
		if (notes!=null)
			getFlexibleTableSize(ihr++, notes);
		
		this.height += calc(hr) + this.margin*2;
		int w = calc(wc) + this.margin*2;
		
		if (w > this.width)
			this.width = w;
	}
		
	protected int calc(int[] num) {
		int res = 0;
		for (int n : num)
			res += n;
		return res;
	}

	protected void getFlexibleTableSize(int indexH, Fields[] fld) throws Exception {		
		int tt;
		int size = fld.length;
		
		if (hr.length-1 < indexH)
			hr = arResize(hr, indexH+1);
		if (wc.length < size) 
			wc = arResize(wc, size);
		
		for (int i = 0; i < size; i++) {
			tt = fld[i].getHeight() + this.paddingVer;
			if (hr[indexH] < tt)
				hr[indexH] = tt;
			tt = fld[i].getWidth() + this.paddingHor;
				if (wc[i] < tt)
				wc[i] = tt;			
		}		
	}

	
	protected int[] arResize(int[] ar, int size) {
		int[] temp = new int[size];
		System.arraycopy(ar, 0, temp, 0, ar.length);			
		return temp;
	}	

	/* ------------------------------------------
	 * Resize
	 * ------------------------------------------ */

	@Override
	public int resize(int max) throws Exception {
		int target = this.width - max - this.margin*2;
		int res = 0; 
//		resizeFields(max-this.margin*2);
		if (res >=target)
			return res;
				
		res += reductionTable(max-this.margin*2);
		calculation();
		return res;			
		
	}
	
	
	protected int reductionTable(int max) throws Exception {
		int res = this.width;
		
		if (header!=null) {
			reduction(header, max, width);
			calculation();
		}
		
		if (notes != null && this.width > max) {
			reduction(notes, max, this.width);
			calculation();
		}
		
		for (Fields[] f : fields) {
			if (this.width > max) {
				reduction(f, max, width);
				calculation();
			}
			else
				break;
		}
		
		return res - this.width;
	}
	
	
	
	@Override	
	protected int reduction(Fields[] flds, int max, int width) throws Exception {
		for (int i = 0; i < flds.length; i++) {
			if (flds[i]!=null) {
				if (!flds[i].reduction(max-this.paddingHor)) {	
					calculation();
					int tt = 0;
					for (Fields f : flds)
						tt += f.getWidth() + this.paddingHor;
					if (tt > max)
						if (paddingRed(flds, max, width) == 0)
							reduction(flds, max, width);
					return width - this.width;
				}	
			}
		}
		return 0;
	}
	
	protected int paddingRed(Fields[] flds, int max, int width) throws Exception {
		if (this.paddingHor > 2) {
			this.paddingHor --;
			int tt = 0;
			for (Fields f : flds)
				tt += f.getWidth() + this.paddingHor;			
			if (tt > max)
				if (reduction (flds, max, width) ==0)
					paddingRed(flds, max, width);			
			return width - this.width;			
		}	
		return 0;
	}
	
	
		
	@Override
	protected int resizeFields(int max) throws Exception {
		boolean[][] reformed = new boolean[this.wc.length][this.hr.length+1];
		
		int target = this.width - max;
		int res = 0, tt, targetSize, maxRes, frm, to = hr.length, tempH;
		float correct = (float)max/this.width;
		int numReformed = 0;
		
		
		while (res<target && numReformed < reformed.length) {
			for (int i = 0; i < wc.length; i++) {
				
				if (!reformed[i][0]) {
					maxRes = 0;
					frm = 0;					
					targetSize = (int)(correct*wc[i]) - this.paddingHor;
					int numCellsReformed = 0;
					
					if (header!=null) {						
						if (!reformed[i][1]) {
							if (header[i].getWidth() > targetSize) {
								tt = header[i].reform(targetSize);
								if (tt == 0) {
									reformed[i][1] = true;
									numCellsReformed ++;
								} else {
									tempH = header[i].getHeight() + this.paddingVer;
									if (tempH > hr[0])
										hr[0] = tempH;
								}
								maxRes = tt;
							}
						} else 
							numCellsReformed ++;
						frm = 1;
						to--;
					}
							
					if (notes!=null) {						
						if (!reformed[i][hr.length]) {
							if (notes[i].getWidth() > targetSize) {						
								tt = notes[i].reform(targetSize);
								if (tt == 0) {
									reformed[i][hr.length] = true;
									numCellsReformed ++;
								} else {
									tempH = notes[i].getHeight() + this.paddingVer;
									if (tempH > hr[hr.length-1])
										hr[hr.length-1] = tempH;
								}
								if (tt > maxRes)
									maxRes = tt;
							}
						} else
							numCellsReformed++;
						to--;
					}
								
					for (int j = 0; j < to; j++) {						
						if (!reformed[i][j+1]) {	
							
							if(fields[i][j].getWidth() > targetSize) {
								tt = notes[i].reform(targetSize);
								if (tt == 0) {
									reformed[i][j+1] = true;
									numCellsReformed++;
								} else {
									tempH = fields[i][j].getHeight() + this.paddingVer;
									if (tempH > hr[j+frm])
										hr[j+frm] = tempH;
								}
								if (tt > maxRes)
									maxRes = tt;
							}
						} else
							numCellsReformed++;						 
					}
										
					if (numCellsReformed >= hr.length) {
						reformed[i][0] = true;
						numReformed++;
					}
					wc[i] = wc[i] - maxRes;
					correct = (float)(max - maxRes)/wc[i];	
					res += maxRes;
				}				
			}			
		}		
		return res;		
	}
	
	
	
	@Override	
	protected ImageObject rotation() {
		return new TableObjectInLine(fields, rs, paddingVer);
	}

		

	
	/* ------------------------------------------
	 * Setting for draw. 
	 * ------------------------------------------ */	
	
	@Override
	public void setStartXY(int startX, int startY) throws Exception {
		this.startX = startX;
		this.startY = startY;
		startX += this.margin + this.paddingHor/2;
		startY += this.margin + this.paddingVer/2;
		int y = startY;
		int x = startX;
		int w;
		int bs;
		int from = 0;
		if (header!=null)
			from++;
		int to = hr.length;
		if (notes!=null) 
			to--;
		
		for (int i = 0; i < wc.length; i++) {
			y = startY;
			
			if (header!=null) {
				w = header[i].getWidth();				
				bs = (wc[i] - this.paddingHor - w)/2;
				header[i].setXY(x+bs, y);
				y += hr[0];				
			}
						
			for (int j = 0; j < fields.length; j++) {				
				w = fields[j][i].getWidth();
				
				bs = (wc[i] - this.paddingHor - w)/2;
				
				fields[j][i].setXY(x+bs,y);
				y += hr[j+from];				
			}
			
			if (notes!=null) {
				w = notes[i].getWidth();
				bs = (wc[i] - this.paddingHor - w)/2;
				notes[i].setXY(x+bs, y);
			}
			x += wc[i];
		}
	}
	
	
		


	
	/* ------------------------------------------
	 * Draw
	 * ------------------------------------------ */
	
	@Override
	protected void drawGrid(Graphics2D gr) {
		gr.setColor(borderColor);
		int x1 = this.startX;
		int y1 = this.startY;
		int y2 = this.startY + this.height;
		int x2 = this.startX + this.width;
		int x = x1;		

		for (int i = 0; i < wc.length-1; i++) {
			x += wc[i];
			gr.drawLine(x, y1, x, y2);
		}
		
		for (int i = 0; i < hr.length - 1; i ++) {
			y1 += hr[i];
			gr.drawLine(x1, y1, x2, y1);
		}		
	}
	
	
	@Override
	protected void drawFields(Graphics2D gr) throws Exception {
		for (Fields[] fs : fields)
			for (Fields f : fs)
				f.draw(gr);		
	}


	




	
	/* ------------------------------------------
	 * Getters
	 * ------------------------------------------ */


}
