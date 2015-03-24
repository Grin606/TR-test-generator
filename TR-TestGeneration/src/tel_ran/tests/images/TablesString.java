package tel_ran.tests.images;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class TablesString extends Tables {
	
//	String[][] fields;

	
	public TablesString(String[] header, String[][] fields, Font fontHeader, Font fontFields,
			int fr) {
		super(header, fontHeader, fontFields, fr);
		this.fields = fields;
		setWcHr();
	}
	
	protected int getElementSize (Object ob, Boolean width, Boolean height) {
		
	assert width == true ^ height == true;
		
	Rectangle2D testStr = fontFields.getStringBounds((String) ob, Image.frc);
		if (width)
			return (int)testStr.getWidth() + margin*2;
			
		if (height)	
			return (int)testStr.getHeight() + margin*2;
		
		return 0;
		
	}

	@Override
	protected void resizeElement() throws Exception {
		this.sizeCorrection--;
		fontHeader = new Font(fontHeader.getName(), fontHeader.getStyle(), fontHeader.getSize() - 1);
		fontFields = new Font(fontFields.getName(), fontFields.getStyle(), fontFields.getSize() - 1);
	}

//	@Override
//	public BufferedImage draw(BufferedImage image) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	protected void drawTable(Graphics2D gr) {
		int x1, x2, y1, y2, i;
		int ihr = 0, iwc = 0;
	
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
						
			for (Object[] fld0 : fields) {
				x1 = this.startX;
								
				for (Object fld1 : fld0) {
					bounds = fontFields.getStringBounds((String) fld1, Image.frc);
					y2 = y1 - (hr[ihr] - (int)bounds.getHeight())/2;				
					x2 = x1 + (wc[iwc] - (int)bounds.getWidth())/2;				
					gr.drawString((String) fld1, (float)x2, (float)y2);
					x1 += wc[iwc++];				
			}			
				iwc = 0;
				y1 += hr[ihr++];
			
		}
		

		}

	}
}
