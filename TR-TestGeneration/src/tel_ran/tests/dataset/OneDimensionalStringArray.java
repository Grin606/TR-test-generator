package tel_ran.tests.dataset;

public class OneDimensionalStringArray extends OneDimensional {
	
	public String[] data;
		
	public OneDimensionalStringArray() {
		super();		
		len = 0;
		setType();
	}	
	
	public OneDimensionalStringArray(String[] data) {
		super();
		this.len = data.length;		
		this.data = new String[this.len];
		System.arraycopy(data, 0, this.data, 0, this.len);
		setType();
		
	}

	private void setType() {
		super.type = DataSet.ONE_DIM_STRING_ARRAY;	
	}
	
	public String[] getData() {
		return data;
	}


}
