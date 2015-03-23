package tel_ran.tests.dataset;

import tel_ran.tests.generator.Testing_Problem;

public class TwoDimensionalStringArray extends TwoDimensional {
	
	public String[][] data;
	
	public TwoDimensionalStringArray() {
		super();
		data = new String[0][0];
		len = width = 0;
		setType();
	}
	
	public TwoDimensionalStringArray(String[][] t) {
		super();
		len = t.length;		
		width = findWidth(t);
		data = new String[len][width];
		copyTwoDim(t, data);	
	}
			
	private void setType() {
		super.type = DataSet.TWO_DIM_STRING_ARRAY;	
	}
	
	public String[][] getData() {
		return data;
	}

}
	
	


