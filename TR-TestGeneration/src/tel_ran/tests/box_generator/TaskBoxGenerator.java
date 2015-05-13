package tel_ran.tests.box_generator;

import java.io.FileNotFoundException;
import java.util.Random;

import tel_ran.tests.generator.ITestingProblem;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.tools.RandFunc;

public abstract class TaskBoxGenerator {
	
	protected ITestingProblem[] tasks;
	protected int len;
	protected int numberOfTask;
	private Random ran = new Random();	
	String getView;
	protected String cat;	
	
	
	public ITestingProblem generate(int level) {
				
		int seq = ran.nextInt(numberOfTask);
		
		tasks[seq].generate(level);
		return tasks[seq];		
	}

	public int getNumberOfTask() {
		return this.numberOfTask;
	}

	public String getCategory() {
		return this.cat;
	}
	
	public String getView() {
		return tasks[0].getView();
	}
	
	
	
}
