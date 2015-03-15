package tel_ran.tests.dataset;

public class TwoDimensionalPictureArray {
	
	public Picture[][] tpArray;
	public int width;
	public int height;
	
	public TwoDimensionalPictureArray() {
		
		tpArray = new Picture[0][0];
		width = 0;
		height = 0;
	}
	
	public TwoDimensionalPictureArray(Picture[][] t) {
		
		height = t.length;
	
		if (height != 0) width = t[0].length;
		else return;
		
		if (width == 0)return;
		
		tpArray = t;
	}
	
	public boolean isNotEmpty() {
		 
		if (height != 0 && width != 0) return true;
		return false;	
	}

//	public Picture[][] getTDPA() {
//		
//		return tpArray;
//	}
//	public int getWidth() {
//		return width;
//	}
//	public int getHeight() {
//		return height;
//	}
	
	

}
