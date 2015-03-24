package tel_ran.tests.images;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


/**
 * Class for ImageObject with only texts
 * The text can be divided into several lines
 */
public class TaskDescription extends ImageObject {
	
	/** The String[] array for the text. 
	 * Each String in the array is a line in the picture
	 * Required field
	 **/  
	String[] text;
	/** The maximum width of the text **/
	int maxLength;
	/** The high of one lines **/
	int hRow;
	Rectangle2D testStr;
	
	public TaskDescription() {
		super();		
	}
			
	/**
	 * This constructor fills all required fields
	 * @param text - the given text in one String variable. If it's length is more than maximum, 
	 * the string will be divided in some lines. Otherwise there'll be created a new String array of length = 1.  
	 * @param font - Font for text
	 * @param max - Maximum width of the lines in the text
	 * @param frc
	 */
	public TaskDescription(String text, Font font, int max, FontRenderContext frc) {
		super();
		maxLength = max;
		fontFields = font;		
		testStr = fontFields.getStringBounds(text, Image.frc);	
		hRow = (int)testStr.getHeight();
		int len = (int)testStr.getWidth();
						
		if (len > this.maxLength) {
			StringDevider dev = new StringDevider(fontFields);
			this.text = dev.dev(text, len, maxLength);
			super.width = max; 
		} else {
			this.text = new String[1];
			this.text[0] = text;
			super.width = len;
		}		
	}

	public String[] getText() {
		return text;
	}

	public void setText(String[] text) {
		this.text = text;
	}



	@Override
	public void calculation() {	
		assert this.margin != 0;		
		super.setHeight((this.hRow + this.margin) * text.length + this.margin*2);
		
	}

//	@Override
//	public BufferedImage draw(BufferedImage image) {
//		Graphics2D gr = image.createGraphics();
//		int y;
//		gr.setColor(this.fontColor);
//		gr.setFont(fontFields);
//		
//		Rectangle2D bounds = fontFields.getStringBounds(this.text[0], Image.frc);		
//		y = this.startY + (int)bounds.getHeight();
//				
//		for (int i = 0; i < this.text.length; i++) {
//			gr.drawString(text[i], startX, y);
//			y -= this.hRow;
//		}
//		
//		gr.dispose();
//		return image;
//		
//	}

	@Override
	public void draw(Graphics2D gr) {		
		int y;
		gr.setColor(this.fontColor);
		gr.setFont(fontFields);
		
		Rectangle2D bounds = fontFields.getStringBounds(this.text[0], Image.frc);		
		y = this.startY + (int)bounds.getHeight();
				
		for (int i = 0; i < this.text.length; i++) {
			gr.drawString(text[i], startX, y);
			y -= this.hRow;
		}				
		
	}


	

}
