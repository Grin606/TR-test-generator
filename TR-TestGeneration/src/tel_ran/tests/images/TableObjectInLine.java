package tel_ran.tests.images;

import tel_ran.tests.pictures.Picture;

public class TableObjectInLine extends TableObject {


	/* ------------------------------------------
	 * Constructor
	 * ------------------------------------------ */		
	
	public TableObjectInLine(Resizing rs, int padding) {
		super(rs, padding);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public TableObjectInLine(Fields[][] fields, Resizing rs, int padding) {
		super(fields, rs, padding);		
		setWcHr();
	}



	public TableObjectInLine(Picture[][][] data, Resizing rs, int padding) {
		super(data, rs, padding);
		// TODO Auto-generated constructor stub
	}



	public TableObjectInLine(Picture[][] data, Resizing rs, int padding) {
		super(data, rs, padding);
		// TODO Auto-generated constructor stub		
	}

	public TableObjectInLine(String[][] data, int numFont, Resizing rs,
			int padding) {
		super(data, numFont, rs, padding);
		// TODO Auto-generated constructor stub
	}
	
	
	
	/* ------------------------------------------
	 * Start setters 
	 * ------------------------------------------ */
	
	
	
	/* ------------------------------------------
	 * Calculation
	 * ------------------------------------------ */	
	
	@Override
	protected void calculateObject() throws Exception {
		
	int iwc = 0;
	
		if (header!=null)
			getFlexibleTableSize(iwc++, header);
		
		if (notes!=null)
			getFlexibleTableSize(iwc++, notes);
	
		for (Fields[] f : this.fields) {
			getFlexibleTableSize(iwc++, f);
		}
		
		this.height += calc(hr) + this.margin*2;
		int w = calc(wc) + this.margin*2;
		
		if (w > this.width)
			this.width = w;
	}
	
	
	@Override
	protected void getFlexibleTableSize(int indexW, Fields[] fld) throws Exception {		
		int tt;
		int size = fld.length;
				
		if (wc.length-1 < indexW)
			wc = arResize(wc, indexW+1);
		if (hr.length < size) 
			hr = arResize(hr, size);
		
				
		for (int i = 0; i < size; i++) {
			tt = fld[i].getWidth() + this.paddingHor;
			if (wc[indexW] < tt)
				wc[indexW] = tt;
			tt = fld[i].getHeight() + this.paddingVer;
			if (hr[i] < tt)
				hr[i] = tt;			
		}		
	}
	
	/* ------------------------------------------
	 * Resize
	 * ------------------------------------------ */	
	
	
	@Override	
	protected ImageObject rotation() {
		return new TableObject(fields, rs, paddingVer);
	}
	
	/* ------------------------------------------
	 * Setting for draw. 
	 * ------------------------------------------ */	
			
	@Override
	public void setStartXY(int startX, int startY) throws Exception {
		startX += this.margin + this.paddingHor/2;
		startY += this.margin + this.paddingVer/2;
		int y = startY;
		int x = startX;
		int from = 0;		
		int to = wc.length;
		
		
		if (header!=null) {
			for (int i = 0; i < header.length; i++) {
				header[i].setXY(x, y);
				y += hr[i];
			}
			y = startY;
			x += wc[from++];
		}
		
		if (notes!=null) {
			for (int i = 0; i < notes.length; i++) {
				notes[i].setXY(x, y);
				y += hr[i];				
			}
			y = startY;
			x += wc[from++];
		}
			
		
		for (int j = 0; j < fields.length; j ++) {
			for (int i = 0; i < fields[j].length; i ++) {
				fields[j][i].setXY(x, y);
				y += hr[i];
			}
			y = startY;
			x += wc[j + from];			
		}
		
	}

	
	
	/* ------------------------------------------
	 * Draw
	 * ------------------------------------------ */
	
	
	
	
	/* ------------------------------------------
	 * Getters
	 * ------------------------------------------ */

}
