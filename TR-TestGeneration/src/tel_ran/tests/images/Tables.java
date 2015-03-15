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
public class Tables extends ImageObject {	

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
	String[][] fields;
	
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
	public Tables(String[] header, String[][] fields, Font fontHeader, Font fontFields, int fr) {
		super();
		this.header = header;
		this.fields = fields;
		this.fontHeader = fontHeader;
		this.fontFields = fontFields;
		wc = new int[fields[0].length];
		if (header == null)
			hr = new int[fields.length];
		else
			hr = new int[fields.length + 1];
		Arrays.fill(wc, Image.minColWidth);
		Arrays.fill(hr, Image.minRowHeight);		
		this.header = header;
		this.fields = fields;
		this.margin = Image.padding;
		this.fontColor = Image.fontColor;
		this.fr = fr;
		
	}
		
	@Override
	public void calculation() {
		Rectangle2D testStr; 		
		int temp;
		int iwc = 0, ihr = 0;
		
		//setting numbers of rows and columns for table with header
		if (this.header != null) {
			numRow++;
			numCol = header.length;			

			testStr = fontHeader.getStringBounds(this.header[0], Image.frc);
			temp = (int)testStr.getHeight() + margin*2;
			
			if (temp > hr[ihr])
				hr[ihr] = temp;
			
			for (String hdr : header) {
				testStr = fontHeader.getStringBounds(hdr, Image.frc);
				temp = (int)testStr.getWidth() + margin*2;
				if(temp > wc[iwc])
					wc[iwc++] = temp;				
			}			
			iwc = 0;
			ihr++;
		}
				
		for (String[] fld0 : fields) {
			numRow++;
			if (fld0.length > numCol)
				numCol = fld0.length;
			
			for (String fld : fld0) {
				testStr = fontFields.getStringBounds(fld, Image.frc);
				temp = (int)testStr.getWidth() + margin*3;
				if(temp > wc[iwc]) {
					wc[iwc++] = temp;										
				}
				temp = (int)testStr.getHeight() + margin*2;
				if(temp > hr[ihr])
					hr[ihr] = temp;
			}
			iwc = 0;
			ihr++;
		}
		
		assert numRow == hr.length;
		assert numCol == wc.length;
				
		//calculation of width and height of the table
		height = calc(hr);
		width = calc(wc);
		
		if (width > Image.minWidth) {
			if (header != null) {
				fontResize(fontHeader);
			}
			fontResize(fontFields);
			calculation();
		}		
	}
	
	private void fontResize(Font font) {		
		font = new Font(font.getName(), font.getStyle(), font.getSize() - 1);
	}
	
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

	public String[][] getFields() {
		return fields;
	}

	public void setFields(String[][] fields) {
		this.fields = fields;
	}

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
	public BufferedImage draw(BufferedImage image) {
						
		Graphics2D gr = image.createGraphics();
		int x1, x2, y1, y2, i;
		int ihr = 0, iwc = 0;
		gr.setColor(this.borderColor);
		
		if (fr == 1 ) {					
			gr.drawRect(this.startX, this.startY, this.width, this.height);
		}
		
		if (fr == 2) {
			y1 = this.startY;
			x2 = this.startX + this.width;
		
			for (i = 0; i < this.numRow-1; i++) {
				y1 += hr[ihr++];
				gr.drawLine(startX, y1, x2, y1);			
			}
			ihr = 0;
		
			x1 = this.startX;
			y2 = this.startY + this.height;
		
			for (i = 0; i < this.numCol-1; i++) {
				x1 += wc[iwc++];
				gr.drawLine(x1, startY, x1, y2);
			}
			iwc = 0;
		}
		
		gr.setColor(fontColor);
		
		Rectangle2D bounds;
				
		y1 = this.startY + hr[ihr] - this.margin/2;
		
		if (this.header != null) {
			gr.setFont(this.fontHeader);			
						
			x1 = this.startX;
			
			for (String hd : header) {
				bounds = fontHeader.getStringBounds(hd, Image.frc);
				
				y2 = y1 - (hr[ihr] - (int)bounds.getHeight())/2;
				x2 = x1 + (wc[iwc] - (int)bounds.getWidth())/2;				
				gr.drawString(hd, (float)x2, (float)y2);
				x1 += wc[iwc++];				
			}	
			iwc = 0;
			y1 += hr[ihr++];
		}		
		
		if (this.fields != null) {
			gr.setFont(this.fontFields);							
						
			for (String[] fld0 : fields) {
				x1 = this.startX;
								
				for (String fld1 : fld0) {
					bounds = fontFields.getStringBounds(fld1, Image.frc);
					y2 = y1 - (hr[ihr] - (int)bounds.getHeight())/2;				
					x2 = x1 + (wc[iwc] - (int)bounds.getWidth())/2;				
					gr.drawString(fld1, (float)x2, (float)y2);
					x1 += wc[iwc++];				
			}			
				iwc = 0;
				y1 += hr[ihr++];
			
		}
			
		}
		
		gr.dispose();
		return image;
		
	}

	@Override
	public void draw(Graphics2D gr) {
		
		int x1, x2, y1, y2, i;
		int ihr = 0, iwc = 0;
		gr.setColor(this.borderColor);
		
		if (fr == 1 ) {					
			gr.drawRect(this.startX, this.startY, this.width, this.height);
		}
		
		if (fr == 2) {
			y1 = this.startY;
			x2 = this.startX + this.width;
		
			for (i = 0; i < this.numRow-1; i++) {
				y1 += hr[ihr++];
				gr.drawLine(startX, y1, x2, y1);			
			}
			ihr = 0;
		
			x1 = this.startX;
			y2 = this.startY + this.height;
		
			for (i = 0; i < this.numCol-1; i++) {
				x1 += wc[iwc++];
				gr.drawLine(x1, startY, x1, y2);
			}
			iwc = 0;
		}
		
		gr.setColor(fontColor);
		
		Rectangle2D bounds;
				
		y1 = this.startY + hr[ihr] - this.margin/2;
		
		if (this.header != null) {
			gr.setFont(this.fontHeader);			
						
			x1 = this.startX;
			
			for (String hd : header) {
				bounds = fontHeader.getStringBounds(hd, Image.frc);
				
				y2 = y1 - (hr[ihr] - (int)bounds.getHeight())/2;
				x2 = x1 + (wc[iwc] - (int)bounds.getWidth())/2;				
				gr.drawString(hd, (float)x2, (float)y2);
				x1 += wc[iwc++];				
			}	
			iwc = 0;
			y1 += hr[ihr++];
		}		
		
		if (this.fields != null) {
			gr.setFont(this.fontFields);							
						
			for (String[] fld0 : fields) {
				x1 = this.startX;
								
				for (String fld1 : fld0) {
					bounds = fontFields.getStringBounds(fld1, Image.frc);
					y2 = y1 - (hr[ihr] - (int)bounds.getHeight())/2;				
					x2 = x1 + (wc[iwc] - (int)bounds.getWidth())/2;				
					gr.drawString(fld1, (float)x2, (float)y2);
					x1 += wc[iwc++];				
			}			
				iwc = 0;
				y1 += hr[ihr++];
			
		}
			
		}
				
	
	}
	
}
