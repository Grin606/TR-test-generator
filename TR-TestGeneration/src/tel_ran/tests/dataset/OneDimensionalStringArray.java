package tel_ran.tests.dataset;

public class OneDimensionalStringArray {

	public String[] osArray;
	public int len = 0;
	
	public OneDimensionalStringArray() {
		
		len = 0;
		osArray = new String[0];
	}
	
	public OneDimensionalStringArray(String[] s) {
		
		if (s == null)return;
		
		len = s.length;
		if (len == 0) return;
		osArray = s;
	}
	
	public boolean isNotEmpty() {
		
		if (len == 0) return false;
		return true;
	}
}
