package tel_ran.tests.dataset;

/**
 * Class, containing info on the frames around and inside data 
 *
 */
public class Frames {
	
	
	public static final int NO_BORDERS = 0;
	public static final int OUT_BORDERS = 1;	
	public static final int ALL_BORDERS = 2;
	public static final int CELL_BORDERS_IF_FULL = 3;
	public static final int GRID = 4;
	public static final int INNER_OBJECT_BORDEDS = 5;
	
	public boolean frames_out;
	public boolean frames_all;
	public boolean frames_if_full;
	
	
	/**
	 * Constructor setting false to all fields (no frames)
	 */
	public Frames() {
		frames_out = false;
		frames_all = false;
		frames_if_full = false;
	}
}
