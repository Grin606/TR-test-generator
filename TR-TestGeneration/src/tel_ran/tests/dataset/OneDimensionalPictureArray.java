package tel_ran.tests.dataset;

import tel_ran.tests.pictures.Picture;

public class OneDimensionalPictureArray {
	
	public Picture[] opArray;
	public int len;
		
	public OneDimensionalPictureArray() {
		opArray = new Picture[0];
		len = 0;
	}
	public OneDimensionalPictureArray(Picture[] p) {
		
		len = p.length;
		if (len == 0) return;
		
		opArray = p;
	}
	
	public boolean isNotEmpty() {
		if (len == 0) return false;
		return true;
	}
	

}
