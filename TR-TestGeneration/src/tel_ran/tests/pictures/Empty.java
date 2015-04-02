package tel_ran.tests.pictures;

import java.awt.Color;
import java.awt.Graphics2D;


public class Empty extends Picture {

	public Empty() {
		super();
		setName("Empty");
	}

	@Override
	public
	void draw(Graphics2D gr, int x_UpperLeft, int y_UpperLeft, int percent) {		
		int width=getWidth();
		int height=getHeight();
		Color color = getColor();

		double scale = (double)percent/100.;
		
		width = (int)(width*scale);
		height = (int)(height*scale);

		
		gr.setColor(Color.WHITE);
		gr.fillRect(x_UpperLeft, y_UpperLeft, width, height);

	}

}
