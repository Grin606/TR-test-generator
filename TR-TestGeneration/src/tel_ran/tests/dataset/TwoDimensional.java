package tel_ran.tests.dataset;

public abstract class TwoDimensional extends DataSet {
	
	int width;

	public TwoDimensional() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public int getWidth() {
		return width;
	}
	
	public static void copyTwoDim(Object[][] source, Object[][] dest) {
		assert source.length == dest.length;
		for (int i = 0; i < source.length; i++) {
			assert source[i].length >= dest[i].length;
			System.arraycopy(source[i], 0, dest[i],  0, source[i].length);
		}
	}
	
	public static int findWidth(Object[][] t) {
		int max = 0;
		for (Object[] p : t) 
			if (p.length > max)
				max = p.length;
		return max;		
	}
	
	public boolean isNotEmpty() {
		if (len == 0 || width == 0)
			return false;
		return true;
	}
	
	public abstract Object[][] getData();


}
