package tel_ran.tests.dataset;

/**
 * Class, containing info on the frames around and inside data 
 *
 */
public class Frames {
	
	
	public static final int NO_FRAMES = 0;
	public static final int FRAME_OUT = 1;
	public static final int ALL_FRAMES= 2;
	public static final int FRAMES_IF_FULL = 3;
	
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
