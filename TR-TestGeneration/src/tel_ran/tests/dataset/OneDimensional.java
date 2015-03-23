package tel_ran.tests.dataset;

public abstract class OneDimensional extends DataSet {
		
	public OneDimensional() {
		super();		
	}
		
	public boolean isNotEmpty() {
		if (len == 0)
			return false;
		return true;
	}
	
	public abstract Object[] getData(); 

}
