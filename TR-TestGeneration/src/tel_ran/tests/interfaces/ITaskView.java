package tel_ran.tests.interfaces;

import tel_ran.tests.utils.files.FileService;

public interface ITaskView {
	
	public static final String PICTURE = "tel_ran.tests.test_views.ImageView"; 
	public static final String CODE = "tel_ran.tests.test_views.CodeView";
	public static final int N_ANSWERS_FIELDS = 9;
	
	String[] getTaskViews(ITestingProblem task, int lvl) throws Exception;
	void setFileService(FileService fileService);

}
