package tel_ran.tests.generator;

import tel_ran.tests.dataset.*;
import tel_ran.tests.pictures.Picture;
import tel_ran.tests.processor.ITaskView;

public abstract class Testing_Problem extends AbstractTest {
	
	public static final int ONE_DIM_STRING_ARRAY = 1; 
	public static final int TWO_DIM_STRING_ARRAY = 2;
	public static final int ONE_DIM_PICTURE_ARRAY = 3;
	public static final int TWO_DIM_PICTURE_ARRAY = 4;
	public static final int THREE_DIM_PICTURE_ARRAY = 5;
	

	protected String correctAnswerChar;
		
	public DataSet p;            //problem
	public DataSet a;            //answers
	
	
	public Testing_Problem() {
		super();	
		numOfAnswers = 5;		
		p = new DataSet();
		a = new DataSet();
		typeOfView = ITaskView.PICTURE;
	}
	
	@Override
	public String getCorrectAnswerChar(){
		return correctAnswerChar;
	}
	
	@Override
	public int WhatKindOfProblem() {
		return p.getKind();
	}
	
	public int WhatKindOfAnswers() {
		return a.getKind();
	}
	
	public String[] getODSA_p() {
		return p.os.osArray;
	}
	public int getODSAlength_p() {
		return p.os.len;
	}
	public String[] getODSA_a() {
		return a.os.osArray;
	}
	public int getODSAlength_a() {
		return a.os.len;
	}
	public Picture[] getODPA_p() {
		return p.op.opArray;
	}
	public int getODPAlength_p() {
		return p.op.len;
	}
	public Picture[] getODPA_a() {
		return a.op.opArray;
	}
	public int getODPAlength_a() {
		return a.op.len;
	}
	
	public String[][] getTDSA_p() {
		return p.ts.tsArray;
	}
	public int[] getTDSAdims_p() {
		int[] res = new int[2];
		res[0] = p.ts.width;
		res[1] = p.ts.height;
		return res;
	}
	public String[][] getTDSA_a() {
		return a.ts.tsArray;
	}
	public int[] getTDSAdims_a() {
		int[] res = new int[2];
		res[0] = a.ts.width;
		res[1] = a.ts.height;
		return res;
	}
		
	public Picture[][] getTDPA_p() {
		return p.tp.tpArray;
	}
	public int[] getTDPAdims_p() {
		int[] res = new int[2];
		res[0] = p.tp.width;
		res[1] = p.tp.height;
		return res;
	}
	public Picture[][] getTDPA_a() {
		return a.tp.tpArray;
	}
	public int[] getTDPAdims_a() {
		int[] res = new int[2];
		res[0] = a.tp.width;
		res[1] = a.tp.height;
		return res;
	}
	public Picture[][][] getTHDPA_p() {
		return p.thp.thpArray;
	}
	public int[] getTHDPAdims_p() {
		int[] res = new int[3];
		res[0] = p.thp.width;
		res[1] = p.thp.height;
		res[2] = p.thp.deep;
		return res;
	}
	public Picture[][][] getTHDPA_a() {
		return a.thp.thpArray;
	}
	public int[] getTHDPAdims_a() {
		int[] res = new int[3];
		res[0] = a.thp.width;
		res[1] = a.thp.height;
		res[1] = a.thp.deep;
		return res;
	}
	
	public Frames getProblemFrames() {
		return p.exv.fr;
	}
	public Frames getAnswerFrames() {
		return a.exv.fr;
	}
	
	
	
}
	


	

