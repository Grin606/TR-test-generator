package tel_ran.tests.pictures;

import java.awt.*;

public class Rhomb extends Picture {

	public Rhomb() {
		super();
		setName("Rhomb");
	}
	
	@Override
	public void draw(Graphics2D gr, int x_UpperLeft, int y_UpperLeft, int percent) {
		System.out.printf("%10s%10s%15s", getName(), getColorString(), getInsideString());
	}


}