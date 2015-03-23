package tel_ran.tests.dataset;

public class OneDimensionalPictureArray extends OneDimensional {

	public Picture[] data;	
		
	public OneDimensionalPictureArray() {
		super();
		data = new Picture[0];
		super.len = 0;
		setType();
	}
	
	public OneDimensionalPictureArray(Picture[] p) {
		super();
		len = p.length;
		data = new Picture[len];
		System.arraycopy(p, 0, data, 0, len);
		setType();
	}
	
	private void setType() {
		super.type = DataSet.ONE_DIM_PICTURE_ARRAY;	
	}
	
	public Picture[] getData() {
		return data;
	}


}
