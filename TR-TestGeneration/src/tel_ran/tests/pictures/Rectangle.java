package tel_ran.tests.pictures;

import java.awt.Color;
import java.awt.Graphics2D;


public class Rectangle extends Picture{

	public Rectangle() {
		super();
		setName("Rectangle");
/*		int height=getHeight();
		setHeight(height/2);
*/		
	}
	
	@Override
	public void draw(Graphics2D gr, int x, int y, int percent) {
		int width=getWidth();
		int height=getHeight();
		Color color = getColor();

		double scale = (double)percent/100.;
		
		width = (int)(width*scale);
		height = (int)(height*scale);
		
		super.fillPict(gr, x, y, width, height);
		gr.setColor(color);
		int xPoints[]={x+width/4, x+width/4*3, x+width/4*3, x+width/4, x+width/4};
		int yPoints[]={y,y,y+height, y+height,y};
		gr.drawPolygon(xPoints, yPoints, 5);
		gr.fillPolygon(xPoints, yPoints, 5);
		if (getInside()==Picture.INSIDE_EMPTY){
			gr.setColor(Color.WHITE);
			int xP[]={x+width/4+THICK, x+width/4*3-THICK, x+width/4*3-THICK, x+width/4+THICK, x+width/4+THICK};
			int yP[]={y+THICK, y+THICK, y+height-THICK, y+height-THICK, y+THICK};
			gr.drawPolygon(xP, yP, 5);
			gr.fillPolygon(xP, yP, 5);
		}
		super.drawInside(gr, x, y, width, height);
/*		if (getInside()==Picture.INSIDE_ERECT_CROSS){
			gr.setColor(Color.WHITE);
			gr.drawLine(x+width/2, y, x+width/2, y+height);
			gr.drawLine(x, y+height/2, x+width, y+height/2);
		}
*/		
	}


}
