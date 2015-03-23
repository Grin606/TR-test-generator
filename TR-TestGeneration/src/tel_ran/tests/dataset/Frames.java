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
	public static final int FRAME_IN = 4;
	public static final int FRAMES_IF_FULL_OUT = 5;
	
	boolean frames_out;
	boolean frames_in;
	boolean frames_for_each;
	
	int typeOfFrames = 0;
	
	/**
	 * Constructor setting false to all fields (no frames)
	 */
	public Frames() {
		frames_out = false;
		frames_in = false;
		frames_for_each = false;
	}
	
	public void allSet(boolean out, boolean in, boolean forEach) {
		frames_out = out;
		frames_in = in;
		frames_for_each = forEach;
		setType();
	}
	
	private void setType() {
		
		if(frames_out) {
			typeOfFrames = 1;
			if(frames_in)
				typeOfFrames = 2;
			else if(frames_for_each)
				typeOfFrames = 5;	
			return;
		}
		if(frames_in)
			typeOfFrames = 4;			
		else if(frames_for_each)
			typeOfFrames = 3;
		else
			typeOfFrames = 0;
			
	}
	
	
	
	
}
