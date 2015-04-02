package tel_ran.tests.images;

import java.awt.Color;
import java.awt.Graphics2D;

public class FieldsObject implements Fields {
	
	ImageObject field;	
	int minw;
	int minh;
	int w = 0;
	int h = 0;

	public FieldsObject(ImageObject field) {
		super();
		this.field = field;
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
	
	private void reSettinf() throws Exception {
		field.calculation();
		w = field.getWidth();
		h = field.getHeight();
	}

	@Override
	public int getWidth() throws Exception {	
		if (w == 0) {
			reSettinf();
		}
		return w;
	}

	@Override
	public int getHeight() throws Exception {	
		if (h == 0)
			reSettinf();
		return field.getHeight();
	}


	@Override
	public void draw(Graphics2D gr) throws Exception {
		field.draw(gr);
	}

	@Override
	public void setXY(int x, int y) throws Exception {
		field.setStartXY(x, y);		
	}
	
	@Override
	public int reform(int max) throws Exception {	
		if (field.getRotated())
			return 0;
		ImageObject res = field.reform(max);
		if (res == null)
			return 0;
		int tmp = field.getWidth() - res.getWidth();
		field = res;
		reSettinf();
		return tmp;
	}

	@Override
	public boolean reduction(int max) throws Exception {
		field.resize(max);
		reSettinf();
		if (field.getWidth() > max)
			return false;
		return true;
	}
	
	public void setBorders(int border, Color borderColor2) {
		field.setBorders(border, borderColor2);
	}



}
