package tel_ran.tests.box_generator;

import java.util.List;
import java.util.Random;

import tel_ran.tests.interfaces.ITaskView;
import tel_ran.tests.interfaces.ITestingProblem;

public abstract class TaskBoxGenerator {
	
	protected ITestingProblem[] tasks;
	protected int len;
	protected int numberOfTask;
	private Random ran = new Random();	
	String getView;
	protected String dirName;	
	
	
	public ITestingProblem generate(int level) {
				
		int seq = ran.nextInt(numberOfTask);
		
		tasks[seq].generate(level);
		return tasks[seq];		
	}

	public int getNumberOfTask() {
		return this.numberOfTask;
	}

	public String getDirName() {
		return dirName;
	}
	
	public ITaskView getView() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return tasks[0].getView();
	}

	
	
}
