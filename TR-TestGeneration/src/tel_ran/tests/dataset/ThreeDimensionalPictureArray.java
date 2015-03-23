package tel_ran.tests.dataset;

import tel_ran.tests.pictures.Picture;

public class ThreeDimensionalPictureArray {
	
	public Picture[][][] thpArray;
	public int width;
	public int height;
	public int deep;
	
	public ThreeDimensionalPictureArray() {
		
		thpArray = new Picture[0][0][0];
		width = 0;
		height = 0;
		deep = 0;
	}

	public ThreeDimensionalPictureArray(Picture[][][] t) {
		
		deep = t.length;
		if (deep != 0) {
			height = t[0].length;
			if (height != 0) {
				width = t[0][0].length;
			} else return;
		}else return;
		if (width == 0)return;
		
		thpArray = t;
	}
	
	public boolean isNotEmpty() {
		
		if (deep !=0 && height != 0 && width != 0) return true;
		return false;
		
	}

//	public Picture[][][] getTDPA() {
//		return thpArray;
//	}
//
//	public int getWidth() {
//		return width;
//	}
//
//	public int getHeight() {
//		return height;
//	}
//
//	public int getDeep() {
//		return deep;
//	}
	
	
	
	
	
	
	

}
