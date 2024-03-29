package tel_ran.tests.images;

import java.awt.Color;
import java.awt.Graphics2D;

import tel_ran.tests.dataset.Frames;


public abstract class ImageObject {
	
	protected int width;
	protected int height;	
	protected int startX;
	protected int startY;
	protected int paddingHor;
	protected int paddingVer;
	protected int margin;
	protected Resizing rs;			
	protected Color fontColor; 	
	protected Color borderColor;
	protected int borders = 0;
	
	
	Fields[] header;
	Fields[] notes;	
	int[] headerWH;
	int[] notesWH;
	
	Boolean rotated = false;
	
	/* ------------------------------------------
	 * Constructor
	 * ------------------------------------------ */
	
	public ImageObject(Resizing rs, int padding) {
		super();
		this.rs = rs;
		this.borders = 0;
		this.margin = 0;
		this.paddingHor = this.paddingVer = padding;	
		headerWH = new int[2];
		notesWH = new int[2];
	}
	
	
	/* ------------------------------------------
	 * Start setters 
	 * ------------------------------------------ */
	
	public abstract void setHeader(String[] header, int numFont);
	
	public void setHeader(Fields[] fields) {
		this.header = fields;
		this.headerWH = new int[2];		
		
	}
	
	public abstract void setNotes(String[] notes, int numFont);
	
	public void setNotes(Fields[] fields) {
		this.notes = fields;
		this.notesWH = new int[2];		
	}
	
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	
	
	
	public void setBorders(Frames frames, Color borderColor) {
		
		this.borderColor = borderColor;
				
		setBordersSwith(frames.getFrExternal(), borderColor);
		
		if (frames.getFrMedium() != 0)
			setInnerBorders(frames.getFrMedium(), borderColor);	
		
	}	
	
	protected void setBordersSwith (int frames, Color borderColor) {
		
		this.borders = frames;
		
		switch (frames) {
		case Frames.OUTSIDE:
			this.margin += Image.margin+4;
			break;
		case Frames.INSIDE_AND_OUTSIDE:			
			this.margin += Image.margin+4;
			this.paddingHor *=2;
			this.paddingVer +=2;
			break;
		case Frames.INSIDE :				
			this.paddingHor *=2;
			this.paddingVer +=2;
			break;
		}
		
		this.borderColor = borderColor;
		
	}
		
	protected abstract void setInnerBorders(int border, Color borderColor2);


	public Boolean getRotated() {
		return rotated;
	}


	public void setRotated(Boolean rotated) {
		this.rotated = rotated;
	}
	
	/* ------------------------------------------
	 * Calculation
	 * ------------------------------------------ */
	
	
	public void calculation() throws Exception {
		height = 0;
		width = 0;
		
		
		switch (borders) {
			case Frames.OUTSIDE :
				width = height = 4;
				
			case Frames.INSIDE_AND_OUTSIDE:
				width = height = 4;
				
			case Frames.INSIDE :
				width = height = 4;		
				
		}
				
		calculateObject();		
	}
		
	protected abstract void calculateObject() throws Exception;
		
	
	/* ------------------------------------------
	 * Resize
	 * ------------------------------------------ */
		
	public abstract int resize(int max) throws Exception;
	protected abstract int resizeFields(int max) throws Exception;
	protected abstract int reduction(Fields[] flds, int max, int width) throws Exception;

	
	public ImageObject reform(int max) throws Exception {
		this.rotated = true;
		
		if (this.width - max < max/4)
			return null;
		
		ImageObject newOb = getRotateObject();		
		newOb.calculation();
		
		if (newOb.width > max)
			return null;
	
		return newOb;
	}
	
	protected ImageObject getRotateObject() {
		ImageObject io = rotation();

		if (this.header!=null)
			io.setHeader(header);
		if (this.notes!=null)
			io.setNotes(notes);		
		if (this.fontColor!=null)
			io.setFontColor(fontColor);
		if (this.borders != 0)
			io.setBordersSwith(borders, borderColor);		
		io.setRotated(true);
		
		return io;	

	}
	
	protected abstract ImageObject rotation();

	
	
	/* ------------------------------------------
	 * Setting for draw. 
	 * ------------------------------------------ */
		
	protected void setHeadeNotesXY(Fields[] fs, int x, int y) throws Exception {
		if (fs != null) {			
			for (Fields f : fs) {				
				x += f.getWidth() + this.paddingHor;
				f.setXY(x,y);
			}			
		}
	}
		
	
	public abstract void setStartXY(int startX, int startY) throws Exception; 
	

	
	
	/* ------------------------------------------
	 * Draw
	 * ------------------------------------------ */
	
	public void draw(Graphics2D gr) throws Exception {
				
		if (this.borders == Frames.OUTSIDE || this.borders == Frames.INSIDE_AND_OUTSIDE)
			drawBorders(gr);
		if (this.borders == Frames.INSIDE || this.borders == Frames.INSIDE_AND_OUTSIDE)
			drawGrid(gr);
		gr.setColor(this.fontColor);
		if (header!=null)
			for (Fields f : header)
				f.draw(gr);
		
		if (notes!=null)
			for (Fields f : notes)
				f.draw(gr);
		
		drawFields(gr);
		
	}

	protected void drawBorders(Graphics2D gr) {
		gr.setColor(borderColor);
		gr.drawRect(this.startX, this.startY, this.width, this.height);		
	}

	protected abstract void drawGrid(Graphics2D gr) throws Exception;
	protected abstract void drawFields(Graphics2D gr) throws Exception;
	

	/* ------------------------------------------
	 * Getters
	 * ------------------------------------------ */
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	

	
	protected  int getHeaderHeight() {		
		return headerWH[1];
	}
		
	protected int getHeaderWidth() {
		return headerWH[0];	
	}
	
	protected int getNotesHeight() {
		return notesWH[1];
	}
	
	protected int getNotesWidth() {
		return notesWH[0];
	}

	

}
