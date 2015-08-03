package tel_ran.tests.box_generator;

import java.util.List;
import java.util.Random;

import tel_ran.tests.generator.ITestingProblem;

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
	
	public String getView() {
		return tasks[0].getView();
	}
	
	public abstract int getCategoryIndex();
	
	
}
