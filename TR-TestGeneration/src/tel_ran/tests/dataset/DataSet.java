package tel_ran.tests.dataset;

import tel_ran.tests.pictures.Picture;

public class DataSet {
	
	public OneDimensionalStringArray os;
	public TwoDimensionalStringArray ts;
	public OneDimensionalPictureArray op;
	public TwoDimensionalPictureArray tp;
	public ThreeDimensionalPictureArray thp;
	
	public ExternalView exv;
	
	public DataSet() {
		os = new OneDimensionalStringArray();
		ts = new TwoDimensionalStringArray();
		op = new OneDimensionalPictureArray();
		tp = new TwoDimensionalPictureArray();
		thp = new ThreeDimensionalPictureArray();
		
		exv = new ExternalView();
	}

	public void setODSA(String[] t) {
		os = new OneDimensionalStringArray(t);
	}
	public void setTDSA(String[][] t) {
		ts = new TwoDimensionalStringArray(t);
	}
	public void setODPA(Picture[] t) {
		op = new OneDimensionalPictureArray(t);
	}
	public void setTDPA(Picture[][] t) {
		tp = new TwoDimensionalPictureArray(t);
	}
	public void setTHDPA(Picture[][][] t) {
		thp = new ThreeDimensionalPictureArray(t);
	}
	
	public boolean isODSA(){
		return (os.isNotEmpty());
	}
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
	
	public int getKind() {
		if (isODSA()) return 1;
		if (isTDSA()) return 2;
		if (isODPA()) return 3;
		if (isTDPA()) return 4;
		if (isTHDPA()) return 5;
		return 0;
	}
	
	public void setFrames (boolean out, boolean all, boolean if_full) {
		exv.fr.frames_out = out;
		exv.fr.frames_all = all;
		exv.fr.frames_if_full = if_full;
	}

	public int whatFrames() {
		if (exv.fr.frames_out) return 1;
		if (exv.fr.frames_all) return 2;
		if (exv.fr.frames_if_full) return 3;
		return 0;
	}
		

	
}
