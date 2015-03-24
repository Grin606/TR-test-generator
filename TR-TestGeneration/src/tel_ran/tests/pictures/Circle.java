package tel_ran.tests.pictures;

import java.awt.*;


public class Circle extends Picture {

	public Circle() {
		super();
		setName("Circle");
	}

	@Override
	public void draw(Graphics2D gr, int x, int y, int percent) {
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
		gr.drawOval(x, y, width, height);
		gr.fillOval(x, y, width, height);
		if (getInside()==Picture.INSIDE_EMPTY){
			gr.setColor(Color.WHITE);
			gr.drawOval(x+THICK, y+THICK, width-THICK*2, height-THICK*2);
			gr.fillOval(x+THICK, y+THICK, width-THICK*2, height-THICK*2);
		}
		if (getInside()==Picture.INSIDE_ERECT_CROSS){
			gr.setColor(Color.WHITE);
			int xCurr=(x+width/2);
			int yCurr=(y+height/2);
			gr.drawLine(xCurr, y, xCurr, y+height);
			gr.drawLine(x, yCurr, x+width, yCurr);
		}
	}


	
}