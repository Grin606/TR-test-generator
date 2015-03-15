package tel_ran.tests.dataset;

public class TwoDimensionalStringArray {
	
	public String[][] tsArray;
	public int width;
	public int height;
	
	public TwoDimensionalStringArray() {
		
		tsArray = new String[0][0];
		width = 0;
		height = 0;
	}
	
	public TwoDimensionalStringArray(String[][] s) {
	
		if (s == null)return;
		
		height = s.length;
		
		if (height != 0) width = s[0].length;
		else return;
		
		if (width == 0)return;
		
		tsArray = s;
		
	}
	
	public boolean isNotEmpty() {
		if(width != 0 && height != 0)return true;
		return false;
	}

//	public String[][] getTDSA() {
//		return tsArray;
//	}
//
//	public int getWidth() {
//		return width;
//	}
//
//	public int getHeight() {
//		return height;
//	}
}
	
	


