package tel_ran.tests.images;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 * Abstract class for all sort of objects on the picture.
 * It contains some basis parameters for these objects * 
 *
 */

public abstract class ImageObject {
	
	/** width of the object **/
	int width;
	/** height of the object**/
	int height;
	/** X-coordinate of the object, its upper left corner**/
	int startX;
	/** Y-coordinate of the object, its upper left corner**/
	int startY;
	/** Distance between pieces of texts and borders inside the object
	 * Default value = 0, but can be changed
	 **/
	int margin;
	/** Font for headers in tables and in other objects **/ 
	Font fontHeader;
	/** Font for main text in the object **/
	Font fontFields;
	/** Color of all texts in the object **/
	protected Color fontColor; 		
	/** Color of all borders in tables **/
	protected Color borderColor;
	
	/** 
	 * 
	 * @return Color of texts in the ImageObject
	 */
	public Color getFontColor() {
		return fontColor;
	}
	
	/**
	 * Sets color of texts in the ImageObject
	 * @param fontColor 
	 */
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}
	
	/**
	 * 
	 * @return Color of borders of tables in the ImageObkect
	 */
	public Color getBorderColor() {
		return borderColor;
	}
	
	/**
	 * Sets color of borders for tables in the ImageObject
	 * @param borderColor
	 */
	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}	
	
	public Font getFontHeader() {
		return fontHeader;
	}	
	public void setFontHeader(Font fontHeader) {
		this.fontHeader = fontHeader;
	}
	
	public Font getFontFields() {
		return fontFields;
	}	
	public void setFontFields(Font fontFields) {
		this.fontFields = fontFields;
	}
		
	public int getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		assert margin >= 0;
		this.margin = margin;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		assert width >0;
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		assert height >0;
		this.height = height;
	}

	public int getStartX() {
		return startX;
	}
	public void setStartX(int startX) {
		assert startX >= 0;
		this.startX = startX;
	}
	
	public int getStartY() {
		return startY;
	}
	public void setStartY(int startY) {
		assert startY >= 0;
		this.startY = startY;
	}
	
	/**
	 * This method calculate height and width of the ImageObject based
	 * on the preset parameters of the object
	 */
	public abstract void calculation(); 
	
	/**
	 * This method draws the ImageObject in BufferedImage
	 * All required parameters must be defined before calling it.
	 * @param image
	 * @return
	 */
	public abstract BufferedImage draw(BufferedImage image);
	public abstract void draw(Graphics2D gr);

}
