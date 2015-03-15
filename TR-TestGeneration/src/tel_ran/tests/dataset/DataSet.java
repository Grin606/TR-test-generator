package tel_ran.tests.dataset;

/** Class, containing data set for problems and answers **/
public class DataSet {
	
	/** one dimensional string array **/
	public OneDimensionalStringArray os;
	/** two dimensional string array **/
	public TwoDimensionalStringArray ts;
	/** one dimensional picture array **/
	public OneDimensionalPictureArray op;
	/** two dimensional picture array **/
	public TwoDimensionalPictureArray tp;
	/** three dimensional picture array **/
	public ThreeDimensionalPictureArray thp;
	
	/** 
	 * class containing info on external view of the data (frames,
	 * fonts, colors etc.) 
	 */
	public ExternalView exv;
	
	public DataSet() {
		os = new OneDimensionalStringArray();
		ts = new TwoDimensionalStringArray();
		op = new OneDimensionalPictureArray();
		tp = new TwoDimensionalPictureArray();
		thp = new ThreeDimensionalPictureArray();
		
		exv = new ExternalView();
	}

	/**
	 * Sets one dimensional string array
	 * @param t - content of the array
	 */
	public void setODSA(String[] t) {
		os = new OneDimensionalStringArray(t);
	}
	
	/**
	 * Sets two dimensional string array
	 * @param t - content of the array
	 */
	public void setTDSA(String[][] t) {
		ts = new TwoDimensionalStringArray(t);
	}
	
	/**
	 * Sets one dimensional picture array 
	 * @param t - content of the array
	 */
	public void setODPA(Picture[] t) {
		op = new OneDimensionalPictureArray(t);
	}
	public void setTDPA(Picture[][] t) {
		tp = new TwoDimensionalPictureArray(t);
	}
	public void setTHDPA(Picture[][][] t) {
		thp = new ThreeDimensionalPictureArray(t);
	}
	
	
	/**
	 * method checks if the corresponding field is empty
	 * @return true - if it's empty
	 */
	public boolean isODSA(){
		return (os.isNotEmpty());
	}
	
	/**
	 * method checks if the corresponding field is empty
	 * @return true - if it's empty
	 */
	public boolean isTDSA(){
		return (ts.isNotEmpty());
	}
	public boolean isODPA(){
		return (op.isNotEmpty());
	}
	public boolean isTDPA(){
		return (tp.isNotEmpty());
	}
	public boolean isTHDPA(){
		return (thp.isNotEmpty());
	}
	
	
	/**
	 * Returns the integer corresponding at data kind
	 * @return :
	 * 1 = Testing_Problem.ONE_DIM_STRING_ARRAY - one dimensional string array (ODSA)
	 * 2 = Testing_Problem.TWO_DIM_STRING_ARRAY - two dimensional string array (TDSA)
	 * 3 = Testing_Problem.ONE_DIM_PICTURE_ARRAY - one dimensional picture array (ODPA)
	 * 4 = Testing_Problem.TWO_DIM_PICTURE_ARRAY - two dimensional picture array (ODSA)
	 * 5 = Testing_Problem.THREE_DIM_PICTURE_ARRAY - three dimensional picture array (ODSA)
	 */
	public int getKind() {
		if (isODSA()) return 1;
		if (isTDSA()) return 2;
		if (isODPA()) return 3;
		if (isTDPA()) return 4;
		if (isTHDPA()) return 5;
		return 0;
	}
	
	
	/**
	 * Sets information on frames to ExternalView exv: 
	 * @param out - frame around data
	 * @param all - frames around all data cells
	 * @param if_full - frames around data cells if they are full
	 */
	public void setFrames (boolean out, boolean all, boolean if_full) {
		exv.fr.frames_out = out;
		exv.fr.frames_all = all;
		exv.fr.frames_if_full = if_full;
	}
	
	/**
	 * Gets integer describing frames around and inside data:
	 * @return - 
	 * 0 = Frames. NO_FRAMES - no frames
	 * 1 = Frames. FRAME_OUT - frame around data
	 * 2 = Frames. ALL_FRAMES - frames around all data cells
	 * 3 = Frames. FRAMES_IF_FULL - frames around data cells if they are full
	 */
	public int whatFrames() {
		if (exv.fr.frames_out) return 1;
		if (exv.fr.frames_all) return 2;
		if (exv.fr.frames_if_full) return 3;
		return 0;
	}
	

	

	
}
