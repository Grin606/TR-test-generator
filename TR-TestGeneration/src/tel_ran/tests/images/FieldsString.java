package tel_ran.tests.images;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import tel_ran.tests.exceptions.TasksException;

public class FieldsString implements Fields {
	
	ArrayList<String> field;
	Resizing rs; 
	Font font;
	Color fontColor;
	int numFonts;
	int w;
	int h;
	boolean dev = false;	
	int lineH;
	int x;
	int y;
	int minw;
	int minh;

	
	
	@Override
	public String toString() {
		return "FieldsString [field=" + field + "]";
	}

	public FieldsString(String field, int num, Resizing rs, Color color) {
		super();
		assert num < 3;
		this.fontColor = color;
		this.field = new ArrayList<String>();
		this.field.add(field);
		this.rs = rs;		
		this.numFonts = num;
		this.font = rs.getFont(numFonts);
		setMinHeight();
		setMinWidth();
		calculate();
	}
	
	private void reniewFont() {
		this.font = rs.getFont(numFonts);
		calculate();
	}
	
	private void calculate() {
		Rectangle2D bounds;			
		
		int mw = 0, mh = 0, tmp, s=0;
		
		for (String str : field) {		
			if (str != null) {
				bounds = font.getStringBounds(str, Image.frc);
				tmp = (int)bounds.getWidth();
				if (tmp > mw)
					mw = tmp;
				tmp = (int)bounds.getHeight();
				if (tmp > mh)
					mh = tmp;
					s++;
			}
		}
		this.w = mw;
		this.lineH = mh;
		this.h = (lineH + (mh/3)*(s-1))*s;
		
//		if (this.w < minw)
//			this.w = minw;
//		if (this.h < minh)
//			this.h = minh;
		
		dev = false;
	}
	
	@Override
	public int compareTo(Fields arg0) {		
		try {
			return w - arg0.getWidth();
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
			rs.setResizeFont();
			reniewFont();
		} catch (TasksException te) {
			return false;
		}
		return true;	
	}
	
	@Override
	public void draw(Graphics2D gr) {
		gr.setFont(font);		
		gr.setColor(fontColor);		
		for (String str : field) {
			gr.drawString(str, x, y+lineH);
			y += lineH + lineH/3; 
		}
	}


	@Override
	public void setXY(int x, int y) {
		this.x = x;		
		this.y = y;
	}

	private void setMinWidth() {
		minw = Image.minColWidth;		
	}

	private void setMinHeight() {
		minh = Image.minRowHeight;
		
	}

	@Override
	public int reform(int max) {
		StringDevider d = new StringDevider(font);
		int n = field.size();
		int tmp = this.w;
		for (int i = 0; i < n; i++) {				
			String[] res = d.dev(field.get(i), max);
			if (res != null) {
				int n2 = res.length;				
				for (int j = 0; j < n2; j++) {
					field.add(i+j, res[j]);
					i++;
				}
				i--;
			}
		}		
		calculate();		
		
		return tmp - this.w;			
	}

	@Override
	public boolean reduction(int max) throws Exception {
		boolean tmp;
		while (this.w > max) {
			tmp = resize();
			if (!tmp)
				return false;			
		}
			
		return true;	
	}
}
