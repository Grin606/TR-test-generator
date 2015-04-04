package tel_ran.tests.pictures;
import java.awt.*;

import tel_ran.tests.tools.RandFunc;

public abstract class Picture {
	
	private int width=20;
	private int height=20;
	
	private Color color;
	private int colorInt;
	
	private int inside;
	private int shape;
	
	private String name = "";
	
	public static final int NUMBER_OF_SHAPES = 6;
	
	public static final int EMPTY = 0;
	public static final int TRIANGLE = 1;
	public static final int SQUARE = 2;
	public static final int CIRCLE = 3;
	public static final int RECTANGLE = 4;
	public static final int RUMB = 5;
	public static final String[] shapeArray = {"Empty", "Triangle", "Square", "Circle", "Rectangle", "Rhomb"};
	private static final String PACKAGE = "tel_ran.tests.pictures.";
	
	protected static final int THICK =3;
	
	public static final int NUMBER_OF_COLORS = 6;
	public static final int COLOR_MAGENTA = 0;
	public static final int COLOR_BLACK = 1;
	public static final int COLOR_BLUE = 2;
	public static final int COLOR_GRAY = 3;
	public static final int COLOR_ORANGE = 4;
	public static final int COLOR_RED = 5;
	public static final String[] colorArray = {"Magenta", "Black", "Blue", "Gray", "Orange","Red"};
	
	protected static Color backgroundColor = Color.WHITE;
	
	public static final int NUMBER_OF_INSIDES = 8;
	public static final int INSIDE_EMPTY = 0;
	public static final int INSIDE_FULL = 1;
	public static final int INSIDE_VERTLINE = 2;
	public static final int INSIDE_HORLINE = 3;
	public static final int INSIDE_ERECT_CROSS = 4;
	public static final int INSIDE_NW_SE_LINE = 5;
	public static final int INSIDE_NE_SW_LINE = 6;
	public static final int INSIDE_OBLIQUE_CROSS = 7;
	public static final String[] insideArray = {"Empty", "Full", "VertLine", "HorLine", "Erect cross", "NW-SE", "NE-SW", "Oblique cross"};
		 
	public abstract void draw(Graphics2D gr, int x_UpperLeft, int y_UpperLeft, int percent);
	public void draw(Graphics2D gr, int x, int y) {
		draw(gr, x, y, 100);
	}		 
	
	public void setRandomColor() {
		setColor(RandFunc.IntRandomInRange(0, NUMBER_OF_COLORS-1));	
	}
	public void setRandomColorExept(int exept) {
		setColor(RandFunc.IntRandomInRangeExept(0, NUMBER_OF_COLORS-1, exept));
	}
	
	public void setRandomInside() {
		setInside(RandFunc.IntRandomInRange(0, NUMBER_OF_INSIDES-1));
	}

	
	public boolean equals(Picture obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		Picture other = (Picture) obj;
		if (color.equals(other.color))
			return false;
		if (inside != other.inside)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public Picture copyPicture() {
		
		Picture p;
		p = getPictureShape(shape);
		p.setColor(color);
		p.setInside(inside);
		return p;
	}
	
	//   setters and getters

	//   picture
	
	public static Picture setPicture (int ip, Color ic, int ii){
		
		Picture p = getPictureShape(ip);
		p.setColor(ic);
		p.setInside(ii);
		
		return p;
	}
	
	public static Picture setPicture (int iShape, int iColor, int iInside){
		
		return setPicture (iShape, colorByInt(iColor), iInside);		
	}
	
	public static Picture setRandomPicture(boolean noEmpty) {
		
		Picture p = setRandomShape(noEmpty);
		p.setRandomColor();
		p.setRandomInside();
		
		return p;
	}
	
	// shape
	
	public static Picture getPictureShape(int iShape) {
		
		Picture p;
		
		try {
			Class cl = Class.forName(PACKAGE+shapeArray[iShape]);
			p = (Picture) cl.newInstance();
		} catch (Exception e) {
			return null;
		}
				
		p.shape = iShape;
		return p;
	}
	
	public static Picture setRandomShape(boolean noEmpty) {	
		return getPictureShape(RandFunc.IntRandomInRange((noEmpty ? 1 : 0), NUMBER_OF_SHAPES-1));
	}
	
	public int getShape() {
		return shape;
	}
	
	// color
	
	public void setColor(Color color) {
		this.color = color;
		setColorInt(color) ;
	}
	
	public void setColor (int ic) {
		
		if (ic < 0 || ic > NUMBER_OF_COLORS-1) return;
		
		switch (ic) {
		case 0:
			this.color = Color.MAGENTA;
			break;
		case 1:
			this.color = Color.BLACK;
			break;
		case 2:
			this.color = Color.BLUE;
			break;
		case 3:
			this.color = Color.GRAY;
			break;
		case 4:
			this.color = Color.ORANGE;
			break;
		case 5:
			this.color = Color.RED;
			break;
		default: 
			this.color = Color.BLACK;
		}
		
		colorInt = ic;
	}
	
	public void setColorInt (Color color) {
				
		if (color == Color.MAGENTA) colorInt = 0;
		if (color == Color.BLACK) colorInt = 1;
		if (color == Color.BLUE) colorInt = 2;
		if (color == Color.GRAY) colorInt = 3;
		if (color == Color.ORANGE) colorInt = 4;
		if (color == Color.RED) colorInt = 5;
	
	}
	
	public Color getColor() { 
		return color;
	}
	
	public int getColorInt() {
		return colorInt;
	}
	
	public String getColorString() {
		return colorArray[colorInt];
	}
	
	// inside
	
	public void setInside(int inside) {
		this.inside = inside;
	}
	
	public int getInside() {
		return inside;
	}
	
	public String getInsideString() {
		return insideArray[inside];
	}
	
	// name
	
	public void setName(String s) {
		name = s;
	}
	
	public String getName() {
		return name;
	}
	
	// dimensions
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int w) {
		this.width = w;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int h) {
		this.height = h;
	}
	
	//service
	
	public boolean isEmpty() {
		return (getShape() != Picture.EMPTY);
	}
	
	public static Color colorByInt (int ic) {
		
		if (ic < 0 || ic > NUMBER_OF_COLORS-1) return null;
		
		switch (ic) {
		case 0: return Color.MAGENTA;
		case 1: return Color.BLACK;
		case 2: return Color.BLUE;
		case 3: return Color.GRAY;
		case 4: return Color.ORANGE;
		case 5: return Color.RED;
		default: return null;
		}
	}
	
	public void fillPict(Graphics2D gr, int x, int y, int width2, int height2) {
		gr.setColor(backgroundColor);
		gr.fillRect(x, y, width, height);
		
	}
	public void drawInside(Graphics2D gr, int x, int y, int width2, int height2) {
		
		gr.setColor(backgroundColor);
	
		if (getInside()==Picture.INSIDE_ERECT_CROSS){
			gr.drawLine(x, y+height/2, x+width, y+height/2);
		}
		if (getInside()==Picture.INSIDE_HORLINE){
			gr.drawLine(x, y+height/2, x+width, y+height/2);
		}
		if (getInside()==Picture.INSIDE_VERTLINE){
			gr.drawLine(x+width/2, y, x+width/2, y+height);
		}
		if (getInside()==Picture.INSIDE_OBLIQUE_CROSS){
			gr.drawLine(x, y, x+width, y+height);
			gr.drawLine(x+width, y, x, y+height);
		}
		if (getInside()==Picture.INSIDE_NE_SW_LINE){
			gr.drawLine(x+width, y, x, y+height);
		}
		if (getInside()==Picture.INSIDE_NW_SE_LINE){
			gr.drawLine(x, y, x+width, y+height);
		}
	}
	public static void setBackgroundColor(Color backgroundColor) {
		Picture.backgroundColor = backgroundColor;
	}
	
	
}
