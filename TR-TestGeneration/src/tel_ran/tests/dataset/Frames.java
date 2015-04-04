package tel_ran.tests.dataset;

public class Frames {
	
	public static final int NO_FRAMES = 0;
	public static final int INSIDE = 1;
	public static final int OUTSIDE = 2;
	public static final int INSIDE_AND_OUTSIDE = 3;
	
	private int frInternal = 0;
	private int frMedium = 0;
	private int frExternal = 0;
	
	public int getFrInternal() {
		return frInternal;
	}
	public void setFrInternal(int fr) {
		if (fr == 1 || fr == 3 ) return;
		this.frInternal = fr;
	}
	public int getFrMedium() {
		return frMedium;
	}
	public void setFrMedium(int fr) {
		this.frMedium = fr;
	}
	public int getFrExternal() {
		return frExternal;
	}
	public void setFrExternal(int fr) {
		this.frExternal = fr;
	}
	
	
	
}
