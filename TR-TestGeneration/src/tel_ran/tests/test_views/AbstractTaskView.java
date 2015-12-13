package tel_ran.tests.test_views;

import java.util.HashSet;

import tel_ran.tests.interfaces.ITaskView;
import tel_ran.tests.utils.files.FileService;


public abstract class AbstractTaskView implements ITaskView {

	protected FileService fileService;
	String[] answer;
	HashSet<String> unique;
	
	
	
	public AbstractTaskView() {
		super();
		answer = new String[N_ANSWERS_FIELDS];
		unique = new HashSet<String>(); 		
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;		
	}





	
		


}
