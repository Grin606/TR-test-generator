package tel_ran.tests.interfaces;

import tel_ran.tests.generator.ITestingProblem;


public interface ITaskView {
	
	public static final String PICTURE = "tel_ran.tests.processor.ImageView"; 
	public static final String CODE = "tel_ran.tests.processor.CodeView";
	public static final int N_ANSWERS_FIELDS = 9;
	
	String[] getTaskViews(ITestingProblem task, int lvl) throws Exception;
	void setPath(String path, String dirName);

}
