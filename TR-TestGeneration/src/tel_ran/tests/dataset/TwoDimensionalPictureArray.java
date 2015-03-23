package tel_ran.tests.dataset;

import tel_ran.tests.generator.Testing_Problem;

public class TwoDimensionalPictureArray extends TwoDimensional {
	
	public Picture[][] data;
	
	public TwoDimensionalPictureArray() {
		super();
		data = new Picture[0][0];
		len = width = 0;
		setType();
	}
	
	public TwoDimensionalPictureArray(Picture[][] t) {		
		super();
		len = t.length;
		width = findWidth(t);
		data = new Picture[len][width];
		copyTwoDim(t, data);	
	}
			
	private void setType() {
		super.type = Testing_Problem.TWO_DIM_PICTURE_ARRAY;	
	}
	
	public Picture[][] getData() {
		return data;
	}


}
