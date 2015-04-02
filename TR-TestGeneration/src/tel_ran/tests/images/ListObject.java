package tel_ran.tests.images;


import java.awt.Color;
import java.awt.Graphics2D;


















import tel_ran.tests.pictures.Picture;

public class ListObject extends ImageObject {
	
	Fields[] fields;
	
	
	/* ------------------------------------------
	 * Constructor
	 * ------------------------------------------ */		

	public ListObject(Resizing rs, int padding) {
		super(rs, padding);			
	}
	
	public ListObject(Fields[] flds, Resizing rs, int padding) {
		super(rs, padding);			
		this.fields = flds;
	}
	
	public ListObject(String[] data, Resizing rs, int numFont, int padding) {
		super(rs, padding);
		objectFill(data, numFont);		
	}
		
	public ListObject(Picture[] data, Resizing rs, int padding) {
		super(rs, padding);
		objectFill(data);		
	}
	
	public ListObject(String[][] data, Resizing rs, int numFont, int padding) {
		super(rs, padding);
		int size = data.length;
		fields = new FieldsObject[size];
		for (int i = 0; i < size; i++)	{
			ListObject ob = getInnerList();
			ob.objectFill(data[i], numFont);			
			fields[i] = new FieldsObject(ob);
		}
	}
	
	public ListObject(Picture[][] data, Resizing rs, int padding) {
		super(rs, padding);
		int size = data.length;
		fields = new FieldsObject[size];
		for (int i = 0; i < size; i++)	{
			ListObject ob = getInnerList();
			ob.objectFill(data[i]);		
			fields[i] = new FieldsObject(ob);
		}
	}
	
	public ListObject(String[][][] data, Resizing rs, int numFont, int padding) {
		super(rs, padding);
		int size = data.length;
		fields = new FieldsObject[size];
		for (int i = 0; i < size; i++)	{
			TableObject ob = getInnerTable();
			ob.objectFill(data[i], numFont);		
			fields[i] = new FieldsObject(ob);
		}
	}
	
	
	public ListObject(Picture[][][] data, Resizing rs, int padding) {
		super(rs, padding);
		int size = data.length;
		fields = new FieldsObject[size];
		for (int i = 0; i < size; i++)	{
			TableObject ob = getInnerTable();
			ob.objectFill(data[i]);		
			fields[i] = new FieldsObject(ob);
		}
	}
	
	protected ListObject getInnerList() {
		return new ListObjectInLine(this.rs, this.paddingVer);		
	}
	
	protected TableObject getInnerTable() {
		return new TableObject(this.rs, this.paddingVer);
	}
	
	
	/* ------------------------------------------
	 * Start setters 
	 * ------------------------------------------ */
	
	public void objectFill(String[] data, int numFont) {
		int size = data.length;
		fields = new FieldsString[size];
		for (int i = 0; i < size; i++) 
			fields[i] = new FieldsString(data[i], numFont, rs, fontColor);			
	}
	
	public void objectFill(Picture[] data) {
		int size = data.length;
		fields = new FieldsPicture[size];
		for (int i = 0; i < size; i++) 
			fields[i] = new FieldsPicture(data[i], rs);			
	}
		
	public void setHeader(String[] header, int numFont) {		
		this.header = new FieldsObject[1];		
		ImageObject tmp = new ListObject(header, rs, numFont, paddingVer);
		this.header[0] = new FieldsObject(tmp);	
		this.headerWH = new int[2];
	}
	
	public void setNotes(String[] notes, int numFont) {		
		this.notes = new FieldsObject[1];		
		ImageObject tmp = new ListObject(notes, rs, numFont, paddingVer);
		this.notes[0] = new FieldsObject(tmp);	
		this.notesWH = new int[2];
	}
	
	
	@Override
	protected void setInnerBorders(int border, Color borderColor2) {		
//		if (fields[0].getClass().getName() == "tel_ran.tests.images.FieldsObject")
			for (Fields f : fields)
				((FieldsObject) f).setBorders(border, borderColor2);
		
	}

	
	
	/* ------------------------------------------
	 * Calculation
	 * ------------------------------------------ */	
	
		
	protected int[] calculateHN(Fields[] flds, int[] wh) throws Exception {
		int h1 = 0;	
		int w1 = 0;
		
		for (Fields fs : flds) {
			int tt = fs.getHeight();
			if (tt > h1)
				h1 = tt;
			w1 += fs.getWidth() + this.paddingHor;
		}			
		
		wh[1] = h1;
		wh[0] = w1-this.paddingHor;
		
		if (wh[0] > width)
			width = wh[0];		
		return wh;		
	}
	
	
	
	@Override
	protected void calculateObject() throws Exception {	
		
		if (header != null) {
			int[] res = calculateHN(this.header, headerWH);			
			height += res[1] + this.paddingVer*2;
			headerWH = res;
		}
		if (notes != null) {
			int[] res = calculateHN(this.notes, notesWH);
			height += res[1] + this.paddingVer*2;
			notesWH = res;
		}
		
		calculateFields();
	}
	
	protected void calculateFields() throws Exception {
				
		int w1 = 0;
		int h1 = 0;
		int tt;
		
		for (Fields fl : this.fields) {
			tt = fl.getWidth();
			if (tt > w1)
				w1 = tt;
			h1 += fl.getHeight() + this.paddingVer;			
		}
		
		w1 += this.paddingHor*2;
		
		if (w1 > this.width)
			width = w1;
		
		this.height += h1 - this.paddingVer;		
		
	}
	
	
	
	/* ------------------------------------------
	 * Resize
	 * ------------------------------------------ */
	
	
	public int resize(int max) throws Exception {
		int w = width;
		
		
		if (headerWH[0] + this.margin*2 > max) {
			resizeList(header, max-this.margin*2, headerWH[0]);			
		}
				
		if (notesWH[0] + this.margin*2 > max) {
			resizeList(notes, max-this.margin*2, notesWH[0]);			
		}
				
		if (width > max) {
			resizeFields(max-this.margin*2);
//			calculation();
//			resize(max);
		}	
		return w - this.width;
	}
		
	protected int reformList(Fields[] flds, int max) throws Exception {
		int size = max/10;		
		int tt;
		int min = 0;
		for (int i = 0; i < flds.length; i++) {
			if (flds[i].getWidth() - max + this.paddingHor > size) {
				tt = flds[i].reform(max - this.paddingHor);
				if (tt < min)
					min = tt;				
			}			
		}
		return min;
	}	
		
	protected int resizeList(Fields[] flds, int max, int width) throws Exception {
		
		int res = reformList (flds, max);
				
		if (width - res <= max)
			return res;
				
		res += reduction(flds, max, width);
		calculation();
		return res;		
		
	}
	
	protected int paddingRed(Fields[] flds, int max, int width) throws Exception {
		if (this.paddingHor > 2) {
			this.paddingHor --;
			int[] s = calculateHN(flds, new int[2]);
			if (s[0] > max)
				if (reduction (flds, max, width) ==0)
					paddingRed(flds, max, width);
			return width - s[0];
		}	
		return 0;
	}


	protected int reduction(Fields[] flds, int max, int width) throws Exception {			
		for (int i = 0; i < flds.length; i++) {
			if (flds[i]!=null) {
				if (!flds[i].reduction(max-this.paddingHor)) {
					int[] s = calculateHN(flds, new int[2]);
					if (s[0] > max)
						if (paddingRed(flds, max, width) == 0) 
							reduction (flds, max, width);			
					return width - s[0];
				}	
			}
		}
		
		return 0;		
	
	}
	
	
	@Override
	protected int resizeFields(int max) throws Exception {
		return resizeList(this.fields, max, this.width);		
	}
	
	@Override	
	protected ImageObject rotation() {
		return new ListObjectInLine(fields, rs, paddingVer);
	}
	
	
	/* ------------------------------------------
	 * Setting for draw. 
	 * ------------------------------------------ */
		
	
	public void setStartXY(int startX, int startY) throws Exception {
		assert startX >= 0;
		assert startY >= 0;
		this.startX = startX;
		this.startY = startY;
		int x = startX + this.margin;
		int y = startY + this.margin;
		
		if (header!=null) {
			setHeadeNotesXY(header, x, y);
			y += headerWH[1] + this.paddingVer*2;
		}
				
		setInnerXY(x, y);
		
		y += this.height - headerWH[1] - notesWH[1] - this.margin*2 + this.paddingVer*2;
		
		if (notes!=null) {
			setHeadeNotesXY(notes, x, y);
		}
	}
	
	protected void setInnerXY (int startX, int startY) throws Exception {			
		for (Fields f : fields) {
			f.setXY(startX, startY);			
			startY += f.getHeight() + this.paddingVer;
		}			
	}
	
	
	/* ------------------------------------------
	 * Draw
	 * ------------------------------------------ */

	@Override
	protected void drawGrid(Graphics2D gr) throws Exception {
		gr.setColor(borderColor);
		int x1 = this.startX;
		int y = this.startY;
		int x2 = this.startY + this.width;		
		int i;
		
		if (header!=null) {
			y += headerWH[1] + this.paddingVer;
			gr.drawLine(x1, y, x2, y);
			y += this.paddingVer;			
		}
		
		for (i = 0; i < fields.length - 1; i++) {
			y += fields[i].getHeight() + this.paddingVer;
			gr.drawLine(x1, y, x2, y);
		}
		
		y += fields[fields.length-1].getHeight() + this.paddingVer;
		
		if (notes!=null) {
			y += this.paddingVer;
			gr.drawLine(x1, y, x2, y);
		}
		
	}

	@Override
	protected void drawFields(Graphics2D gr) throws Exception {
		for (Fields f : fields)
			f.draw(gr);		
	}

	
	
	/* ------------------------------------------
	 * Getters
	 * ------------------------------------------ */


}
