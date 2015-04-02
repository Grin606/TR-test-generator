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
	
	protected static final int THICK =3;
	
	public static final int NUMBER_OF_COLORS = 6;
	public static final int COLOR_MAGENTA = 0;
	public static final int COLOR_BLACK = 1;
	public static final int COLOR_BLUE = 2;
	public static final int COLOR_GREEN = 3;
	public static final int COLOR_YELLOW = 4;
	public static final int COLOR_RED = 5;
	public static final String[] colorArray = {"Magenta", "Black", "Blue", "Green", "Yellow","Red"};
	
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
		if (colorInt != other.colorInt)
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
		p = setShape(shape);
		p.setColor(color);
		p.setInside(inside);
		return p;
	}
	
	//   setters and getters

	//   picture
	
	public static Picture setPicture (int ip, Color ic, int ii){
		
		Picture p = setShape(ip);
		p.setColor(ic);
		p.setInside(ii);
		
		return p;
	}
	
	public static Picture setPicture (int ip, int ic, int ii){
		
		Picture p = setShape(ip);
		p.setColor(ic);
		p.setInside(ii);
		
		return p;
	}
	
	public static Picture setRandomPicture(boolean noEmpty) {
		Picture p = setRandomShape(noEmpty);
		p.setRandomColor();
		p.setRandomInside();
		
		return p;
	}
	
	// shape
	
	public static Picture setShape(int is) {
		
		Picture p;
		
		switch(is) {
		case Picture.EMPTY: p = new Empty(); break;
		case Picture.TRIANGLE: p = new Triangle(); break;
		case Picture.SQUARE: p = new Square(); break;
		case Picture.CIRCLE: p =  new Circle(); break;
		case Picture.RECTANGLE: p =  new Rectangle(); break;
		case Picture.RUMB: p =  new Rhomb();break;
		default: return null;
		}
		
		p.shape = is;
		return p;
	}
	
	public static Picture setRandomShape(boolean noEmpty) {
		if (noEmpty) return setShape(RandFunc.IntRandomInRange(1, NUMBER_OF_SHAPES-1));	
		else return setShape(RandFunc.IntRandomInRange(0, NUMBER_OF_SHAPES-1));
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
			this.color = Color.GREEN;
			break;
		case 4:
			this.color = Color.YELLOW;
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
		if (color == Color.GREEN) colorInt = 3;
		if (color == Color.YELLOW) colorInt = 4;
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
		return (getShape() != 0);
	}
	
	public static Color colorByInt (int ic) {
		
		if (ic < 0 || ic > NUMBER_OF_COLORS-1) return null;
		
		switch (ic) {
		case 0: return Color.MAGENTA;
		case 1: return Color.BLACK;
		case 2: return Color.BLUE;
		case 3: return Color.GREEN;
		case 4: return Color.YELLOW;
		case 5: return Color.RED;
		default: return null;
		}
	}

}
