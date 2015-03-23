package tel_ran.tests.box_generator;

import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.tools.RandFunc;

public abstract class TaskBoxGenerator {
	
	Testing_Problem[] tasks;
	int len;
	
	public Testing_Problem generate() {
		int seq = RandFunc.IntRandomInRange(0, len-1);
		tasks[seq].generate();
		return tasks[seq];		
	}	

}
