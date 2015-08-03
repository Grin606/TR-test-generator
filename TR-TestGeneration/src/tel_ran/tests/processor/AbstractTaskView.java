package tel_ran.tests.processor;

import java.util.HashSet;

import tel_ran.tests.interfaces.ITaskView;


public abstract class AbstractTaskView implements ITaskView {

	String path;
	String dirName;
	String[] answer;
	HashSet<String> unique;
	
	
	
	public AbstractTaskView() {
		super();
		answer = new String[N_ANSWERS_FIELDS];
		unique = new HashSet<String>(); 		
	}


	public AbstractTaskView(String path, String dirName) {
		super();
		this.path = path;
		this.dirName = dirName;
		answer = new String[N_ANSWERS_FIELDS];
		unique = new HashSet<String>(); 
	}


	public void setPath(String path, String dirName) {
		this.path = path;
		this.dirName = dirName;
	}



	
		


}
