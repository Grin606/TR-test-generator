package tel_ran.tests.pictures;

import java.awt.Color;
import java.awt.Graphics2D;


public class Triangle extends Picture {

	public Triangle() {
		super();
		setName("Triangle");
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
		int xPoints[]={x+width/2, x+width, x, x+width/2};
		int yPoints[]={y,y+height, y+height,y};
		gr.drawPolygon(xPoints, yPoints, 4);
		gr.fillPolygon(xPoints, yPoints, 4);
		if (getInside()==Picture.INSIDE_EMPTY){
			gr.setColor(Color.WHITE);
			int xP[]={x+width/2, x+width-THICK, x+THICK, x+width/2};
			int yP[]={y+THICK+1,y+height-THICK, y+height-THICK,y+THICK};
			gr.drawPolygon(xP, yP, 4);
			gr.fillPolygon(xP, yP, 4);
		}
		if (getInside()==Picture.INSIDE_ERECT_CROSS){
			gr.setColor(Color.WHITE);
			gr.drawLine(x+width/4, y+height/2, x+width/4*3, y+height/2);
			gr.drawLine(x+width/2, y, x+width/2, y+height);
		}

	}

}