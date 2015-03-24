package tel_ran.tests.images;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import tel_ran.tests.pictures.Picture;



public class TablesPictures extends Tables {
	
//	Picture[][] fields;
		

	public TablesPictures(String[] header, Picture[][] fields, Font fontHeader, Font fontFields,
			int fr) {
		super(header, fontHeader, fontFields, fr);
		this.fields = fields;	
		setWcHr();
	}
	
	protected int getElementSize (Object ob, Boolean width, Boolean height) {
		
		assert width == true ^ height == true;
		
//		if (width)
//			return (Picture)ob.getWidth() * 100 / sizeCorrection;
//			
//		if (height)	
//			return (Picture)ob.getHeight() * 100 / sizeCorrection;
		
		return 0;
		
	}

	@Override
	protected void resizeElement() throws Exception {		
		this.sizeCorrection -= 5;	
		if (sizeCorrection == 0)
			throw new Exception();
		
	}


//	public BufferedImage draw(BufferedImage image) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	protected void drawTable(Graphics2D gr) {
		// TODO Auto-generated method stub
		
	}
	
	

}
