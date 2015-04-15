package tel_ran.tests.pictures;

import tel_ran.tests.tools.RandFunc;

public class PictureN {
	
	private int color=1;
	private int inside=1;
	private int shape=1;
	
	private String name = "";
	
	public static final int NUMBER_OF_SHAPES = Picture.NUMBER_OF_SHAPES;
	public static final int nS1 = NUMBER_OF_SHAPES-1;
	
	public static final String[] shapeArray = {"Empty", "Triangle", "Square", "Circle", "Rectangle", "Rhomb"};
	
	public static final int NUMBER_OF_COLORS = Picture.NUMBER_OF_COLORS;
	public static final int nC1 = NUMBER_OF_COLORS-1;
	
	public static final String[] colorArray = {"Magenta", "Black", "Blue", "Gray", "Orange","Red"};
	
	public static final int NUMBER_OF_INSIDES = Picture.NUMBER_OF_INSIDES;
	public static final int nI1 = NUMBER_OF_INSIDES-1;
	

	public static final String[] insideArray = {"Empty", "Full", "VertLine", "HorLine", "Erect cross", "NW-SE", "NE-SW", "Oblique cross"};

	public boolean equals(PictureN obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		PictureN other = (PictureN) obj;
		if (color != other.color) return false;
		if (shape != other.shape) return false;
		if (inside != other.inside)	return false;
		
		return true;
	}
	
	public PictureN copyPicture() {
		
		PictureN p = new PictureN();
		p.setShape(shape);
		p.setColor(color);
		p.setInside(inside);
		return p;
	}
	
	//   setters and getters

	//   picture
	
	public void setPicture (int ip, int ic, int ii){
		 
		setShape(ip);
		setColor(ic);
		setInside(ii);
	}
	
	public void setRandomPicture() {
		
		setRandomShape(true);
		setRandomColor();
		setRandomInside();
	}
	
	public void setEmptyPicture() {
		setShape(Picture.EMPTY);
	}
	
	// shape
	
	public void setShape(int iShape) {
		shape = iShape;
		name = shapeArray[iShape];
	}
	
	public void setRandomShape(boolean noEmpty) {
		setShape(RandFunc.IntRandomInRange((noEmpty ? 1 : 0), nS1-1));
	}	
    public int getShape() {
		return shape;
	}
	
	// color
	
	public void setColor(int ic) {
		color = ic;
	}

	public void setRandomColor() {
		setColor(RandFunc.IntRandomInRange(0, nC1));	
	}	
	
	
	public int getColor() { 
		return color;
	}
	
	public String getColorString() {
		return colorArray[color];
	}
	
	// inside
	
	public void setInside(int ii) {
		inside = ii;
	}

	public void setRandomInside() {
		setInside(RandFunc.IntRandomInRange(0, nI1));
	}

	
	public int getInside() {
		return inside;
	}
	
	public String getInsideString() {
		return insideArray[inside];
	}
	
		
	//service
	
	public boolean isEmpty() {
		return (getShape() != Picture.EMPTY);
	}
	
	public String getName() {
		return name;
	}

}
