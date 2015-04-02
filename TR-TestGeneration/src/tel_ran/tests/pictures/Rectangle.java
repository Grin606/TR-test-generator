package tel_ran.tests.pictures;

import java.awt.Color;
import java.awt.Graphics2D;


public class Rectangle extends Square {

	public Rectangle() {
		super();
		setName("Rectangle");
		int height=getHeight();
		setHeight(height/2);
		
	}
	
/*	@Override
	void draw(Graphics2D gr, int x, int y, int percent) {
		System.out.printf("%10s%10s%15s", getName(), getColorString(), getInsideString());
		int width=getWidth();
		int height=getHeight();
		Color color = getColor();

		double scale = (double)percent/100.;
		
		width = (int)(width*scale);
		height = (int)(height*scale);
		
		
		gr.setColor(Color.WHITE);
		gr.fillRect(x, y, width, height);
		gr.setColor(color);
		int xPoints[]={x, x+width, x+width, x, x};
		int yPoints[]={y,y,y+height, y+height,y};
		gr.drawPolygon(xPoints, yPoints, 5);
		gr.fillPolygon(xPoints, yPoints, 5);
		if (getInside()==Picture.INSIDE_EMPTY){
			gr.setColor(Color.WHITE);
			int xP[]={x+THICK, x+width-THICK, x+width-THICK, x+THICK, x+THICK};
			int yP[]={y+THICK, y+THICK, y+height-THICK*2, y+height-THICK*2, y+THICK};
			gr.drawPolygon(xP, yP, 5);
			gr.fillPolygon(xP, yP, 5);
		}
		if (getInside()==Picture.INSIDE_ERECT_CROSS){
			gr.setColor(Color.WHITE);
			gr.drawLine(x+width/2, y, x+width/2, y+height);
			gr.drawLine(x, y+height/2, x+width, y+height/2);
		}
		
	}
*/

}
