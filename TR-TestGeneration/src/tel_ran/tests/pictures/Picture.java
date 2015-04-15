package tel_ran.tests.pictures;
import java.awt.*;

public abstract class Picture {
	
	private int width=25;
	private int height=25;
	
	private Color color;
	private int inside;
	private int shape;
	
	private String name = "";
	private int colorInt;
	
	public static final int NUMBER_OF_SHAPES = 6;
	
	public static final int EMPTY = 0;
	public static final int TRIANGLE = 1;
	public static final int SQUARE = 2;
	public static final int CIRCLE = 3;
	public static final int RECTANGLE = 4;
	public static final int RUMB = 5;
	public static final String[] shapeArray = {"Empty", "Triangle", "Square", "Circle", "Rectangle", "Rhomb"};
	
	protected static final int THICK = 2;
	
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
	
	
	//   setters and getters

	//   picture
	
	public static Picture setPicture (int iShape, int iColor, int iInside){
		
		Picture p = setShape(iShape);
		p.setColor(iColor);
		p.setInside(iInside);
		
		return p;
	}
	
	public static Picture setShape(int iShape) {
		
		Picture res = null;		
		switch (iShape) {
		case 0: res = new Empty();break;
		case 1: res = new Triangle(); break;
		case 2: res = new Square(); break;
		case 3: res = new Circle(); break;
		case 4: res = new Rectangle(); break;
		case 5: res = new Rhomb(); break;
		}
		
		return res;
	}
	
	public void setColor (Color c) {
		this.color = c;
	}

	public void setColor (int ic) {
		this.color = colorByInt(ic);
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
		return (shape != Picture.EMPTY);
	}
	
	public static Color colorByInt (int ic) {
		
		if (ic < 0 || ic > NUMBER_OF_COLORS-1) return null;
		
		switch (ic) {
		case 0: return new Color (192,0,192);
		case 1: return Color.BLACK;
		case 2: return Color.BLUE;
		case 3: return new Color(0,127,127);
		case 4: return new Color(255,127,63);
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
			gr.drawLine(x, y+height/2+1, x+width, y+height/2+1);
			gr.drawLine(x+width/2, y, x+width/2, y+height);
			gr.drawLine(x+width/2+1, y, x+width/2+1, y+height);
		}
		if (getInside()==Picture.INSIDE_HORLINE){
			gr.drawLine(x, y+height/2, x+width, y+height/2);
			gr.drawLine(x, y+height/2+1, x+width, y+height/2+1);
		}
		if (getInside()==Picture.INSIDE_VERTLINE){
			gr.drawLine(x+width/2, y, x+width/2, y+height);
			gr.drawLine(x+width/2+1, y, x+width/2+1, y+height);
		}
		if (getInside()==Picture.INSIDE_OBLIQUE_CROSS){
			gr.drawLine(x, y, x+width, y+height);
			gr.drawLine(x, y+1, x+width-1, y+height);
			gr.drawLine(x+width, y, x, y+height);
			gr.drawLine(x+width, y+1, x+1, y+height);
		}
		if (getInside()==Picture.INSIDE_NE_SW_LINE){
			gr.drawLine(x+width, y, x, y+height);
			gr.drawLine(x+width, y+1, x+1, y+height);
		}
		if (getInside()==Picture.INSIDE_NW_SE_LINE){
			gr.drawLine(x, y, x+width, y+height);
			gr.drawLine(x, y+1, x+width-1, y+height);
		}
	}
	public static void setBackgroundColor(Color backgroundColor) {
		Picture.backgroundColor = backgroundColor;
	}
	
	
}

