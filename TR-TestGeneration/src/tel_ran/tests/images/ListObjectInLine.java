package tel_ran.tests.images;

import java.awt.Graphics2D;
import java.util.LinkedList;

import tel_ran.tests.pictures.Picture;

public class ListObjectInLine extends ListObject {
	
	
	/* ------------------------------------------
	 * Constructor
	 * ------------------------------------------ */	
	
	public ListObjectInLine(Resizing rs, int padding) {
		super(rs, padding);			
	}	
	
	public ListObjectInLine(Fields[] flds, Resizing rs, int padding) {
		super(flds, rs, padding);	
	}



	public ListObjectInLine(Picture[] data, Resizing rs, int padding) {
		super(data, rs, padding);		
	}
	
	public ListObjectInLine(String[] data, Resizing rs, int numFont, int padding) {
		super(data, rs, numFont, padding);		
	}

	public ListObjectInLine(String[][] data, Resizing rs, int numFont,
			int padding) {
		super(data, rs, numFont, padding);		
	}	
	
	public ListObjectInLine(Picture[][] data, Resizing rs, int padding) {
		super(data, rs, padding);		
	}

	public ListObjectInLine(Picture[][][] data, Resizing rs, int padding) {
		super(data, rs, padding);		
	}

	public ListObjectInLine(String[][][] data, Resizing rs, int numFont,
			int padding) {
		super(data, rs, numFont, padding);		
	}

	protected ListObject getInnerList() {
		return new ListObject(this.rs, this.paddingVer);		
	}
	
	
	/* ------------------------------------------
	 * Start setters 
	 * ------------------------------------------ */
	
	
	
	/* ------------------------------------------
	 * Calculation
	 * ------------------------------------------ */	
	
	@Override
	protected void calculateFields() throws Exception {	
		int w1 = 0;
		int h1 = 0;
		
		for (Fields fl : this.fields) {			
			int tt = fl.getHeight();
			if (tt > h1)
				h1 = tt;
			w1 += fl.getWidth() + this.paddingHor;			
		}
				
		w1 -= this.paddingHor;
		
		if (w1 > this.width)
			width = w1;
		
		this.height += h1;				
	}
	
	
	/* ------------------------------------------
	 * Resize
	 * ------------------------------------------ */
	
	@Override
	protected int resizeFields(int max) throws Exception {
		
		return resizeList(fields, max, width);
		
		
	}
	
	protected int reformList(Fields[] fields, int max) throws Exception {
		int res = 0, tt, target = this.width - max - this.margin*2;
		int control, dev;
		LinkedList<Fields> lst = new LinkedList<Fields>();
		
		for (Fields f : fields)
			lst.add(f);
		int size;
		float correct = (float)max/this.width;
		
		while (!lst.isEmpty() && res < target) {
			for (Fields f : lst) {
				control = f.getWidth();						
				size = (int)(correct*control) - this.paddingHor;
				tt = f.reform(size);
				
				if (tt == 0)
					lst.remove(f);
				dev = control - tt;
				correct = (float)(max - dev) / (this.width - dev);				
				res += tt;
			}
		}
		
		return res;		
	}
	
	@Override	
	protected ImageObject rotation() {
		return new ListObject(fields, rs, paddingVer);
	}
	
	/* ------------------------------------------
	 * Setting for draw. 
	 * ------------------------------------------ */
	
	@Override
	protected void setInnerXY (int startX, int startY) throws Exception {		
		for (Fields f : fields) {
			f.setXY(startX, startY);			
			startX += f.getWidth() + this.paddingHor;			
		}			
	}
		
	
	/* ------------------------------------------
	 * Draw
	 * ------------------------------------------ */
	
	@Override
	protected void drawGrid(Graphics2D gr) throws Exception {
		gr.setColor(borderColor);
		int x1 = this.startX;
		int y1 = this.startY;
		int y2 = this.startY + this.height;		
		int x2 = this.startX + this.width; 
		int i;
		int x = x1;
		
		if (header!=null) {			
			y1 += headerWH[1] + this.paddingVer;
			gr.drawLine(x1, y1, x2, y1);
			y1 += this.paddingVer;			
		}
		
		if (notes != null) {
			y2 -= notesWH[1] - this.paddingVer;
			gr.drawLine(x1, y2, x2, y2);
			y2 -= this.paddingVer;
		}
								
		for (i = 0; i < fields.length - 1; i++) {
			x += fields[i].getWidth() + this.paddingHor;
			gr.drawLine(x, y1, x, y2);
		}
			
		
	}
	
	/* ------------------------------------------
	 * Getters
	 * ------------------------------------------ */
	

}
