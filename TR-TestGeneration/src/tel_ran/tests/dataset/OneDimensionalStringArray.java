package tel_ran.tests.dataset;

import tel_ran.tests.generator.Testing_Problem;

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
		super.type = Testing_Problem.ONE_DIM_STRING_ARRAY;	
	}
	
	public String[] getData() {
		return data;
	}


}
