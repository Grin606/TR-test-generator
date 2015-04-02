package tel_ran.tests.images;

import java.awt.Font;

import tel_ran.tests.exceptions.TasksException;

public class Resizing {
	
	static final int MainText = 0;
	static final int HeaderText = 1;
	static final int NotesText = 2;
	
	
	int fontResize = 0;
	int pictureResize = 0;
	
	Font[] fonts;	
	int[] sizing;
	int[] min;
	String[] name;
	int[] style;
	private final int numberFonts = 3;
	
	
	
	public Resizing() {
		super();
		this.fonts = new Font[numberFonts];
		this.sizing = new int[numberFonts];
		this.name = new String[numberFonts];
		this.style = new int[numberFonts];
		this.min = new int[numberFonts];
	}


	public Resizing(Font mainText, Font headerText, Font notesText) {
		super();
		this.fonts = new Font[numberFonts];
		this.sizing = new int[numberFonts];
		this.name = new String[numberFonts];
		this.style = new int[numberFonts];
		this.min = new int[numberFonts];
		
		fonts[0] = mainText;
		min[0] = Image.fontTaskSizeMin;
		fonts[1] = headerText;
		min[1] = Image.fontLabelSizeMin;
		fonts[2] = notesText;
		min[2] = Image.fontNotesSizeMin;
		dataSet();		
	}
	
	public void setMainText(Font mainText) {
		fonts[0] = mainText;
	}
	
	public void setHeaderText(Font headerText) {
		fonts[1] = headerText;
	}
	
	public void setNotesText(Font notesText) {
		fonts[2] = notesText;
				
	}
	
	public void setSomeText(Font font, int num) {
		fonts[num] = font;
	}
	
	public Font getFont(int num) {
		return fonts[num];
	}
	
	private void dataSet() {
		int n = fonts.length;
		for (int i = 0; i < n; i++) {
			if (fonts[i]!=null) {
				sizing[i] = fonts[i].getSize();
				name[i] = fonts[i].getName();
				style[i] = fonts[i].getStyle();
			}
		}
	}
	
	public void setResizeFont() throws Exception {
		fontResize ++;
		int n = sizing.length;
		for (int i = 0; i < n; i++) {
			if (fonts[i]!=null) {
				if ((sizing[i] - 1) < min[i])
					throw new TasksException("Font size can't be less than 5");
				else 
					sizing[i]--;
			}
		}
		fontsSet();		
	}
	
	public void setResizePicture() throws TasksException {
		if (pictureResize == 95)
			throw new TasksException("");
		pictureResize +=5;
		
	}
	
	public int getPictureResize() {
		return -pictureResize;
	}
	
	private void fontsSet() {
		int n = fonts.length;
		for (int i = 0; i < n; i++) {
			if(fonts[i]!=null)
				fonts[i] = new Font(name[i], style[i], sizing[i]);
		}
	}
	
	public Font getTextFont() {
		return fonts[0];
	}
	
	public Font getHeaderFont() {
		return fonts[1];
	}
	
	public Font getNotesFont() {
		return fonts[2];
	}
	
	public int getMinMain() {
		return min[0];
	}
	
	public int getMinHeader() {
		return min[1];
	}
	
	public int getMinNotes() {
		return min[2];
	}
	
	public int getMin(int i) {
		if (i > numberFonts)
			return 0;
		return min[i];
	}
	
	
}
