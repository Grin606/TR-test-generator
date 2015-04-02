package tel_ran.tests.pictures;

import java.awt.Color;
import java.awt.Graphics2D;


public class Rhomb extends Picture {

	public Rhomb() {
		super();
		setName("Rhomb");
	}
	
	@Override
	public void draw(Graphics2D gr, int x, int y, int percent) {
		
		int width=getWidth();
		int height=getHeight();
		Color color = getColor();

		double scale = (double)percent/100.;
		
		width = (int)(width*scale);
		height = (int)(height*scale);
		
		
		gr.setColor(Color.WHITE);
		gr.fillRect(x, y, width, height);
		gr.setColor(color);
		int xPoints[]={x+width/2, x+width, x+width/2, x, x+width/2};
		int yPoints[]={y,y+height/2,y+height, y+height/2,y};
		gr.drawPolygon(xPoints, yPoints, 5);
		gr.fillPolygon(xPoints, yPoints, 5);
		if (getInside()==Picture.INSIDE_EMPTY){
			gr.setColor(Color.WHITE);
			int xP[]={x+width/2, x+width-THICK, x+width/2, x+THICK, x+width/2};
			int yP[]={y+THICK,y+height/2,y+height-THICK, y+height/2,y+THICK};
			gr.drawPolygon(xP, yP, 5);
			gr.fillPolygon(xP, yP, 5);
		}
		if (getInside()==Picture.INSIDE_ERECT_CROSS){
			gr.setColor(Color.WHITE);
			gr.drawLine(x+width/2, y, x+width/2, y+height);
			gr.drawLine(x, y+height/2, x+width, y+height/2);
		}
		
	}



}
