package tel_ran.tests.box_generator;

import java.util.Random;

import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.tools.RandFunc;

public abstract class TaskBoxGenerator {
	
	protected Testing_Problem[] tasks;
	protected int len;
	protected int numberOfTask;
	private Random ran = new Random();
	protected String category;
		
	public Testing_Problem generate(int level) {
		
		
		int seq = ran.nextInt(numberOfTask);
		
		tasks[seq].generate(level);
		return tasks[seq];		
	}

	public int getNumberOfTask() {
		return this.numberOfTask;
	}

	public String getCategory() {
		return category;
	}
	
	
	
	
}
