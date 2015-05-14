package tel_ran.tests.images;

import java.awt.Graphics2D;
import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.pictures.Picture;

public class FieldsPicture implements Fields {
	
	Picture field;
	Resizing rs;
	int x;
	int y;
	int w;
	int h;	
	

	public FieldsPicture(Picture pct, Resizing rs) {
		this.field = pct;
		this.rs = rs;		
		reniewWH();
	}
	
	protected void reniewWH() {
		this.w = field.getWidth()*(100+rs.pictureResize)/100;
		this.h = field.getHeight()*(100+rs.pictureResize)/100;
	}

	@Override
	public int compareTo(Fields o) {		
		try {
			return this.getWidth() - o.getWidth();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	@Override
	public int getWidth() {
		return w;
	}

	@Override
	public int getHeight() {		
		return h;
	}

	
	public boolean resize() throws Exception {		
		try {
		rs.setResizePicture();
		reniewWH();
		} catch (TasksException te) {
			return false;
		}
		return true;
	}

	@Override
	public void draw(Graphics2D gr) {
		field.draw(gr, x, y, 100-rs.getPictureResize());
	}

	@Override
	public void setXY(int x, int y) {	
		this.x = x;
		this.y = y;
	}

	@Override
	public int reform(int max) {		
		return 0;
	}

	@Override
	public boolean reduction(int max) throws Exception {
		if (max < Image.minimumObject && this.getWidth() <= max)
			return false;		
		return resize();	
	}

	

}
