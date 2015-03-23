package tel_ran.tests.dataset;

public abstract class DataSet {
	
	public static final int ONE_DIM_STRING_ARRAY = 1; 
	public static final int TWO_DIM_STRING_ARRAY = 2;
	public static final int ONE_DIM_PICTURE_ARRAY = 3;
	public static final int TWO_DIM_PICTURE_ARRAY = 4;
	public static final int THREE_DIM_PICTURE_ARRAY = 5;
	
	public ExternalView exv;
	int len;
	int type;
	
	public DataSet() {
		super();
		exv = new ExternalView();
	}
		
	public String whatKindOfString() {
		return this.getClass().getSimpleName();
	}
		
	public void setBorders (boolean out, boolean in, boolean forEach) {
		exv.fr.frames_out = out;
		exv.fr.frames_in = in;
		exv.fr.frames_for_each = forEach;
	}
	
	public boolean isOutBorder() {
		return exv.fr.frames_out;
	}
	
	public boolean isInBorders() {
		return exv.fr.frames_in;
	}
	
	public boolean isForEachBorder(){
		return exv.fr.frames_for_each;
	}
	
	public int whatBorders() {
		return exv.fr.typeOfFrames;
	}
	
	public abstract boolean isNotEmpty(); 

	public int getKind() {		
		return type;
	}
	
	public abstract Object getData();

}
