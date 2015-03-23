package tel_ran.tests.dataset;

import tel_ran.tests.generator.Testing_Problem;

public class ThreeDimensionalPictureArray extends ThreeDimensional {
	
	public Picture[][][] data;
	
	public ThreeDimensionalPictureArray() {
		super();
		data = new Picture[0][0][0];
		len = width = height = 0;
		setType();
	}

	public ThreeDimensionalPictureArray(Picture[][][] t) {
		super();
		len = t.length;
		width = TwoDimensional.findWidth(t);
		height = findHeight(t);
		data = new Picture[len][width][height];
		copyThreeDim(t, data);		
	}
			
	private void setType() {
		super.type = Testing_Problem.THREE_DIM_PICTURE_ARRAY;	
	}
	
	public Picture[][][] getData() {
		return data;
	}
	
	

}
