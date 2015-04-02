package tel_ran.tests.box_generator;

import java.util.Random;

import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.tools.RandFunc;

public abstract class TaskBoxGenerator {
	
	Testing_Problem[] tasks;
	int len;
	int numberOfTask;
		
	public Testing_Problem generate(int level) {
		Random ran = new Random();
		
		int seq = ran.nextInt(numberOfTask);
		tasks[seq].generate(level);
		return tasks[seq];		
	}

	public int getNumberOfTask() {
		return this.numberOfTask;
	}
	
}
