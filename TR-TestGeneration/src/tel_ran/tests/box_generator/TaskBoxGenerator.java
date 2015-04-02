package tel_ran.tests.box_generator;

import java.util.Random;

import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.tools.RandFunc;

public abstract class TaskBoxGenerator {
	
	Testing_Problem[] tasks;
	int len;
	int numberOfTask;
	Random ran = new Random();
		
	public Testing_Problem generate(int level) {
		
		
		int seq = ran.nextInt(numberOfTask);
		
		tasks[seq].generate(level);
		return tasks[seq];		
	}

	public int getNumberOfTask() {
		return this.numberOfTask;
	}
	
}
