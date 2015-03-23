package tel_ran.tests.dataset;

public abstract class ThreeDimensional extends DataSet {
	
	int width;
	int height;

	public ThreeDimensional() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getHeight() {
		return height;
	}
		
	public boolean isNotEmpty() {
		if (len == 0 || width == 0 || height == 0)
			return false;
		return true;
	}
	
	public static void copyThreeDim(Object[][][] source, Object[][][] dest) {
		assert source.length == dest.length;
		for (int i = 0; i < source.length; i++) {
			assert source[i].length >= dest[i].length;
			TwoDimensional.copyTwoDim(source[i], dest[i]);
		}
	}
	
	public static int findHeight(Object[][][] t) {
		int max = 0;
		for (Object[][] p : t)
			for (Object[] p2 : p)
				if (p2.length > max)
					max = p2.length;
		return max;		
	}
	
	public abstract Object[][][] getData();

}
