package tel_ran.tests.dataset;

public abstract class DataSet {
	
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
