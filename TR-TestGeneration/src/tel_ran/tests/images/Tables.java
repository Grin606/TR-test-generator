package tel_ran.tests.images;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * Class for ImageObject with a table
 * Table can be with or without borders. It contains
 * two-dimensional array of String or other object and
 * one-dimensional array for headers.
 */
public abstract class Tables extends ImageObject {	

	/** 
	 * The width of columns in the table.
	 * Required field
	 **/
	int[] wc;
	/** height of rows in the table. Required field **/
	int[] hr;
	/** The number of rows in the table. Required field**/
	int numRow = 0;
	/** The number of columns in the table. Required field **/
	int numCol = 0;
	/** The String array for table headers. Optional field. **/  
	String[] header;
	/**The two-dimensional String array for table content 
	 * Each array fields[i] contains cells of one row 
	 **/
	
	Object[][] fields;
	
	int fr;
	
	public Tables() {
		super();
	}
	
	/**
	 * This constructor uses some parameters of the given image. It may be convinient for most situations,
	 * but not flexible
	 * @param header - String[] array for the table headers. Optional field (can be null) 
	 * @param fields - String[][] array for the table text content. Required field for text tables
	 * @param img - link to the given image (class Image) with preset parameters
	 * @param fontHeader - Font for the table Headers. Optional.
	 * @param fontFields - Font for the table content. 
	 */
	public Tables(String[] header, Font fontHeader, Font fontFields, int fr) {
		super();
		this.header = header;		
		this.fontHeader = fontHeader;
		this.fontFields = fontFields;		
		this.header = header;	
		this.margin = Image.padding;
		this.fontColor = Image.fontColor;
		this.fr = fr;		
	}
	
	protected void setWcHr() {
		wc = new int[fields[0].length];
		if (header == null)
			hr = new int[fields.length];
		else
			hr = new int[fields.length + 1];
		Arrays.fill(wc, Image.minColWidth);
		Arrays.fill(hr, Image.minRowHeight);
	}
	
	
		
	@Override
	public void calculation() throws Exception {
				
		int ihr=0;		
		
		//setting numbers of rows and columns for table with header
		if (this.header != null) {		
			getFlexibleTableSizes(ihr++, this.header); 				
		}
		
		for (Object[] ob : this.fields) {
			getFlexibleTableSizes(ihr++, ob);
		}
		
		numRow = ihr;
		
		assert numRow == hr.length;
		assert numCol == wc.length;
				
		//calculation of width and height of the table
		height = calc(hr);
		width = calc(wc);
		
		if (width > Image.minWidth) {			
			resizeElement(); 
			calculation();
		}		
	}
	
	private void getFlexibleTableSizes(int indexH, Object[] obj) {
		int indexW = 0;
		int temp;	
		
		if (hr.length-1 < indexH)
			hr = resize(hr, indexH+1, Image.minRowHeight);
				
		if (wc.length < obj.length) 
			wc = resize(wc, obj.length, Image.minColWidth);
				
		for (Object o : obj) {
			if (o!=null) {
				temp = getElementSize(o, false, true); 
				if (temp > hr[indexH])
					hr[indexH] = temp;
			
				temp = getElementSize(o, true, false);
				if(temp > wc[indexW])
					wc[indexW] = temp;	
				indexW++;
			}
		}	
		if (indexW > this.numCol)
			this.numCol = indexW;
		
	}
	
	private int[] resize(int[] ar, int size, int value) {
		int[] temp = new int[size];
		System.arraycopy(ar, 0, temp, 0, ar.length);
		Arrays.fill(temp, ar.length, size-1, value);		
		return temp;
	}
		
	protected abstract int getElementSize (Object ob, Boolean width, Boolean height);
	protected abstract void resizeElement() throws Exception;
		
	private int calc(int[] ar) {
		int res = 0;
		
		for(int n : ar)
			res += n;
		
		return res;
	}
	
	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

//	public String[][] getFields() {
//		return fields;
//	}

//	public void setFields(String[][] fields) {
//		this.fields = fields;
//	}

	public Tables(int width, int height) {
		super();
		setWidth(width);
		setHeight(height);
	}

	public int getNumRow() {
		return numRow;
	}

	public int getNumCol() {
		return numCol;
	}
		
	@Override
	public void  draw(Graphics2D gr) {		
		
		if (fr == 1 ) 
			drawBorders(gr);
		
		if (fr == 2) 
			drawGrid(gr);
		
		drawTable(gr);
				
//		return gr;
		
	}
	
	protected void drawBorders(Graphics2D gr) {
		gr.setColor(borderColor);
		gr.drawRect(this.startX, this.startY, this.width, this.height);		
	}
	
	protected void drawGrid(Graphics2D gr) {
		gr.setColor(borderColor);
		int ihr = 0, iwc = 0;
		int y1 = this.startY;
		int x2 = this.startX + this.width;
	
		for (int i = 0; i < this.numRow-1; i++) {
			y1 += hr[ihr++];
			gr.drawLine(startX, y1, x2, y1);			
		}
			
		int x1 = this.startX;
		int y2 = this.startY + this.height;
	
		for (int i = 0; i < this.numCol-1; i++) {
			x1 += wc[iwc++];
			gr.drawLine(x1, startY, x1, y2);
		}
		
	}
	
	protected abstract void drawTable(Graphics2D gr);

}
